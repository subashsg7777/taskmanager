package com.subash.taskmanager.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ErrorResponseDto {

    private String message;
    private HttpStatus status;
    private String path;
}
