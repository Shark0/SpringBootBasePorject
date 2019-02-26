package com.shark.application.dto.login;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginDtoEntity implements Serializable {

    @ApiModelProperty(value = "Json Web Token")
    private String jwt;
    @ApiModelProperty(value = "使用者 ID")
    private long accountId;
    @ApiModelProperty(value = "使用者名稱")
    private String accountName;

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
}
