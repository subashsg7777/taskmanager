package com.subash.taskmanager.service.implementation;

import com.subash.taskmanager.dto.UserRequestDto;
import com.subash.taskmanager.entity.User;
import com.subash.taskmanager.repository.UserRepo;
import com.subash.taskmanager.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepo userRepo;
    @Override
    public boolean signupUsers(UserRequestDto userRequestDto) {
                User user = new User();
                BeanUtils.copyProperties(userRequestDto,user);
                userRepo.save(user);
                return true;
    }
}
