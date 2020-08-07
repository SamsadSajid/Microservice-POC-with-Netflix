package com.microservice.poc.userprofile.controller;

import com.microservice.poc.common.model.User;
import com.microservice.poc.userprofile.dto.UserProfileRequestDto;
import com.microservice.poc.userprofile.dto.UserProfileResponseDto;
import com.microservice.poc.userprofile.service.impl.UserProfileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.http.HttpHeaders;

@RestController
public class UserProfileController {

    @Autowired
    private UserProfileServiceImpl userProfileService;

    @PostMapping(value = "/save/profile")
    public UserProfileResponseDto saveUserProfile(@Valid UserProfileRequestDto userProfileRequest) {

        System.out.println(userProfileRequest);

        return userProfileService.saveUserProfile(userProfileRequest);
    }

    @GetMapping(value = "/get/profile")
    public UserProfileResponseDto getUserProfile(@Valid @RequestBody String userName) {

        System.out.println(userName);

        return userProfileService.getUserProfile(userName);
    }
}
