package com.microservice.poc.auth.utility.errorResponseUtility;


import com.microservice.poc.auth.utility.ErrorEnum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class LocalizedErrorMessageMap {

    public static final List<String> badCredentials = Arrays.asList(
            ErrorMessageEn.BAD_CREDENTIALS, ErrorMessageBn.BAD_CREDENTIALS);

    public static final List<String> userDoesNotExist = Arrays.asList(
            ErrorMessageEn.BAD_CREDENTIALS, ErrorMessageBn.BAD_CREDENTIALS);

    private static final HashMap<ErrorEnum, List<String>> localizedErrorMessagesMap = new HashMap<>(){{
            put(ErrorEnum.CREDENTIALS_MISMATCH, badCredentials);
            put(ErrorEnum.USER_DOES_NOT_EXISTS, userDoesNotExist);
    }};

    public static List<String> getLocalizedErrorMessages (ErrorEnum errorEnum) {

        return localizedErrorMessagesMap.get(errorEnum);
    }
}
