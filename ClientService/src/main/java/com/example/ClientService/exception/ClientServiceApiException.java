package com.example.ClientService.exception;

import org.springframework.http.HttpStatus;

public class ClientServiceApiException extends RuntimeException{
    private HttpStatus httpStatus;
    private String message;

    public ClientServiceApiException(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public ClientServiceApiException(String message, HttpStatus httpStatus, String message1) {
        super(message);
        this.httpStatus = httpStatus;
        this.message = message1;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
