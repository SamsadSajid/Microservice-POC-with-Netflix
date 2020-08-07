package com.microservice.poc.userprofile.service;

import com.microservice.poc.userprofile.model.User;
import com.microservice.poc.userprofile.dto.UserProfileRequestDto;
import com.microservice.poc.userprofile.dto.UserProfileResponseDto;

public interface IUserProfileService {

    UserProfileResponseDto getUserProfile(String user);

    UserProfileResponseDto saveUserProfile(UserProfileRequestDto userProfileRequestDto);
}
