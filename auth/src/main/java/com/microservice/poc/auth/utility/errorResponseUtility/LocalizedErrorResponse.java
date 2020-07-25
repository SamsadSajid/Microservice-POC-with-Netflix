package com.microservice.poc.auth.utility.errorResponseUtility;


import com.microservice.poc.auth.utility.ErrorEnum;
import com.microservice.poc.auth.utility.Utility;
import com.microservice.poc.auth.utility.exception.GenericException;

import java.util.ArrayList;
import java.util.List;


public class LocalizedErrorResponse {

    public static GenericException generateLocalizedErrorMessage(ErrorEnum errorEnum) {

        List<LocalizedError> errorList = new ArrayList<>();

        List<String> localizedErrorMessages = LocalizedErrorMessageMap.getLocalizedErrorMessages(errorEnum);

        errorList.add(new LocalizedError(Utility.ENGLISH, localizedErrorMessages.get(0)));
        errorList.add(new LocalizedError(Utility.BANGLA, localizedErrorMessages.get(1)));

        return new GenericException(errorList);
    }
}
