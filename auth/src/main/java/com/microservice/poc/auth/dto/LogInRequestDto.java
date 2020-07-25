package com.microservice.poc.auth.dto;

import com.microservice.poc.auth.utility.Utility;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@ToString
public class LogInRequestDto implements Serializable {

    @NotNull(message = Utility.INVALID_REQUEST)
    @Size(min = Utility.USER_NAME_MIN_LENGTH, max = Utility.USER_NAME_MAX_LENGTH)
    private String userName;

    @NotNull(message = Utility.INVALID_REQUEST)
    @Size(min = Utility.USER_PASSWORD_MIN_LENGTH, max = Utility.USER_PASSWORD_MAX_LENGTH)
    private String password;
}
