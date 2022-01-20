package com.example.ClientService.exception;

import org.springframework.http.HttpStatus;

public class ClientServiceApiExcepion extends RuntimeException{
    private HttpStatus httpStatus;
    private String message;

    public ClientServiceApiExcepion(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public ClientServiceApiExcepion(String message, HttpStatus httpStatus, String message1) {
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
