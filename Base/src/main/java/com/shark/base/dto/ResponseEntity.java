package com.shark.base.dto;

import io.swagger.annotations.ApiModelProperty;

public class ResponseEntity {

    @ApiModelProperty(value = "回傳碼")
    private int returnCode;

    @ApiModelProperty(value = "回傳訊息")
    private String returnMessage;

    public int getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(int returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;
    }
}


