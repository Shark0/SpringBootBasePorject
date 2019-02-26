package com.shark.application.config.security;

import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoginAuthorizationFilter extends BasicAuthenticationFilter {

    public LoginAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(SecurityConfiguration.AUTH_HEADER);
//        System.out.println("LoginAuthorizationFilter doFilterInternal header: " + header);
        if(header != null && header.startsWith(SecurityConfiguration.TOKEN_PREFIX)) {
            UsernamePasswordAuthenticationToken authentication = getAuthentication(header);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String header) {
//        System.out.println("LoginAuthorizationFilter getAuthentication token: " + header);
        String account = null;
        try {
            account = Jwts.parser()
                    .setSigningKey(SecurityConfiguration.SECRET.getBytes())
                    .parseClaimsJws(header.replace(SecurityConfiguration.TOKEN_PREFIX, ""))
                    .getBody()
                    .getSubject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        return new UsernamePasswordAuthenticationToken(account, null, authorities);
    }
}
