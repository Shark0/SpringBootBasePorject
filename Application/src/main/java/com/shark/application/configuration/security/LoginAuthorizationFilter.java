package com.shark.application.configuration.security;

import com.shark.application.repository.permission.PermissionRepository;
import com.shark.application.repository.permission.dao.PermissionDaoEntity;
import com.shark.application.util.StringUtil;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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

    private PermissionRepository permissionRepository;


    public LoginAuthorizationFilter(AuthenticationManager authenticationManager, PermissionRepository permissionRepository) {
        super(authenticationManager);
        this.permissionRepository = permissionRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(SecurityConfiguration.ACCESS_HEADER);
//        System.out.println("LoginAuthorizationFilter doFilterInternal header: " + header);
        if(header != null && header.startsWith(SecurityConfiguration.ACCESS_PREFIX)) {
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
                    .setSigningKey(SecurityConfiguration.ACCESS_SECRET.getBytes())
                    .parseClaimsJws(header.replace(SecurityConfiguration.ACCESS_PREFIX, ""))
                    .getBody()
                    .getSubject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        if(!StringUtil.isEmpty(account)) {
            List<PermissionDaoEntity> permissionDaoEntityList = permissionRepository.findByAccount(account);
            for(PermissionDaoEntity permissionDaoEntity: permissionDaoEntityList ) {
                authorities.add(new SimpleGrantedAuthority(permissionDaoEntity.getCode()));
            }
        }

        return new UsernamePasswordAuthenticationToken(account, null, authorities);
    }
}
