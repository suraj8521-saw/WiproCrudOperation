package com.example.demo.exceptionHandler;

import com.example.demo.dto.ExceptionResponseDto;
import com.example.demo.dto.ValidExceptionResponceDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidExceptionResponceDto> methodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException, HttpServletRequest httpServletRequest){
        Map<String,String> fieldsError=new HashMap<>();
        methodArgumentNotValidException
                .getBindingResult()
                .getFieldErrors()
                .forEach(error->fieldsError.put(error.getField(),error.getDefaultMessage()));

        ValidExceptionResponceDto validExceptionResponceDto=new ValidExceptionResponceDto(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                httpServletRequest.getRequestURI(),
                "Validation Failed!",
                fieldsError

        );
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(validExceptionResponceDto);
    }
    @ExceptionHandler(StudentAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponseDto> handleStudentAlreadyExistsException(StudentAlreadyExistsException exception, HttpServletRequest httpServletRequest){
        ExceptionResponseDto exceptionResponseDto=new ExceptionResponseDto(
                LocalDateTime.now(),
                HttpStatus.CONFLICT.value(),
                HttpStatus.CONFLICT.getReasonPhrase(),
                httpServletRequest.getRequestURI(),
                exception.getMessage()

        );
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(exceptionResponseDto);
    }
    @ExceptionHandler(StudentServiceException.class)
    public ResponseEntity<ExceptionResponseDto> handleStudentServiceException(StudentServiceException exception, HttpServletRequest httpServletRequest){
        ExceptionResponseDto exceptionResponseDto=new ExceptionResponseDto(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                httpServletRequest.getRequestURI(),
                exception.getMessage()

        );
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(exceptionResponseDto);
    }
    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<ExceptionResponseDto> handleStudentNotFoundException(StudentNotFoundException exception, HttpServletRequest httpServletRequest){
        ExceptionResponseDto exceptionResponseDto=new ExceptionResponseDto(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                httpServletRequest.getRequestURI(),
                exception.getMessage()

        );
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exceptionResponseDto);
    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionResponseDto> handleGlobalException(RuntimeException runtimeException, HttpServletRequest httpServletRequest){
        ExceptionResponseDto exceptionResponseDto=new ExceptionResponseDto(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                httpServletRequest.getRequestURI(),
                runtimeException.getMessage()

        );
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(exceptionResponseDto);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponseDto> handleException(Exception exception, HttpServletRequest httpServletRequest){
        ExceptionResponseDto exceptionResponseDto=new ExceptionResponseDto(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                httpServletRequest.getRequestURI(),
                exception.getMessage()

        );
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(exceptionResponseDto);
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponseDto> handleResourceNotFoundException(ResourceNotFoundException resourceNotFoundException, HttpServletRequest httpServletRequest){
        ExceptionResponseDto exceptionResponseDto=new ExceptionResponseDto(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                httpServletRequest.getRequestURI(),
                resourceNotFoundException.getMessage()

        );
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exceptionResponseDto);
    }
    @ExceptionHandler(DuplicateResourceFound.class)
    public ResponseEntity<ExceptionResponseDto> handleResourceNotFoundException(DuplicateResourceFound duplicateResourceFound, HttpServletRequest httpServletRequest){
        ExceptionResponseDto exceptionResponseDto=new ExceptionResponseDto(
                LocalDateTime.now(),
                HttpStatus.CONFLICT.value(),
                HttpStatus.CONFLICT.getReasonPhrase(),
                httpServletRequest.getRequestURI(),
                duplicateResourceFound.getMessage()

        );
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(exceptionResponseDto);
    }

}
