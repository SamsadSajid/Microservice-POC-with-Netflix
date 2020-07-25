package com.microservice.poc.auth.service;


import com.microservice.poc.auth.model.User;

import java.util.Optional;

public interface IJwtTokenService {

    Optional<String> generateAccessToken(int accessTokenLifeTime, User user);
}
