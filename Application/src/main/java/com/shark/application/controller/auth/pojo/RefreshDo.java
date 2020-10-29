package com.shark.application.controller.auth.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
public class RefreshDo implements Serializable {
    @NotEmpty
    @ApiModelProperty(value = "access token", required = true)
    private String accessToken;

    @NotEmpty
    @ApiModelProperty(value = "refresh token", required = true)
    private String refreshToken;
}
