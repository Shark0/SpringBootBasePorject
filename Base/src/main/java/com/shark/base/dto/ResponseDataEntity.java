package com.shark.base.dto;

import io.swagger.annotations.ApiModelProperty;

public class ResponseDataEntity<Data> extends ResponseEntity {

    @ApiModelProperty(value = "回傳資料")
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
