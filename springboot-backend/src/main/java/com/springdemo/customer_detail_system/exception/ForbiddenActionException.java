package com.springdemo.customer_detail_system.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class ForbiddenActionException extends RuntimeException{

    // Runtime exception just needs this, I guess :/
    private static final long serialVersionUID = 1;

    public ForbiddenActionException(String message){
        super(message);
    }
    
}
