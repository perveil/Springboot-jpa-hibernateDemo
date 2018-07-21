package com.example.demo.handle;

import com.example.demo.exception.MyException;
import com.example.demo.util.Result;
import com.example.demo.util.ResultUtil;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public static Result handleFileOverMaxSizeException(RuntimeException ex){
        ex.printStackTrace();
        /*
        RuntimeException是NullPointerException的父类，
        当发生NullPointerException异常时，但是想返回一个ResponesStatus状态码，
        所以使用AnnotationUtils来验证NullPointerException是否添加了@ResponesStatus注解

        通过传入AnnotatedElement和注解类型来查找方法或者类对象上的注解
        * */
        if (AnnotationUtils.findAnnotation(ex.getClass(),ResponseStatus.class)!=null){
            throw ex;
        }
       if (ex instanceof MaxUploadSizeExceededException){
           return ResultUtil.fail(-1,ex.getMessage());
       }
       return ResultUtil.fail(-2,new RuntimeException().getMessage());

    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST) //返回一个bad.Request
    public static Result handleNullPointerException(NullPointerException ex){
       // System.out.println( new NumberFormatException() instanceof RuntimeException);
        return ResultUtil.fail(-1,"所传递参数为空");
    }

    @ExceptionHandler(MyException.class)
    @ResponseBody
    public static Result handleMyException(MyException ex){
        return ResultUtil.fail(-1,ex.getMessage());
    }

    //AnnotationUtils 工具类的使用

}
