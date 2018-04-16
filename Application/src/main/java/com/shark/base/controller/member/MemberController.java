package com.shark.base.controller.member;

import com.shark.base.dto.ResponseDataEntity;
import com.shark.base.dto.ResponseEntity;
import com.shark.base.dto.member.MemberDtoEntity;
import com.shark.base.repository.mysql.member.dao.MemberDaoEntity;
import com.shark.base.security.Config;
import com.shark.base.service.member.RegisterService;
import com.shark.base.service.member.UpdateNameService;
import com.shark.base.util.ServletUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.Date;
import java.util.HashMap;

@org.springframework.web.bind.annotation.RestController
@RequestMapping(value = "/member")
public class MemberController {


    @Autowired
    private RegisterService registerService;

    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public ResponseDataEntity<MemberDtoEntity> register(HttpServletRequest request, HttpServletResponse response) {

        HashMap<String, String> parameters = ServletUtil.generateServiceParameters(request,
                MemberDaoEntity.ACCOUNT,
                MemberDaoEntity.PASSWORD,
                MemberDaoEntity.NAME);

        ResponseDataEntity<MemberDtoEntity> responseDataEntity = registerService.request("", parameters);
        if(responseDataEntity.getReturnCode() == 1) {
            String token = Jwts.builder()
                    .setSubject(parameters.get(MemberDaoEntity.ACCOUNT))
                    .setExpiration(new Date(System.currentTimeMillis() + Config.EXPIRATION_TIME))
                    .signWith(SignatureAlgorithm.HS512, Config.SECRET.getBytes())
                    .compact();
            response.addHeader(Config.HEADER, Config.TOKEN_PREFIX + token);
        }

        return responseDataEntity;
    }


    @Autowired
    private UpdateNameService updateNameService;

    @RequestMapping(method = RequestMethod.POST, value = "/updateName")
    public ResponseEntity updateName(HttpServletRequest request) {
        HashMap<String, String> parameters = ServletUtil.generateServiceParameters(request, MemberDaoEntity.NAME);
        Principal principal = request.getUserPrincipal();
        String memberId = principal.getName();
        System.out.println("MemberController updateName() memberId: " + memberId);
        return updateNameService.request(memberId, parameters);
    }
}
