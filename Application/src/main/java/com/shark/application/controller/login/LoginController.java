package com.shark.application.controller.login;

import com.shark.application.dto.ResponseDataEntity;
import com.shark.application.dto.login.LoginDtoEntity;
import com.shark.application.service.login.LoginService;
import com.shark.application.service.login.RegisterService;
import com.shark.application.util.ServletUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@RestController
@RequestMapping(value = "/login")
@Api(tags = "登入")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @ApiOperation(value = "登入", notes = "", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = LoginService.INPUT_ACCOUNT, value = "帳號", required = true, paramType = "query"),
            @ApiImplicitParam(name = LoginService.INPUT_PASSWORD, value = "密碼", required = true, paramType = "query")
    })
    @PostMapping("login")
    public ResponseDataEntity<LoginDtoEntity> login(HttpServletRequest request) {
        HashMap<String, String> parameters =
                ServletUtil.generateServiceParameters(request, getClass(), "login", false);
        return loginService.request("", parameters);
    }


    @Autowired
    private RegisterService registerService;

    @ApiOperation(value = "註冊", notes = "", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = LoginService.INPUT_ACCOUNT, value = "帳號", required = true, paramType = "query"),
            @ApiImplicitParam(name = RegisterService.INPUT_PASSWORD, value = "密碼", required = true, paramType = "query"),
            @ApiImplicitParam(name = RegisterService.INPUT_NAME, value = "名字", required = true, paramType = "query")
    })
    @PostMapping("/register")
    public ResponseDataEntity<LoginDtoEntity> register(HttpServletRequest request) {
        HashMap<String, String> parameters =
                ServletUtil.generateServiceParameters(request, getClass(), "register", false);
        return registerService.request("", parameters);
    }
}
