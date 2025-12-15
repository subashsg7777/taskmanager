package com.subash.taskmanager.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TaskNotFoundException extends RuntimeException{
    private static final Logger log = LoggerFactory.getLogger(TaskNotFoundException.class);

    public TaskNotFoundException(String taskNotFound) {
        return;
    }
}
