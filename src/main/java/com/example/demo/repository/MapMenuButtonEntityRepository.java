package com.example.demo.repository;

import com.example.demo.entity.MapMenubuttonEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface MapMenuButtonEntityRepository extends CrudRepository<MapMenubuttonEntity,Long> {
    //通过Button的Id数组批量删除按钮和菜单的关联 ,,有问题！！！！
    @Modifying
    @Transactional
    @Query(value = "delete from tonfun_sys.map_menubutton where map_menubutton.buttonID in (?1) and map_menubutton.menuID=?2",nativeQuery = true)
    public  void deleteByButtonIdList(Long[] ButtonIdList,Long menuId);
    //
    @Modifying
    @Transactional
    @Query(value = "delete from tonfun_sys.map_menubutton where map_menubutton.buttonID=?1",nativeQuery = true)
    public  void deleteByButtonId(Long ButtonId);

    @Modifying
    @Transactional
    @Query(value = "delete from tonfun_sys.map_menubutton where map_menubutton.menuID=?1",nativeQuery = true)
    public  void deleteBymenuID(Long menuId);


    //得到所有的关联
    @Transactional
    @Query(value = "select map_menubutton.buttonID from map_menubutton where map_menubutton.menuID=?1",nativeQuery = true)
    public  Long[] SelectByMenuId(Long menuId);




}
