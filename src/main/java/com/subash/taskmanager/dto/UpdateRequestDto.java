package com.subash.taskmanager.dto;

import com.subash.taskmanager.entity.User;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateRequestDto {

    @NotNull
    private User user;

    @NotNull
    private Long task_id;
}