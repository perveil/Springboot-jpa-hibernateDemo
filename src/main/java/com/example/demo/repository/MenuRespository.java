package com.example.demo.repository;

import com.example.demo.entity.MenuEntity;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLUpdate;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;
import java.util.List;


public interface MenuRespository extends CrudRepository<MenuEntity,Long> {

    //查找
    @Query(value = "select menu.* from menu where menu.menuID=?1",nativeQuery = true)
    public  MenuEntity SelectByMenuId(long MenuId);

    //只查找id和name
    @Query(value = "select t.menuID,t.menuName from menu t order by t.menuID",nativeQuery = true)
    public  List<Object> SelectOnlyNameAndId();

    //按照顺序把数据拿到手
    @Query(value = "select  t.*  from  tonfun_sys.menu t order by  t.menuID",nativeQuery = true)
    public List<MenuEntity> SelectOderByMenuId();

    //找到下一级元素
    @Query(value = "select menu.*  from menu where menu.supMenuID=?1",nativeQuery = true)
    public  List<MenuEntity> SelectByfatherMenuId(long fatherMenuId);

    @Query(value = "select menu.menuID,menu.menuName from menu where menu.supMenuID=?1",nativeQuery = true)
    public  List<MenuEntity> SelectByfatherMenuIdOnlyNameAndId(long fatherMenuId);

    //删除元素
    @Modifying
    @Query(value = "delete from tonfun_sys.menu where menuID=?1",nativeQuery = true)
    @Transactional
    public void deleteByMenuId(long MenuId);

    //修改元素
//    @Modifying
//    @Query(value = "update tonfun_sys.menu set pa",nativeQuery = true)


   //蠢萌的方法 不更新supId
    //@Query 如果是修改 需要加上Modifying
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value ="update tonfun_sys.menu set menuName=:menuname,menuCode=:menucode,url=:url,serial=:serial,enabledState=:EnabledState,visibleState=:VisibleState,supMenuID=:supId where menuID=:menuid",nativeQuery = true)
    public void UpdateByMenuId(
            @Param("menuname") String menuName,
            @Param("menucode") String menuCode,
            @Param("url")String url,
            @Param("supId") Long supId,
            @Param("serial") Long serial ,
            @Param("EnabledState") Byte Enabled ,
            @Param("VisibleState") Byte Visible,
            @Param("menuid") Long menuid);
}
