package com.example.demo.service;

import com.example.demo.entity.ButtonEntity;
import com.example.demo.util.Result;

import java.util.List;

public interface btnService {
     public Object select(Long btnId);
     public Result insert(ButtonEntity buttonEntity);
     public  Result update(ButtonEntity buttonEntity);
     public  Result delete(Long btnId);
    public List<ButtonEntity> selectAll();
}
