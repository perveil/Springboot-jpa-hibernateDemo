package com.example.demo.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.ButtonEntity;
import com.example.demo.entity.MapMenubuttonEntity;
import com.example.demo.entity.MenuEntity;
import com.example.demo.repository.ButtonRespository;
import com.example.demo.repository.MapMenuButtonEntityRepository;
import com.example.demo.repository.MenuRespository;
import com.example.demo.service.menuService;
import com.example.demo.util.JsonUtil;
import com.example.demo.vo.MenuVo;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class menuServiceImpl implements menuService {

    @Resource
    private MenuRespository menuRespository;
    @Resource
    private ButtonRespository buttonRespository;
    @Resource
    private MapMenuButtonEntityRepository mapRepository;

    @Transactional
    @Override
    public JSONArray SelectAll() {
      List<MenuEntity> menuEntityList= menuRespository.SelectOderByMenuId();
      List<MenuVo> AimList=new ArrayList<MenuVo>();
      AimList.add(new MenuVo().setItself(menuEntityList.get(0))); //先把第一个父级元素添加到第一个MenuVoList中
      menuEntityList.remove(0);
       for (int i=1;i<menuEntityList.size();i++){
         if (menuEntityList.get(i).getSupId()==0){
             AimList.add(new MenuVo().setItself(menuEntityList.get(i))); //把所有的父级元素先添加到AimList中
             menuEntityList.remove(menuEntityList.get(i));
         }
       }
       JSONArray menuVoJsonList=new JSONArray();
       for (int i=0;i<AimList.size();i++){
           AimList.get(i).setChildrenList(menuRespository.SelectByfatherMenuId(AimList.get(i).getItself().getMenuId()));
           menuVoJsonList.add( JsonUtil.makeJson(AimList.get(i)));
       }
        return menuVoJsonList;
    }

    @Override
    @Transactional
    public long insert(MenuEntity m) {
        try{
            MenuEntity newMenuEntity= menuRespository.save(m);
            return  newMenuEntity.getMenuId();
        }catch (RuntimeException e){
              e.printStackTrace();
            return (long) -1;
        }


    }

    @Override
    public void delete(long MenuId) throws RuntimeException {
        List<MenuEntity> menuSonEntityList = menuRespository.SelectByfatherMenuId(MenuId);
        MenuEntity menuEntity=menuRespository.SelectByMenuId(MenuId);  //删除的菜单本身
            //删除有子级的菜单
            for (int i=0;i<menuSonEntityList.size();i++){
                Long[] buttonIdList=mapRepository.SelectByMenuId(menuSonEntityList.get(i).getMenuId()); //通过menuId获得到关联表中所有的ButtonID
                if (buttonIdList.length>0){
                    mapRepository.deleteByButtonIdList(buttonIdList,MenuId);//批量删除关联
                } //如果没有关联按钮就不删除
                  menuRespository.deleteByMenuId(menuSonEntityList.get(i).getMenuId()); //删除父元素时需要删除所有的子元素.前提是有子级
            }
            //删除本身的按钮关联
        Long[] buttonIdList=mapRepository.SelectByMenuId(menuEntity.getMenuId()); //通过menuId获得到关联表中所有的ButtonID
        if (buttonIdList.length>0){
            mapRepository.deleteByButtonIdList(buttonIdList,MenuId);//批量删除关联
        }

        menuRespository.deleteByMenuId(MenuId);
    }

    @Override
    public void update(MenuEntity m) {
      //最笨的方法
      menuRespository.UpdateByMenuId(m.getMenuName(),
              m.getMenuCode(),
              m.getUrl(),
              m.getSupId(),
              m.getSerial(),
              m.getEnabledState(),
              m.getVisibleState(),
              m.getMenuId());
    }

    @Override
    public JSONArray select() {
         List<Object> menuEntityList1= menuRespository.SelectOnlyNameAndId();  //?????

        List<MenuEntity> menuEntityList= menuRespository.SelectOderByMenuId();
        List<MenuVo> AimList=new ArrayList<MenuVo>();
        AimList.add(new MenuVo().setItself(menuEntityList.get(0))); //先把第一个父级元素添加到第一个MenuVoList中
        menuEntityList.remove(0);
        for (int i=1;i<menuEntityList.size();i++){
            if (menuEntityList.get(i).getSupId()==0){
                AimList.add(new MenuVo().setItself(menuEntityList.get(i))); //把所有的父级元素先添加到AimList中
                menuEntityList.remove(menuEntityList.get(i));
            }
        }
        JSONArray menuVoJsonList=new JSONArray();
        for (int i=0;i<AimList.size();i++){
            AimList.get(i).setChildrenList(menuRespository.SelectByfatherMenuId(AimList.get(i).getItself().getMenuId()));
            menuVoJsonList.add(JsonUtil.makeJsonVersionTwo(AimList.get(i)));
        }
        return menuVoJsonList;

    }

    @Override
    public JSONArray getMenuButton(Long menuId) {
        JSONArray jsonArray=new JSONArray();
        List<ButtonEntity> AllButtonList= buttonRespository.SelectBtnOrderByButtonId();   //找到所有的按钮
        Long [] ButtonIdList=mapRepository.SelectByMenuId(menuId); //通过关联找到所有的ButtonId
        //通过ButtonId来找到ButtonIdList
        for (int i=0;i<ButtonIdList.length;i++){
            for (int j=0;j<AllButtonList.size();j++){
                 if (AllButtonList.get(j).getButtonId()==ButtonIdList[i]){
                     AllButtonList.get(j).ChangeIsSelected();
                 }
            }
        }
        for (int i=0;i<AllButtonList.size();i++){
            JSONObject jsonObject=new JSONObject();  //新建一个JSONObject
            jsonObject.put("name", AllButtonList.get(i).getButtonName());
            jsonObject.put("id", AllButtonList.get(i).getButtonId());
            jsonObject.put("Selected", AllButtonList.get(i).ObtainIsSelected());
            jsonArray.add(jsonObject);     //找到时，改变选择的状态后添加
        }

        return jsonArray;
    }

    @Override
    public void updateButton(Long menuId, Long[] buttonId) {
        mapRepository.deleteBymenuID(menuId);
        for (int i=0;i<buttonId.length;i++){
            mapRepository.save(new MapMenubuttonEntity(menuId,buttonId[i]));
        }
    }

}
