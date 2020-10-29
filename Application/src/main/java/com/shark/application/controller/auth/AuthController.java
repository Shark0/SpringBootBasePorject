package com.shark.application.controller.auth;

import com.shark.application.controller.auth.pojo.LoginDo;
import com.shark.application.controller.auth.pojo.LoginDto;
import com.shark.application.controller.auth.pojo.RefreshDo;
import com.shark.application.controller.auth.pojo.RegisterDo;
import com.shark.application.controller.pojo.ResponseDto;
import com.shark.application.exception.DataProcessException;
import com.shark.application.exception.ResultGenerationException;
import com.shark.application.service.login.LoginService;
import com.shark.application.service.login.RefreshService;
import com.shark.application.service.login.RegisterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/auth")
@Api(tags = "驗證")
public class AuthController {

    private final LoginService loginService;

    @ApiOperation(value = "登入")
    @PostMapping("login")
    public ResponseDto<LoginDto> login(
            @Valid @RequestBody LoginDo loginDo) throws DataProcessException, ResultGenerationException {
        return loginService.request(null, loginDo);
    }

    private final RegisterService registerService;

    @ApiOperation(value = "註冊")
    @PostMapping("/register")
    public ResponseDto<LoginDto> register(
            @Valid @RequestBody RegisterDo registerDo) throws DataProcessException, ResultGenerationException {
        return registerService.request(null, registerDo);
    }

    private final RefreshService refreshService;

    @ApiOperation(value = "更新Access Token")
    @PostMapping("/refresh")
    public ResponseDto<LoginDto> refresh(
            @Valid @RequestBody RefreshDo refreshDo) throws Exception {
        return refreshService.request(null, refreshDo);
    }

}
