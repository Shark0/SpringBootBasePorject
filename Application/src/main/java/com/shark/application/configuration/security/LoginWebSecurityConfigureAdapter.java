package com.shark.application.configuration.security;


import com.shark.application.repository.permission.PermissionRepository;
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


@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class LoginWebSecurityConfigureAdapter extends WebSecurityConfigurerAdapter {

    @Autowired
    private PermissionRepository permissionRepository;

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
                .antMatchers(HttpMethod.POST, SecurityConfiguration.LOGIN_URL + "/login").permitAll()
                .antMatchers(HttpMethod.POST, SecurityConfiguration.LOGIN_URL + "/register").permitAll()
                .antMatchers(HttpMethod.POST, SecurityConfiguration.LOGIN_URL + "/refresh").permitAll()
                .antMatchers(HttpMethod.GET, "/swagger-ui.html").permitAll()
                .antMatchers(HttpMethod.GET, "/swagger-resources/**").permitAll()
                .antMatchers(HttpMethod.GET, "/v2/**").permitAll()
                .antMatchers(HttpMethod.GET, "/webjars/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new LoginAuthorizationFilter(authenticationManager(), permissionRepository))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}
