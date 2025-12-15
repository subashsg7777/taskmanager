package com.subash.taskmanager.controller;

import com.subash.taskmanager.dto.AllTaskResponseDto;
import com.subash.taskmanager.dto.TaskRequestDto;
import com.subash.taskmanager.dto.UpdateRequestDto;
import com.subash.taskmanager.dto.UpdateResponseDto;
import com.subash.taskmanager.entity.Task;
import com.subash.taskmanager.entity.User;
import com.subash.taskmanager.service.ITaskService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.json.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.jackson.autoconfigure.JacksonProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private static final Logger log = LoggerFactory.getLogger(TaskController.class);
    private final ITaskService taskService;

    @PostMapping
    public ResponseEntity<TaskRequestDto> AddNewTasks(@Valid @RequestBody TaskRequestDto taskRequestDto) {
        Boolean result = taskService.addTasks(taskRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(taskRequestDto);
    }

    @GetMapping
    public ResponseEntity<List<AllTaskResponseDto>>  GetAllTasks(@RequestParam Long id) {
        AllTaskResponseDto allTaskResponseDto = new AllTaskResponseDto();
        List<Task> Results = taskService.GetAllTasks(id);
        log.info("Results: {}", Results);

//        Mapping Results to Response Dto
        List<AllTaskResponseDto>ConvertedResults = Results.stream().map(result -> {

            AllTaskResponseDto dto = new AllTaskResponseDto();
            BeanUtils.copyProperties(result, dto);
            dto.setUser(result.getUser().getId());
            return dto;
        }).toList();
        return ResponseEntity.status(HttpStatus.OK).body(ConvertedResults);
    }
    
    @PutMapping
    public ResponseEntity<UpdateResponseDto> updateTask(@RequestParam Long id, @RequestParam User user){

        boolean result = taskService.updateTasks(id,user);
        UpdateResponseDto updateResponseDto = new UpdateResponseDto();
        if(result){
            updateResponseDto.setTask_id(id);
            updateResponseDto.setUser(user);
            updateResponseDto.setMessage("Updated Successfully");
            return ResponseEntity.status(HttpStatus.OK).body(updateResponseDto);
        }

        else{
            updateResponseDto.setMessage("Updation Failed");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(updateResponseDto);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteTask(@RequestParam Long id, @RequestParam User userId) {

        boolean result = taskService.deleteTasks(id,userId);
        if(result){
            Map<String,Object> response = new HashMap<>();
            response.put("message","Delete Successful");
            response.put("id",id);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        else{
            Map<String,Object> response = new HashMap<>();
            response.put("message","Delete Failed");
            response.put("id",id);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}