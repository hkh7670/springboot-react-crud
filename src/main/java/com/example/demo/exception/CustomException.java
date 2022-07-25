package com.example.demo.exception;


import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomException extends RuntimeException {
    private static final long serialVersionUID = -7806029002430564887L;
    private final HttpStatus status;
    private final String errorCode;
    private final String message;

    public CustomException(ErrorCode errorCode, String ...additionalMsg) {
        this.status = errorCode.getStatus();
        this.errorCode = errorCode.name();
        this.message = errorCode.getMessage() + " ==> " + String.join("/", additionalMsg);
    }

    public CustomException(ErrorCode errorCode) {
        this.status = errorCode.getStatus();
        this.errorCode = errorCode.name();
        this.message = errorCode.getMessage();
    }

    @Override
    public String toString() {
        return "CustomException{" +
                "status=" + status +
                ", errorCode='" + errorCode + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
