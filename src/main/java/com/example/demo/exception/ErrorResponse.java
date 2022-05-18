package com.example.demo.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
    private int status;
    private String message;
    private String code;

    public ErrorResponse(CustomException ex) {
        ErrorCode errorCode = ex.getErrorCode();
        String exceptionMsg = ex.getExceptionMsg();
        this.status = errorCode.getStatus();
        this.message = errorCode.getMessage() + "(" + exceptionMsg + ")";
        this.code = errorCode.getErrorCode();
    }

    public ErrorResponse(ErrorCode errorCode) {
        this.status = errorCode.getStatus();
        this.message = errorCode.getMessage();
        this.code = errorCode.getErrorCode();
    }
}
