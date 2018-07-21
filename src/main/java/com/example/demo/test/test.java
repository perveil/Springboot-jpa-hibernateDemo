package com.example.demo.test;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import static java.net.URLEncoder.*;

public class test {

    public static void main(String[] args) throws UnsupportedEncodingException {
//        String  s="private java.lang.String com.example.demo.entity.MenuEntity.menuCode";
//        System.out.println(s.substring(s.lastIndexOf('.')+1));
        String name="wtf";
        System.out.println(name);
        //subString函数了解多少
        System.out.println(name.substring(0,1).toUpperCase().concat(name.substring(1)));

        //URLDecoder.decode(String aimStr,String enc);
        /*
        * param 要解码的字符串、目标字符编码格式UTF-8
        * */

        try{
            System.out.println(URLDecoder.decode("%e7%bb%84%e7%bb%87%e6%9c%ba%e6%9e%84", "UTF-8"));
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }

    }
}
