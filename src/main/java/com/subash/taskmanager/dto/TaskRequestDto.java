package com.subash.taskmanager.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jdk.jfr.BooleanFlag;
import lombok.Data;

@Data
public class TaskRequestDto {

//    public enum TaskStatus {
//        PENDING,
//        IN_PROGRESS,
//        COMPLETED
//    }


    @NotBlank
    @Size(min = 1, max = 50)
    public String title;

    @NotBlank
    @Size(min = 1, max = 250)
    public String description;

//    @NotBlank
//    @Size(min = 1, max = 11)
//    public TaskStatus STATUS;

//    @NotBlank
//    public Boolean DELETED;
//
    @NotNull
    public Long user;
}
