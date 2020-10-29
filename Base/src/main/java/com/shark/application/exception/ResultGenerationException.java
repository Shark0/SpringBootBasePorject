package com.shark.application.exception;

import java.util.Date;

public class ResultGenerationException extends Exception {
    public ResultGenerationException(Exception e) {
        super(e);
    }
}