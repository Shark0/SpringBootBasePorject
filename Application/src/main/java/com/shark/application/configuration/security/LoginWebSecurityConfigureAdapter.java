package com.shark.application.configuration.security;


import com.shark.application.dao.repository.account.AccountRepository;
import com.shark.application.dao.repository.permission.PermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;


@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class LoginWebSecurityConfigureAdapter extends WebSecurityConfigurerAdapter {

    private final AccountRepository accountRepository;
    private final PermissionRepository permissionRepository;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
//        System.out.println("LoginWebSecurityConfigureAdapter configure http security");
        httpSecurity
                .cors()
                .and()
                .csrf()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, SecurityConfiguration.AUTH_URL + "/login").permitAll()
                .antMatchers(HttpMethod.POST, SecurityConfiguration.AUTH_URL + "/register").permitAll()
                .antMatchers(HttpMethod.POST, SecurityConfiguration.AUTH_URL + "/refresh").permitAll()
                .antMatchers(HttpMethod.GET, "/swagger-ui.html").permitAll()
                .antMatchers(HttpMethod.GET, "/swagger-resources/**").permitAll()
                .antMatchers(HttpMethod.GET, "/v2/**").permitAll()
                .antMatchers(HttpMethod.GET, "/webjars/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new LoginAuthorizationFilter(authenticationManager(), accountRepository, permissionRepository))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}
