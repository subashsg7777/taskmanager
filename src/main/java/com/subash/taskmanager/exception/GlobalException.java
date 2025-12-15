package com.subash.taskmanager.exception;

import com.subash.taskmanager.dto.ErrorResponseDto;
import com.subash.taskmanager.dto.ExceptionDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalException {

    private static final Logger log = LoggerFactory.getLogger(GlobalException.class);

    public ResponseEntity<ExceptionDto> InValidInput(MethodArgumentNotValidException methodArgumentNotValidException,
                                                     WebRequest webRequest) {
            ExceptionDto exceptionDto = new ExceptionDto();
            exceptionDto.setMessage(methodArgumentNotValidException.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionDto);
    }

    public ResponseEntity<ExceptionDto> DuplicateEmail(DataIntegrityViolationException dataIntegrityViolationException,
                                            WebRequest webRequest) {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage(dataIntegrityViolationException.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionDto);
    }

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> NoTaskFound(TaskNotFoundException taskNotFoundException, WebRequest webRequest) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto();
        errorResponseDto.setMessage("Task not found");
        errorResponseDto.setStatus(HttpStatus.NOT_FOUND);
        errorResponseDto.setPath(webRequest.getDescription(false));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponseDto);
    }

    @ExceptionHandler(InternalServerException.class)
    public ResponseEntity<ErrorResponseDto> InternalErrors(InternalServerException internalServerException, WebRequest webRequest) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto();
        errorResponseDto.setMessage("Can't Perform Required Action Please Try Again");
        errorResponseDto.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        errorResponseDto.setPath(webRequest.getDescription(false));

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponseDto);
    }

    @ExceptionHandler(InvalidTaskUpdation.class)
    public ResponseEntity<ErrorResponseDto> invalidTaskUpdationException(InvalidTaskUpdation invalidTaskUpdation, WebRequest webRequest) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto();
        errorResponseDto.setMessage("Invalid Task Updation, Please Try Check Whether You Are Updating A Already Completed Task");
        errorResponseDto.setStatus(HttpStatus.BAD_REQUEST);
        errorResponseDto.setPath(webRequest.getDescription(false));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseDto);
    }
}
