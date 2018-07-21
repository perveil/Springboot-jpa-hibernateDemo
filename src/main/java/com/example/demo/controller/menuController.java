package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.example.demo.entity.MenuEntity;
import com.example.demo.service.menuService;
import com.example.demo.util.ResultUtil;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/menu")
public class menuController {
   @Autowired
   private menuService service;

    // FilterRegistrationBean

  // @ExceptionHandler
   @RequestMapping(value = "/select",produces="text/plain;charset=UTF-8")
   @ResponseBody
   public String Select(){
         return JSON.toJSONString(service.select());
   }

    @RequestMapping(value = "/getAllMenus",produces="text/plain;charset=UTF-8")
    @ResponseBody
    public String getAllMenus(){
        return JSON.toJSONString(service.SelectAll());
    }

    @RequestMapping(value = "/insert",produces="text/plain;charset=UTF-8")
    @ResponseBody
    public String   insert(String menuName, String menuCode, String url, String supId, String serial,String enabledState , String visibleState, String menuId)
    throws MySQLIntegrityConstraintViolationException{

        MenuEntity menuEntity=new MenuEntity();
        menuEntity.setSerial(Long.parseLong(serial));
        menuEntity.setSupId(Long.parseLong(supId));
        menuEntity.setMenuName(menuName);
//        menuEntity.setMenuId(Long.parseLong(menuId)); //自动生成
        menuEntity.setVisibleState(Byte.parseByte(visibleState));
        menuEntity.setEnabledState(Byte.parseByte(enabledState));
        menuEntity.setUrl(url);
        menuEntity.setMenuCode(menuCode);
         if (service.insert(menuEntity)!=-1){
             return  JSON.toJSONString(ResultUtil.Success(service.insert(menuEntity)));
         }else {
             return JSON.toJSONString(ResultUtil.fail(-1,"插入错误,请观察menuName或者menuCode重复"));
         }


    }

    @RequestMapping("/delete")
    @ResponseBody
    public String   delete(String menuId){
           service.delete(Long.parseLong(menuId));
           return  JSON.toJSONString(ResultUtil.Success());
    }

    @RequestMapping("/update")
    @ResponseBody
    public String   update(String menuName, String menuCode, String url, String supId, String serial,String enabledState , String visibleState, String menuId
    ){
        MenuEntity menuEntity=new MenuEntity();
        menuEntity.setSerial(Long.parseLong(serial));
        menuEntity.setSupId(Long.parseLong(supId));
        menuEntity.setMenuName(menuName);
        menuEntity.setMenuId(Long.parseLong(menuId));
        menuEntity.setVisibleState(Byte.parseByte(visibleState));
        menuEntity.setEnabledState(Byte.parseByte(enabledState));
        menuEntity.setUrl(url);
        menuEntity.setMenuCode(menuCode);
        try{
            service.update(menuEntity);
            return JSON.toJSONString(ResultUtil.Success());
        }catch (DataIntegrityViolationException e){ //
              return JSON.toJSONString(ResultUtil.fail(-1,"不存在这个父级或者智能是VisibleState,EnabledState只能是1或者0"));
        }catch(Exception e){
            return JSON.toJSONString(ResultUtil.fail(-2,"VisibleState,EnabledState只能是1或者0"));
        }

    }

    @RequestMapping(value = "/getMenuButton",produces="text/plain;charset=UTF-8")
    @ResponseBody
    public  String getMenuButton(String menuId){
       if (menuId.equals("null")||menuId==null){
           return JSON.toJSONString(ResultUtil.fail(-2,"数据格式不匹配,请重新发送menuId"));
       }
       return JSON.toJSONString(service.getMenuButton(Long.parseLong(menuId)), SerializerFeature.DisableCircularReferenceDetect);
    }

    @RequestMapping(value = "/updateButton",produces="text/plain;charset=UTF-8")
    @ResponseBody
    public String updateButton(String menuId,String buttonId){
        String[] buttonIdStringArray=buttonId.split(",");   //解析得到所有的ButtonId
        Long [] buttonIdLongArray=new Long[buttonIdStringArray.length];
        for (int i=0;i<buttonIdStringArray.length;i++){
               buttonIdLongArray[i]=Long.parseLong(buttonIdStringArray[i]);
        }
        service.updateButton(Long.parseLong(menuId),buttonIdLongArray);
       return "Asd";
    }
}
