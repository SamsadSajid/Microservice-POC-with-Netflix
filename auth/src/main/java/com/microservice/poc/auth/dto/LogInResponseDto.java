package com.microservice.poc.auth.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class LogInResponseDto {

    private String message;
    private String userName;
    private String accessToken;
}
