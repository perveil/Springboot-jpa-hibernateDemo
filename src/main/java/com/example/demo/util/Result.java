package com.example.demo.util;

import lombok.Data;

import java.util.Objects;

@Data
public class Result<T> {
      int state;  //错误代码
      String msg;   //错误描述
      T o;    //返回的对象可以用于token
      Long id;   //menuId

    public Long getId() {
        return id;
    }

    public Result setId(Long id) {
        this.id = id;
        return this;
    }

    @Override
    public boolean equals(Object o1) {
        if (this == o1) return true;
        if (o1 == null || getClass() != o1.getClass()) return false;
        Result<?> result = (Result<?>) o1;
        return state == result.state &&
                Objects.equals(msg, result.msg) &&
                Objects.equals(o, result.o);
    }

    @Override
    public int hashCode() {

        return Objects.hash(state, msg, o);
    }

    public int getState() {
        return state;

    }

    public Result setState(int state) {
        this.state = state;
        return  this;
    }

    public String getMsg() {
        return msg;
    }

    public Result setMsg(String msg) {
        this.msg = msg;
        return this;

    }

    public T getO() {
        return o;
    }

    public Result setO(T o) {
        this.o = o;
        return this;
    }




}
