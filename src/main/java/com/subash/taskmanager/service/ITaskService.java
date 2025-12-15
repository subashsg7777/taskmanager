package com.subash.taskmanager.service;

import com.subash.taskmanager.dto.TaskRequestDto;
import com.subash.taskmanager.dto.UpdateRequestDto;
import com.subash.taskmanager.entity.Task;
import com.subash.taskmanager.entity.User;

import java.util.List;

public interface ITaskService {

    boolean addTasks(TaskRequestDto taskRequestDto);
    List<Task> GetAllTasks(Long id);
    boolean updateTasks(Long id, User user);
    boolean deleteTasks(Long id, User userId);
}
