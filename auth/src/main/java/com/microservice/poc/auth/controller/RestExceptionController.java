package com.microservice.poc.auth.controller;

import com.microservice.poc.auth.utility.errorResponseUtility.ErrorResponse;
import com.microservice.poc.auth.utility.exception.GenericException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


@ControllerAdvice
public class RestExceptionController {

    @ExceptionHandler(Throwable.class)
    public final ResponseEntity<?> handleAllExceptions(GenericException ex, WebRequest request) {

        return buildBadRequestResponseEntity(ex);
    }

    private ResponseEntity<ErrorResponse> buildBadRequestResponseEntity(GenericException exception) {

        ErrorResponse errorResponse = new ErrorResponse(exception.getLocalizedErrors());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
