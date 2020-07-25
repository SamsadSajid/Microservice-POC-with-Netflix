package com.microservice.poc.auth.utility.exception;

import com.microservice.poc.auth.utility.errorResponseUtility.LocalizedError;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class GenericException extends RuntimeException {
    private List<LocalizedError> localizedErrors;

    public GenericException(List<LocalizedError> localizedErrors) {
        super(localizedErrors.toString());
        this.localizedErrors = localizedErrors;
    }

    @Override
    public String getMessage() {
        return localizedErrors.toString();
    }
}
