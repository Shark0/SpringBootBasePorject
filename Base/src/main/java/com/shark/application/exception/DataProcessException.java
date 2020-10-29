package com.shark.application.exception;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
public class DataProcessException extends Exception {

    public DataProcessException(Exception e) {
        super(e);
    }
}
