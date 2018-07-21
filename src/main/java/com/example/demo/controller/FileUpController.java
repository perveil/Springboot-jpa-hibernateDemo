package com.example.demo.controller;

import com.example.demo.util.Result;
import com.example.demo.util.ResultUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

@Controller
@RequestMapping("/file")
public class FileUpController {

    @ResponseBody
    @RequestMapping(value = "/fileUp",method = {RequestMethod.POST})
    public String fileUp(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //创建一个通用的多部分解析器
        CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(request.getSession().getServletContext());
        //判断Request是否有文件上传
        if (multipartResolver.isMultipart(request)){
            //转换成多部分request
            MultipartHttpServletRequest multiRequest= (MultipartHttpServletRequest) request;
            Iterator<String> iterator=multiRequest.getFileNames();  //取得request中所有的文件名
            while (iterator.hasNext()){
                //记录上传过程的起始时间，用来计算上传时间
                int pre=(int)System.currentTimeMillis();
                //取得上传文件
                MultipartFile file=multiRequest.getFile(iterator.next());
                if (file!=null){
                    String myFileName = file.getOriginalFilename();
                    if (myFileName.trim().equals("")){
                        String newFileName="uploadFile"+file.getOriginalFilename();
                        String holdPath="D:/"+newFileName;   //保存的文件路径 如果是服务器就所有的文件就放到项目路径
                        //然后将数据库中的字段与holdPath进行关联
                        File holdFile=new File(holdPath);
                        try {
                            file.transferTo(holdFile);
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (IllegalStateException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return ResultUtil.Success().toString();
    }
}
