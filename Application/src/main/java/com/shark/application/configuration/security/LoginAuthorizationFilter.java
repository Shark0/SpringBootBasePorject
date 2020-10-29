package com.shark.application.configuration.security;

import com.shark.application.controller.pojo.AuthAccountDo;
import com.shark.application.dao.repository.account.AccountRepository;
import com.shark.application.dao.repository.account.pojo.AccountDo;
import com.shark.application.dao.repository.permission.PermissionRepository;
import com.shark.application.dao.repository.permission.pojo.PermissionDo;
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

    private AccountRepository accountRepository;
    private PermissionRepository permissionRepository;


    public LoginAuthorizationFilter(
            AuthenticationManager authenticationManager,
            AccountRepository accountRepository,
            PermissionRepository permissionRepository) {
        super(authenticationManager);
        this.accountRepository = accountRepository;
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
            List<PermissionDo> permissionDoList = permissionRepository.findByAccount(account);
            for(PermissionDo permissionDo : permissionDoList) {
                authorities.add(new SimpleGrantedAuthority(permissionDo.getCode()));
            }
        }

        AccountDo accountDo = accountRepository.findByAccount(account);
        AuthAccountDo authAccountDo = null;
        if(accountDo != null) {
            authAccountDo = new AuthAccountDo();
            authAccountDo.setAccount(accountDo.getAccount());
            authAccountDo.setId(accountDo.getId());
            authAccountDo.setName(accountDo.getName());
        }


        return new UsernamePasswordAuthenticationToken(authAccountDo, null, authorities);
    }
}
