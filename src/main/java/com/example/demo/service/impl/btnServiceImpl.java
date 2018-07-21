package com.example.demo.service.impl;

import com.example.demo.entity.ButtonEntity;
import com.example.demo.repository.ButtonRespository;
import com.example.demo.repository.MapMenuButtonEntityRepository;
import com.example.demo.service.btnService;
import com.example.demo.util.Result;
import com.example.demo.util.ResultUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

@Service
public class btnServiceImpl implements btnService {

    @Resource
    private ButtonRespository buttonRespository;
    @Resource
    private MapMenuButtonEntityRepository mapRepository;

    @Override
    public Object select(Long btnId) {
        if (buttonRespository.SelectBtnBybtnid(btnId)!=null){
            return buttonRespository.SelectBtnBybtnid(btnId);
        }else{
            return ResultUtil.fail(-1,"没找到");
        }


    }

    @Override
    public Result insert(ButtonEntity buttonEntity) {
        //插入失败
        try{
          ButtonEntity  btn=buttonRespository.save(buttonEntity);
            return ResultUtil.Success(btn.getButtonId());
        }catch (Exception e){
          e.printStackTrace();
          return ResultUtil.fail(-1,"插入失败，请注意按钮的Name或者Code是否重复还有数据格式的问题");
        }

    }

    @Override
    public Result update(ButtonEntity buttonEntity) {
        try{
            buttonRespository.updateByButtonId(
                    buttonEntity.getButtonId(),
                    buttonEntity.getButtonName(),
                    buttonEntity.getButtonCode(),
                    buttonEntity.getSerial());
            return ResultUtil.Success();
        }catch (Exception e){
            e.printStackTrace();
           return  ResultUtil.fail(-1,"更新失败,请检查ButtonCode或者ButtonName是否重复");
        }

    }

    @Override
    public Result delete(Long btnId) {
        //删除Map表(关联表)里边的关联
        try{
            mapRepository.deleteByButtonId(btnId);
            buttonRespository.deleteByButtonId(btnId); //删除btn表里的数据
            return ResultUtil.Success();
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.fail(-1,"删除失败");
        }
    }

    @Override
    public List<ButtonEntity> selectAll() {
        return buttonRespository.SelectBtnOrderByButtonId();
    }
}
