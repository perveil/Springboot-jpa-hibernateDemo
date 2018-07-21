package com.example.demo.service;

import com.alibaba.fastjson.JSONArray;
import com.example.demo.entity.MenuEntity;

public interface menuService {
    public JSONArray SelectAll();
    public long insert(MenuEntity m);
    public void delete(long MenuId);
    public void update(MenuEntity m);
    public JSONArray select();
    public JSONArray getMenuButton(Long menuId);
    public void updateButton(Long menuId, Long[] buttonId);
}
