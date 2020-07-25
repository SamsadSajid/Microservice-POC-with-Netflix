package com.microservice.poc.auth.controller;

import com.microservice.poc.auth.dto.LogInRequestDto;
import com.microservice.poc.auth.dto.LogInResponseDto;
import com.microservice.poc.auth.service.impl.AuthenticationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationServiceImpl authenticationService;

    public AuthenticationController(){};

    @GetMapping(value = "/site-status")
    public HttpStatus HealthCheck(@RequestHeader HttpHeaders httpHeaders) {

        System.out.println(httpHeaders);

        return HttpStatus.ACCEPTED;
    }

    @PostMapping(value = "/login")
    // TODO: Make @Valid work and if the RequestBody is invalid don't let it go to further
    public LogInResponseDto logIn(@Valid @RequestBody LogInRequestDto logInRequest, @RequestHeader HttpHeaders httpHeaders) {

        return authenticationService.logIn(httpHeaders, logInRequest);
    }
}
