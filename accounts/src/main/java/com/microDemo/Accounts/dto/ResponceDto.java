package com.microDemo.Accounts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class ResponceDto {
    private String statusCode;
    private String statusMessage;
}
