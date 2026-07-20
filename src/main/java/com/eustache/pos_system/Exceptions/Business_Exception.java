package com.eustache.pos_system.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class Business_Exception extends RuntimeException{
    public Business_Exception(String message) {
        super(message);
    }
}
