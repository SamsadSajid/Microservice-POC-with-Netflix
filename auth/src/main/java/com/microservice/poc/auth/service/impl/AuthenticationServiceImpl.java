package com.microservice.poc.auth.service.impl;

import com.microservice.poc.auth.dto.LogInRequestDto;
import com.microservice.poc.auth.dto.LogInResponseDto;
import com.microservice.poc.auth.model.User;
import com.microservice.poc.auth.repository.DataRepository;
import com.microservice.poc.auth.repository.RedisRepository;
import com.microservice.poc.auth.service.IAuthenticationService;
import com.microservice.poc.auth.service.IJwtTokenService;
import com.microservice.poc.auth.utility.ErrorEnum;
import com.microservice.poc.auth.utility.Utility;
import com.microservice.poc.auth.utility.errorResponseUtility.LocalizedErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Optional;


@Service
public class AuthenticationServiceImpl implements IAuthenticationService {

    @Autowired
    private DataRepository dataRepository;

    @Autowired
    private IJwtTokenService IJwtTokenService;

    @Autowired
    private RedisRepository redisRepository;

    @Override
    public LogInResponseDto logIn(HttpHeaders httpHeaders, @RequestBody LogInRequestDto logInRequest) {

        var response = LogInResponseDto.builder();
        Optional<User> queryUser = dataRepository.findByUserName(logInRequest.getUserName());

        queryUser.ifPresentOrElse(
                user -> {
                    // Do not do this in production code.
                    // Make sure to store encrypted password in the db.
                    String plainPassword = user.getPassword();

                    if (plainPassword.equals(logInRequest.getPassword())) {

                        String accessToken = generateAccessToken(user);

                        setUserLastLoggedInTime(user);
                        saveUserAccessTokenInRedisWithTTL(user, accessToken);

                        response
                                .message("SUCCESS")
                                .userName(user.getUserName())
                                .accessToken(accessToken)
                                .build();

                    } else throw LocalizedErrorResponse.generateLocalizedErrorMessage(ErrorEnum.CREDENTIALS_MISMATCH);
                },
                () -> {
                    throw LocalizedErrorResponse.generateLocalizedErrorMessage(ErrorEnum.USER_DOES_NOT_EXISTS);
                }
        );

        return response.build();
    }

    private void setUserLastLoggedInTime(@NotNull User user) {

        user.setLastLoggedIn(LocalDateTime.now());
        dataRepository.save(user);
    }

    private String generateAccessToken(@NotNull User user) {

        return IJwtTokenService
                .generateAccessToken(Utility.ACCESS_TOKEN_LIFETIME, user)
                .orElseThrow(RuntimeException::new);
    }

    private void saveUserAccessTokenInRedisWithTTL(@NotNull User user, @NotNull String accessToken) {

        redisRepository.save(Utility.REDIS_HASH, user.getId().toString(), accessToken);
        redisRepository.setTTL(Utility.REDIS_HASH, Utility.ACCESS_TOKEN_LIFETIME, Utility.ACCESS_TOKEN_EXPIRATION_TIME_UNIT);
    }
}
