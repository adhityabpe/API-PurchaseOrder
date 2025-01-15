package com.purchaseorder.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ApiResponseUtil {

    public static <T> ResponseEntity<ApiResponse<T>> successResponse(T data) {
        ApiResponse<T> response = new ApiResponse<>(HttpStatus.OK.value(), "Success", data);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public static <T> ResponseEntity<ApiResponse<T>> errorResponse(String message, HttpStatus status) {
        ApiResponse<T> response = new ApiResponse<>(status.value(), message, null);
        return new ResponseEntity<>(response, status);
    }

    public static <T> ResponseEntity<ApiResponse<T>> notFoundResponse(String message) {
        ApiResponse<T> response = new ApiResponse<>(HttpStatus.NOT_FOUND.value(), message, null);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    public static <T> ResponseEntity<ApiResponse<T>> badRequestResponse(String message) {
        ApiResponse<T> response = new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), message, null);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
