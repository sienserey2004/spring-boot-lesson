package com.setec.appcoffeeshop.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ApiResponse<T> {
    private boolean success;
    private int status;
    private String message;
    private T data;
    private LocalDateTime timestamp;
}
