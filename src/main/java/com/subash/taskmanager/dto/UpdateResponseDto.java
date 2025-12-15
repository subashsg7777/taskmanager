package com.subash.taskmanager.dto;

import com.subash.taskmanager.entity.User;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UpdateResponseDto {
    private String message;
    private User user;
    private Long task_id;
}
