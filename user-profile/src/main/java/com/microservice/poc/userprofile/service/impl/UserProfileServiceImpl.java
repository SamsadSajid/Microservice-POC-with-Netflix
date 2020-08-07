package com.microservice.poc.userprofile.service.impl;

import com.microservice.poc.userprofile.model.User;
import com.microservice.poc.userprofile.model.UserProfile;
import com.microservice.poc.userprofile.config.s3.AmazonClient;
import com.microservice.poc.userprofile.repository.UserRepository;
import com.microservice.poc.userprofile.dto.UserProfileRequestDto;
import com.microservice.poc.userprofile.dto.UserProfileResponseDto;
import com.microservice.poc.userprofile.service.IUserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.File;
import java.util.Optional;

@Service
public class UserProfileServiceImpl implements IUserProfileService {

//    @Autowired
    private final UserRepository userRepository;

//    @Autowired
//    private UserProfileRepository userProfileRepository;

    public UserProfileServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    private AmazonClient amazonClient;

    @Override
    public UserProfileResponseDto getUserProfile(String user) {

        Optional<User> users = userRepository.findByUserName(user);

        var rsp = UserProfileResponseDto.builder();
        users.ifPresentOrElse(
                user1 -> {
                    UserProfile userProfile = user1.getUserProfile();

                    rsp.firstName(userProfile.getFirstName());
                    rsp.lastName(userProfile.getLastName());
                    rsp.email(userProfile.getEmail());
                    rsp.age(userProfile.getAge());
                    rsp.phone(userProfile.getPhone());
                    rsp.dateOfBirth(userProfile.getDateOfBirth());
                    rsp.sex(userProfile.getSex());
                    rsp.imagePath(userProfile.getImagePath());
                },
                () -> {
                    System.out.println("baal");
                }
        );

        System.out.println(rsp);

        return rsp.build();
    }

    @Override
    public UserProfileResponseDto saveUserProfile(UserProfileRequestDto userProfileRequestDto) {

        var response = UserProfileResponseDto.builder();

        Optional<User> queryUser = userRepository.findByUserName(userProfileRequestDto.getUserName());

        queryUser.ifPresentOrElse(
                user -> {
                    System.out.println(getImagePathFromImageFile(userProfileRequestDto.getImageFile()));
                    UserProfile userProfile = UserProfile.builder()
                            .firstName(userProfileRequestDto.getFirstName())
                            .lastName(userProfileRequestDto.getLastName())
                            .email(userProfileRequestDto.getEmail())
                            .age(userProfileRequestDto.getAge())
                            .phone(userProfileRequestDto.getPhone())
                            .dateOfBirth(userProfileRequestDto.getDateOfBirth())
                            .sex(userProfileRequestDto.getSex())
                            .imagePath(getImagePathFromImageFile(userProfileRequestDto.getImageFile()))
                            .build();

                    user.setUserProfile(userProfile);
                    userRepository.save(user);

                    response.imagePath(user.getUserProfile().getImagePath());
                },
                () -> {
                    System.out.println("Error");
                }
        );

        return response.build();
    }

    private String getImagePathFromImageFile(MultipartFile imageFile) {


        return amazonClient.uploadFile(imageFile);
    }

}
