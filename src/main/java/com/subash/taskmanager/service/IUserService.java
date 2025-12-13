package com.subash.taskmanager.service;

import com.subash.taskmanager.dto.UserRequestDto;

public interface IUserService {

    boolean signupUsers(UserRequestDto userRequestDto);
}
