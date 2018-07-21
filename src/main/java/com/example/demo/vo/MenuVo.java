package com.example.demo.vo;

import com.example.demo.entity.ButtonEntity;
import com.example.demo.entity.MenuEntity;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;


@Data
public class MenuVo{

    private List<MenuEntity> Children;
    private  MenuEntity itself;
    private  List<ButtonEntity> buttonEntityList;
    public List<ButtonEntity> getButtonEntityList() {
        return buttonEntityList;
    }
    public void setButtonEntityList(List<ButtonEntity> buttonEntityList) {
        this.buttonEntityList = buttonEntityList;
    }
    public MenuEntity getItself() {
        return itself;
    }
    public MenuVo setItself(MenuEntity itself) {
        this.itself = itself;
        return  this;
    }
    public List<MenuEntity> getChildrenList() {
        return Children;
    }

    public void setChildrenList(List<MenuEntity> childrenList) {
        Children = childrenList;
    }
}
