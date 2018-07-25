package com.example.demo.interceptor;

import com.example.demo.exception.MyException;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*
   org.springframework.web.multipart.commons.CommonsMultipartResolver Springboot内置的数据转化类
* */

public class FileSizeInterceptor implements HandlerInterceptor {
    private long maxSize; //限定文件最大size
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws MyException {
      //request.getSession().getAttribute(""); 获得对话里边的属性
       if (request==null&&ServletFileUpload.isMultipartContent(request)){ //????
           ServletRequestContext ctx=new ServletRequestContext(request);
           long requestSize=ctx.contentLength();
           if (requestSize>maxSize){
               //throw new MyException(""+maxSize/1024/1024+"MB,文件超过规定大小"); //抛出文件太大的异常
           }
       }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
