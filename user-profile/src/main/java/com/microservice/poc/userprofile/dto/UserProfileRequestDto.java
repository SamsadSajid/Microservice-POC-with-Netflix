package com.microservice.poc.userprofile.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserProfileRequestDto {

    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String age;
    private String sex;
    private MultipartFile imageFile;
    private String dateOfBirth;
    private String phone;
}
