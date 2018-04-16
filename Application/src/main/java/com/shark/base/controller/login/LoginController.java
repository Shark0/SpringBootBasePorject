package com.shark.base.controller.login;

import com.shark.base.dto.ResponseDataEntity;
import com.shark.base.dto.member.MemberDtoEntity;
import com.shark.base.repository.mysql.member.dao.MemberDaoEntity;
import com.shark.base.service.member.LoginService;
import com.shark.base.util.ServletUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@org.springframework.web.bind.annotation.RestController
@RequestMapping(value = "/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseDataEntity<MemberDtoEntity> login(HttpServletRequest request) {
        HashMap<String, String> parameters = ServletUtil.generateServiceParameters(request,
                MemberDaoEntity.ACCOUNT, MemberDaoEntity.PASSWORD);
        return loginService.request("", parameters);
    }

}
