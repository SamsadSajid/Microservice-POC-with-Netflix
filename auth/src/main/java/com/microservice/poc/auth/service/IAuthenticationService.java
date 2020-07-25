package com.microservice.poc.auth.service;

import com.microservice.poc.auth.dto.LogInRequestDto;
import com.microservice.poc.auth.dto.LogInResponseDto;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestBody;

public interface IAuthenticationService {

    LogInResponseDto logIn(HttpHeaders httpHeaders, @RequestBody LogInRequestDto logInRequest);
}
