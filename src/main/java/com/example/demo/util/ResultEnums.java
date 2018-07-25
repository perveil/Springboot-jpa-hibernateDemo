package com.example.demo.util;

import lombok.Getter;

@Getter
public enum ResultEnums {
    SUCCESS(0,"成功"),
    FAILED(-1,"系统错误"),
    MENUCODEORNAMEDUPLICATED(-2,"菜单name或者Code重复")
    ;

    private int code;
    private  String message;
    ResultEnums(int Code,String message){
        this.code=Code;
        this.message=message;
    }


    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
