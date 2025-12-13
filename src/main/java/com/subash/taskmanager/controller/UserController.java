package com.subash.taskmanager.controller;

import com.subash.taskmanager.dto.UserRequestDto;
import com.subash.taskmanager.dto.UserResponseDto;
import com.subash.taskmanager.entity.User;
import com.subash.taskmanager.service.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    @PostMapping("signin")
    public ResponseEntity<UserResponseDto> signin(@Valid  @RequestBody UserRequestDto userRequestDto){
        UserResponseDto userResponseDto = new  UserResponseDto();
            boolean result = userService.signupUsers(userRequestDto);
            if (result) {
                BeanUtils.copyProperties(userResponseDto,userRequestDto);
                userResponseDto.setMessage("Signed In Successfully");
                return ResponseEntity.status(HttpStatus.OK).body(userResponseDto);
            }
            userResponseDto.setMessage("Signed In Failed");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(userResponseDto);
    }
}
