package com.shark.application.exception;

import lombok.Data;

@Data
public class WarningException extends IllegalStateException {

    private final String i18nKey;

    public WarningException(String i18nKey) {
        this.i18nKey = i18nKey;
    }
}
