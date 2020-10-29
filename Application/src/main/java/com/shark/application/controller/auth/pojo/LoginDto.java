package com.shark.application.controller.auth.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginDto implements Serializable {

    @ApiModelProperty(value = "accessToken")
    private String accessToken;

    private String refreshToken;

    @ApiModelProperty(value = "使用者名稱")
    private String accountName;
}
