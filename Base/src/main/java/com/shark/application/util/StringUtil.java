package com.shark.application.util;

public class StringUtil {

    public static boolean isEmpty(String parameter) {
        return parameter == null || (parameter.trim().length() == 0);
    }

    public static boolean isEmail(String email) {
        String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        return email.matches(regex);
    }
}
