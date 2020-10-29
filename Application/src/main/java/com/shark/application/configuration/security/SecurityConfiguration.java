package com.shark.application.configuration.security;

public class SecurityConfiguration {

    public static final String ACCESS_SECRET = "ACCESS_SECRET";
    public static final long ACCESS_EXPIRATION_TIME = 86400000;
    public static final String ACCESS_PREFIX = "access_";
    public static final String ACCESS_HEADER = "access_token";

    public static final String REFRESH_SECRET = "REFRESH_SECRET";
    public static final long REFRESH_EXPIRATION_TIME = 864000000;
    public static final String REFRESH_PREFIX = "refresh_";

    public static final String AUTH_URL = "/auth";
}
