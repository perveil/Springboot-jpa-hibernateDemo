package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.ButtonEntity;
import com.example.demo.service.btnService;
import com.example.demo.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.sql.SQLException;

@RequestMapping("/btn")
@Controller
@CrossOrigin("*")
public class buttonController {
    @Autowired
    private btnService service;

    @ResponseBody
    @RequestMapping(value = "/insert",produces="text/plain;charset=UTF-8")
    public String insert(@RequestBody String btn)throws SQLException {
        JSONObject jsonObject=JSON.parseObject(btn);
    //    ButtonEntity newbtn=(ButtonEntity)JSONObject.toJavaObject(jsonObject,ButtonEntity.class);  //转化为java对象 ??????
        ButtonEntity newbtn=new ButtonEntity();
        newbtn.setButtonCode(jsonObject.getString("buttonCode"));
        newbtn.setButtonName(jsonObject.getString("buttonName"));
        try {
            newbtn.setSerial(Long.parseLong(jsonObject.getString("serial")));
            return JSON.toJSONString(service.insert(newbtn));
        }catch ( Exception e){
           e.printStackTrace();
           return JSON.toJSONString(ResultUtil.fail(-1,"Serial数据格式是错的，请确认格式"));
        }
    }
    @ResponseBody
    @RequestMapping(value = "/update",produces="text/plain;charset=UTF-8")
    public String update(@RequestBody String btn){
        JSONObject jsonObject=JSON.parseObject(btn);
        ButtonEntity newbtn=new ButtonEntity();
        newbtn.setButtonCode(jsonObject.getString("buttonCode"));
        newbtn.setButtonName(jsonObject.getString("buttonName"));
        newbtn.setButtonId(Long.parseLong(jsonObject.getString("buttonId")));
        newbtn.setSerial(Long.parseLong(jsonObject.getString("serial")));
       return  JSON.toJSONString(service.update(newbtn));
    }
    @ResponseBody
    @RequestMapping(value = "/delete",produces="text/plain;charset=UTF-8")
    public String delete(String btnId){
        String [] btnIdList=btnId.split(",");
        for (int i=0;i<btnIdList.length;i++){
            service.delete(Long.parseLong(btnIdList[i]));
        }
        return JSON.toJSONString(ResultUtil.Success());
    }
    @ResponseBody
    @RequestMapping(value = "/select",produces="text/plain;charset=UTF-8")
    public String select(String btnId){
       return JSON.toJSONString(service.select(Long.parseLong(btnId)));
    }
    @ResponseBody
    @RequestMapping(value = "/selectAll",produces="text/plain;charset=UTF-8")
    public String selectAll(){
        return JSON.toJSONString(service.selectAll());
    }


}
