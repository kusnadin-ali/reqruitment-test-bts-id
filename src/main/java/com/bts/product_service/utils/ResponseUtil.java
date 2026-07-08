package com.bts.product_service.utils;

import com.bts.product_service.dto.CommonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtil {

    public static ResponseEntity<CommonResponse> success(String message) {
        CommonResponse response = new CommonResponse();
        response.setMessage(message);
        response.setCode("00");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    public static ResponseEntity<CommonResponse> success(String message, Object data) {
        CommonResponse response = new CommonResponse();
        response.setMessage(message);
        response.setCode("00");
        response.setData(data);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    public static ResponseEntity<CommonResponse> failed(String message) {
        CommonResponse response = new CommonResponse();
        response.setMessage(message);
        response.setCode("01");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    public static ResponseEntity<CommonResponse> failedWithStatus(HttpStatus status, String message) {
        CommonResponse response = new CommonResponse();
        response.setMessage(message);
        response.setCode("01");
        return ResponseEntity.status(status).body(response);
    }

    public static ResponseEntity<CommonResponse> failedWithStatus(HttpStatus status, String message, Object data) {
        CommonResponse response = new CommonResponse();
        response.setMessage(message);
        response.setCode("01");
        response.setData(data);
        return ResponseEntity.status(status).body(response);
    }
}
