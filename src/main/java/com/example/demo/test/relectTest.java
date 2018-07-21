package com.example.demo.test;

import com.example.demo.entity.MenuEntity;

import java.lang.reflect.Field;

public class relectTest {
    public static void main(String[] args) {
        MenuEntity menuEntity=new MenuEntity();
        menuEntity.setMenuId(12123);
        Class cls=menuEntity.getClass();
        Field[] fields=cls.getDeclaredFields(); //获得所有的属性
        System.out.println(fields[1]);
    }
}
