package com.shark.application.controller.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NonNull;

import java.io.Serializable;

@Data
public class AuthAccountDo implements Serializable {

    @JsonProperty("id")
    @SerializedName("id")
    private Long id;

    @JsonProperty("account")
    @SerializedName("account")
    private String account;

    @JsonProperty("name")
    @SerializedName("name")
    private String name;
}
