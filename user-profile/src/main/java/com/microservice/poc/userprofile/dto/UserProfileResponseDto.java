package com.microservice.poc.userprofile.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserProfileResponseDto {

    private String firstName;
    private String lastName;
    private String email;
    private String age;
    private String sex;
    private String imagePath;
    private String dateOfBirth;
    private String phone;
}
