package com.shark.base.security;

public class Config {

    public static final String SECRET = "SecretKeyToGenJWTs";
    public static final long EXPIRATION_TIME = 864000000;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER = "Authorization";
    public static final String REGISTER_URL = "/member/register";
    public static final String LOGIN_URL = "/login";
}
