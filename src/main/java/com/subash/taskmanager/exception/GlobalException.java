package com.subash.taskmanager.exception;

import com.subash.taskmanager.dto.ExceptionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalException {

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
}
