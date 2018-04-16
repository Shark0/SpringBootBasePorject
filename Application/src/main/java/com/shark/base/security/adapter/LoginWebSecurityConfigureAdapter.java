package com.shark.base.security.adapter;


import com.shark.base.repository.mysql.member.MemberRepository;
import com.shark.base.repository.mysql.member.dao.MemberDaoEntity;
import com.shark.base.security.Config;
import com.shark.base.security.filter.LoginAuthorizationFilter;
import com.shark.base.security.filter.LoginUsernamePasswordAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;


@EnableWebSecurity
@Configuration
public class LoginWebSecurityConfigureAdapter extends WebSecurityConfigurerAdapter {

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private MemberRepository memberRepository;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        System.out.println("LoginWebSecurityConfigureAdapter configure http security");
        httpSecurity
                .cors().and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, Config.REGISTER_URL).permitAll()
                .antMatchers(HttpMethod.POST, Config.LOGIN_URL).permitAll()
//                .antMatchers("/role2").hasAuthority("role2")
//                .antMatchers("/role1").hasAuthority("role1")
                .anyRequest().authenticated().and()
                .addFilter(new LoginUsernamePasswordAuthenticationFilter(authenticationManager()))
                .addFilter(new LoginAuthorizationFilter(authenticationManager(), memberRepository))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        ;
    }

    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        System.out.println("LoginWebSecurityConfigureAdapter configure auth");
        authenticationManagerBuilder
                .userDetailsService(new UserDetailsService() {
                    @Override
                    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
                        System.out.println("LoginWebSecurityConfigureAdapter configure auth account: " + account);
                        MemberDaoEntity memberDaoEntity = memberRepository.findByAccount(account);
                        if(memberDaoEntity == null) {
                            throw new UsernameNotFoundException("could not find the account: " + account);
                        }

                        List<GrantedAuthority> authorities = new ArrayList<>();
                        //TODO add role in authorities

                        User user = new User(String.valueOf(memberDaoEntity.getId()), bCryptPasswordEncoder.encode(memberDaoEntity.getPassword()),
                                true, true, true, true,
                                authorities);
                        return user;
                    }
                }).passwordEncoder(bCryptPasswordEncoder);
    }
}
