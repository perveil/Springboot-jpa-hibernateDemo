package com.example.demo.exception;

import com.example.demo.util.ResultEnums;

public class MyException extends Exception {
    private  int code;
     public MyException(ResultEnums enums){
         super(enums.getMessage());
         this.code=enums.getCode();
     }

}
