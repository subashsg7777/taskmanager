package com.subash.taskmanager.controller;

import com.subash.taskmanager.dto.HealthResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/health")
@RequiredArgsConstructor
public class HealthController {

    @GetMapping
    public ResponseEntity<HealthResponseDto> health(HealthResponseDto healthResponseDto) {
        healthResponseDto.setStatus("OK");
        healthResponseDto.setService("UP");
        return  ResponseEntity.status(HttpStatus.OK).body(healthResponseDto);
    }
}
