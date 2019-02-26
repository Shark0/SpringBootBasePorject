package com.shark.application.controller.account;

import com.shark.application.dto.ResponseEntity;
import com.shark.application.service.account.UpdateAccountService;
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
import java.security.Principal;
import java.util.HashMap;

@RestController
@RequestMapping(value = "/account")
@Api(tags = "帳號")
public class AccountController {

    @Autowired
    private UpdateAccountService updateAccountService;

    @ApiOperation(value = "更新帳號", notes = "", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = UpdateAccountService.INPUT_NAME, value = "名字", required = true, paramType = "query")
    })
    @PostMapping("/updateAccount")
    public ResponseEntity updateAccount(HttpServletRequest request) {
        HashMap<String, String> parameters = ServletUtil.generateServiceParameters(request, UpdateAccountService.INPUT_NAME);
        Principal principal = request.getUserPrincipal();
        String memberId = principal.getName();
        return updateAccountService.request(memberId, parameters);
    }

}
