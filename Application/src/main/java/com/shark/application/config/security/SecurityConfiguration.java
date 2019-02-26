package com.shark.application.config.security;

public class SecurityConfiguration {

    public static final String SECRET = "BaseApplication";
    public static final long JWT_EXPIRATION_TIME = 864000000;
    public static final String TOKEN_PREFIX = "shark_";
    public static final String AUTH_HEADER = "authorization";
    public static final String LOGIN_URL = "/login";
}
