package com.example.demo.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.MenuEntity;
import com.example.demo.vo.MenuVo;

import javax.json.JsonArray;
import java.lang.reflect.Field;
import java.util.LinkedHashMap;
/*
* JsonObject 内部是用HashMap来存储的，所以输出是按Key的Hash值来排序，如果要让JsonObject按固定顺序比如Put的顺序排列，
* 可以修改HashMap改为LinkHashMap() 例如：JSONObject jsonObj = new JSONObject(new LinkedHashMap());
* */
public class JsonUtil {
    public  static JSONObject makeJson(MenuVo menuVo){
        Field field[]=MenuEntity.class.getDeclaredFields(); //获得所有的属性 field类

        JSONObject jsonObject=new JSONObject(new LinkedHashMap());
        for (int i=0;i<field.length;i++){
            jsonObject.put(
                    field[i].toString().substring(field[i].toString().lastIndexOf('.')+1),
                    menuVo.getItself().getXXX( field[i].toString().substring(field[i].toString().lastIndexOf('.')+1)));
        }
        jsonObject.put("children",menuVo.getChildrenList());
        return jsonObject;
    }

    public static JSONObject makeJsonVersionTwo(MenuVo menuVo){
        JSONObject jsonObject=new JSONObject(new LinkedHashMap());
        jsonObject.put("id",menuVo.getItself().getMenuId());
        jsonObject.put("text",menuVo.getItself().getMenuName());
        JSONArray jsonArray=new JSONArray();
        for (int i=0;i<menuVo.getChildrenList().size();i++){
            jsonArray.add(makeJsonVersionThree(menuVo.getChildrenList().get(i)));
        }
        jsonObject.put("children",jsonArray);
        return jsonObject;
    }
    public static JSONObject makeJsonVersionThree(MenuEntity menuEntity){
        JSONObject jsonObject=new JSONObject(new LinkedHashMap());
        jsonObject.put("id",menuEntity.getMenuId());
        jsonObject.put("text",menuEntity.getMenuName());
        jsonObject.put("children", new JSONArray());
        return jsonObject;
    }


    public static void main(String[] args) throws Exception {

        MenuVo menuVo=new MenuVo();
        MenuEntity menuEntity=new MenuEntity();
        menuEntity.setMenuId(101);
        menuEntity.setEnabledState((byte) 1);
        menuEntity.setMenuCode("asdasd");
        menuEntity.setVisibleState((byte)1);
        menuEntity.setMenuName("王瑞睿");
        menuEntity.setUrl("/api/select");
        menuEntity.setSupId(1);
        menuEntity.setSerial((long) 101);
        menuVo.setItself(menuEntity);
        menuVo.setChildrenList(null);

       // menuEntity.getXXXUseReflect("MenuName");
        System.out.println( menuEntity.getXXXUseReflect(menuEntity,"MenuName"));
//        JSONObject jsonObject=makeJson(menuVo);
//        System.out.println(menuEntity.getXXX("menuName"));
//        System.out.println(jsonObject.get("menuId"));



       // jsonObject.get("MenuId");
//        Field field[]=MenuEntity.class.getDeclaredFields();
//        System.out.println(field.length);
//        JSONObject jsonObject=new JSONObject();
//
//        jsonObject.put(field[0].toString().substring(field[0].toString().lastIndexOf('.')+1),"asdasd");
//        System.out.println(jsonObject.get(field[0].toString().substring(field[0].toString().lastIndexOf('.')+1)));
//        System.out.println(field[0].toString().substring(field[0].toString().lastIndexOf('.')+1));

    }


}
