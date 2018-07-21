package com.example.demo.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class DataFilterUtil {
    public static String CheckIsUndefinedOrNull(String str){
        if (str.equals("undefined")){
           return "";
        }else  if (str.equals("null")){
          return "";
        }else {
          return str;
        }
    }
    /**
     * 判断字符串是否全部为数字
     * @param  将验证的数据
     * @return boolean
     */
    public static boolean cheakStrlIsNumber(String str){
        if (str==null||str.length()==0) return false;
        return Pattern.compile("^[0-9]*.{1}[0-9]*$").matcher(str).matches();

    }
    /**
     * 返回一个固定格式的当前时间
     */
    public static  String getNow(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }


}
