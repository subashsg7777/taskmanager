package com.subash.taskmanager.service.implementation;

import com.subash.taskmanager.dto.ExceptionDto;
import com.subash.taskmanager.dto.TaskRequestDto;
import com.subash.taskmanager.dto.UpdateRequestDto;
import com.subash.taskmanager.entity.Task;
import com.subash.taskmanager.entity.User;
import com.subash.taskmanager.exception.InternalServerException;
import com.subash.taskmanager.exception.InvalidTaskUpdation;
import com.subash.taskmanager.exception.TaskNotFoundException;
import com.subash.taskmanager.repository.TaskRepo;
import com.subash.taskmanager.repository.UserRepo;
import com.subash.taskmanager.service.ITaskService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.subash.taskmanager.dto.AllTaskResponseDto.TaskStatus.*;

@Service
@RequiredArgsConstructor
public class TaskService implements ITaskService {

    private static final Logger log = LoggerFactory.getLogger(TaskService.class);
    private final TaskRepo taskRepo;
    private final UserRepo userRepo;

    @Override
    public boolean addTasks(TaskRequestDto taskRequestDto) {
        try{
            User user = userRepo.findById(taskRequestDto.getUser())
                    .orElseThrow();

            Task task = new Task();
            task.setStatus(PENDING);
            task.setDeleted(false);
            task.setCreatedAt(LocalDateTime.now());
            task.setUpdatedAt(LocalDateTime.now());
            log.info("tasks : {}", taskRequestDto);
            task.setUser(user);
            BeanUtils.copyProperties(taskRequestDto, task);
            taskRepo.save(task);
            return true;
        }

        catch (Exception e){
            throw new InternalServerException();
        }
    }

    @Override
    public List<Task> GetAllTasks(Long user) {
        List<Task> Result = taskRepo.findByUserIdAndDeleted(user,false);
        if (Result.isEmpty()) {
            throw new TaskNotFoundException("Task Not Found");
        }
        return Result;
    }

    @Override
    public boolean updateTasks(Long id, User user) {
        try{
            Task task = taskRepo.findByIdAndUser(id, user);
            var status = task.getStatus();
            if(!status.equals(COMPLETED)){
                if (status == PENDING) {
                    task.setStatus(IN_PROGRESS);
                }
                else if (status == IN_PROGRESS) {
                    task.setStatus(COMPLETED);
                }

                task.setUpdatedAt(LocalDateTime.now());
                taskRepo.save(task);
                return true;
            }
            else{
                throw new InvalidTaskUpdation();
            }
        }

        catch (InvalidTaskUpdation e) {
            throw e;
        }
        catch (Exception e){
            throw new InternalServerException();
        }
    }

    @Override
    public boolean deleteTasks(Long id,  User userId) {
        try{
            Task task = taskRepo.findByIdAndUser(id,userId);
            if(task.isDeleted()){
                throw new InvalidTaskUpdation();
            }
            task.setDeleted(true);
            task.setUpdatedAt(LocalDateTime.now());
            taskRepo.save(task);
            return true;
        } catch (Exception e) {
            throw new InternalServerException();
        }
    }
}
