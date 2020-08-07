package com.microservice.poc.auth.service.impl;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.microservice.poc.common.model.User;
import com.microservice.poc.auth.service.IJwtTokenService;
import com.microservice.poc.auth.utility.Utility;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;


@Service
public class JwtTokeServiceImpl implements IJwtTokenService {

    @Override
    public Optional<String> generateAccessToken(int accessTokenLifeTime, User user) {

        return Optional.of(JWT.create()
                .withIssuer(Utility.ACCESS_TOKEN_PROVIDER)
                .withAudience(user.getUserName())
                .withClaim(Utility.TOKEN_USER_ID, user.getId().toString())
                .withExpiresAt(getAccessTokenExpiryTime(accessTokenLifeTime))
                .withIssuedAt(new Date())
                .withSubject(Utility.ACCESS_TOKEN_SUBJECT)
                .sign(getSigningAlgorithm()));
    }

    private Date getAccessTokenExpiryTime(int accessTokenExpirationTimeInDays) {

        return new Date(System.currentTimeMillis() + Utility.ONE_DAY_IN_MILLIS * accessTokenExpirationTimeInDays);
    }

    private Algorithm getSigningAlgorithm(){

        return Algorithm.HMAC512(Utility.JWTSECRET);
    }
}
