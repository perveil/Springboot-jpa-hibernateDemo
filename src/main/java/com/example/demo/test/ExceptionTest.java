package com.example.demo.test;

import com.example.demo.exception.MyException;

public class ExceptionTest {
    public static void main(String[] args) {
        try{
            throw new MyException("数据格式错误");
        }catch (MyException e){
          e.printStackTrace();
        }
    }
}
