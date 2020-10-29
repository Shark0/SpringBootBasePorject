package com.shark.application.controller.account.pojo;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AccountSearchDio {
    private Integer pageNumber;
    private String keyword;
}
