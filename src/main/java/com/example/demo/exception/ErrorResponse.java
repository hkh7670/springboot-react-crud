package com.example.demo.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
    private String errorCode;
    private String message;

    public ErrorResponse(CustomException ex) {
        this.errorCode = ex.getErrorCode();
        this.message = ex.getMessage();
    }

    public ErrorResponse(ErrorCode errorCode) {
        this.errorCode = errorCode.name();
        this.message = errorCode.getMessage();
    }
}
