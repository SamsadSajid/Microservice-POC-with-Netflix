package com.microservice.poc.auth.utility;

import java.util.concurrent.TimeUnit;

public class Utility {

    public static final String ACCESS_TOKEN_PROVIDER = "https://inspiringbangladesh.com";
    public static final String TOKEN_USER_ID = "https://inspiringbangladesh.com";
    public static final String ACCESS_TOKEN_SUBJECT = "InspiringBangladesh";
    public static final int ACCESS_TOKEN_LIFETIME = 1;
    public static final long ONE_DAY_IN_MILLIS = 1000;
    public static final TimeUnit ACCESS_TOKEN_EXPIRATION_TIME_UNIT = TimeUnit.MINUTES;

    public static final int USER_NAME_MIN_LENGTH = 3;
    public static final int USER_NAME_MAX_LENGTH = 50;

    public static final int USER_PASSWORD_MIN_LENGTH = 4;
    public static final int USER_PASSWORD_MAX_LENGTH = 255;

    public static final String INVALID_REQUEST = "INVALID_REQUEST";

    public static final String ENGLISH = "en";
    public static final String BANGLA = "bn";

    // TODO: Change the hash for more security
    public static final String REDIS_HASH = "INSPIRINGBANGLADESH";

    public static final String JWTSECRET = "BojackHorseman";
}
