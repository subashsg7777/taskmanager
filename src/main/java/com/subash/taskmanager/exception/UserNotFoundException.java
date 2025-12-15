package com.subash.taskmanager.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserNotFoundException  {
    public UserNotFoundException(String userNotFound) {
        log.error(userNotFound);
    }
}
