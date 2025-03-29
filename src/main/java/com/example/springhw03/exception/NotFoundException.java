package com.example.springhw03.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class NotFoundException extends RuntimeException{
    public NotFoundException(String message){
        super(message);
    }

}
