package com.shark.application.controller.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDto<Result> implements Serializable {

    @ApiModelProperty(value = "resultData")
    private Result resultData;

    @ApiModelProperty(value = "回傳碼")
    private int returnCode;

    @ApiModelProperty(value = "回傳訊息")
    private String returnMessage;
}


