package com.microservice.poc.auth.utility.errorResponseUtility;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@AllArgsConstructor
@Getter
@Setter
@ToString
public class LocalizedError {
    private String language;
    private String value;
}
