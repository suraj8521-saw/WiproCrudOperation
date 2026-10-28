package com.example.demo.dto;

import java.time.LocalDateTime;
import java.util.Map;

public class ValidExceptionResponceDto {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String path;
    private String message;
    private Map<String,String> fieldsError;

    public ValidExceptionResponceDto(LocalDateTime timestamp, int status, String error, String path, String message, Map<String, String> fieldsError) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.path = path;
        this.message = message;
        this.fieldsError = fieldsError;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, String> getFieldsError() {
        return fieldsError;
    }

    public void setFieldsError(Map<String, String> fieldsError) {
        this.fieldsError = fieldsError;
    }
}
