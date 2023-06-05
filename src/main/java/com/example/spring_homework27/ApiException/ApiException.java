package com.example.spring_homework27.ApiException;

import lombok.AllArgsConstructor;
import lombok.Data;


public class ApiException extends  RuntimeException {

    public ApiException(String message){
        super(message);
    }
}
