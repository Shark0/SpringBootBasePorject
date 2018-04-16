package com.shark.base.security.filter;

import com.shark.base.repository.mysql.member.MemberRepository;
import com.shark.base.repository.mysql.member.dao.MemberDaoEntity;
import com.shark.base.security.Config;
import com.shark.base.util.StringUtil;
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

    private MemberRepository memberRepository;

    public LoginAuthorizationFilter(AuthenticationManager authenticationManager, MemberRepository memberRepository) {
        super(authenticationManager);
        this.memberRepository = memberRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(Config.HEADER);
        System.out.println("LoginAuthorizationFilter doFilterInternal() header: " + header);
        if(header == null || !header.startsWith(Config.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(Config.HEADER);
        System.out.println("LoginAuthorizationFilter getAuthentication() token: " + token);
        if (!StringUtil.isEmpty(token)) {
            //parser token
            String memberId = Jwts.parser()
                    .setSigningKey(Config.SECRET.getBytes())
                    .parseClaimsJws(token.replace(Config.TOKEN_PREFIX, ""))
                    .getBody()
                    .getSubject();

            if (memberId != null) {
                System.out.println("LoginAuthorizationFilter getAuthentication memberId: " + memberId);
                MemberDaoEntity memberDaoEntity = memberRepository.findOne(Long.valueOf(memberId));
                List<GrantedAuthority> authorities = new ArrayList<>();
                //TODO add role in authorities
                long id = memberDaoEntity.getId();
                System.out.println("LoginAuthorizationFilter getAuthentication id: " + id);
                return new UsernamePasswordAuthenticationToken(id, null, authorities);
            }
            return null;
        }
        return null;
    }
}
