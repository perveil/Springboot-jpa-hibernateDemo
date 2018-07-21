package com.example.demo.util;

public class ResultUtil {
    //结果工具
   public static Result Success(Object o){ return new Result().setState(0).setMsg("Success").setO(o); }
   //返回一个Menuid的值
    public static Result Success(Long menuId){ return new Result().setState(0).setMsg("Success").setId(menuId); }
    public static Result Success(){ return new Result().setState(0).setMsg("Success"); }
    public static Result fail(int state,String msg){ return new Result().setState(state).setMsg(msg); }
}
