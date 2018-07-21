package com.example.demo.repository;

import com.example.demo.entity.ButtonEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;


public interface ButtonRespository extends CrudRepository<ButtonEntity,Long> {

    //根据buttonId来寻找button
    @Query(value = "SELECT * from tonfun_sys.button  where button.buttonID=:buttonid",nativeQuery = true)
    public ButtonEntity SelectBtnBybtnid(@Param("buttonid") long buttonid);

    //抓到所有的button
    @Query(value = "SELECT * from tonfun_sys.button order  by buttonID",nativeQuery = true)
    public List<ButtonEntity> SelectBtnOrderByButtonId();

    @Modifying
    @Transactional
    @Query(value = "delete from tonfun_sys.button where  button.buttonID=?1",nativeQuery = true)
    public  void deleteByButtonId(Long btnId);

    @Transactional
    @Modifying
    @Query(value = "update tonfun_sys.button set button.button_code=:code,button.button_name=:name,button.serial=:serial where button.buttonID=:btnId ",nativeQuery = true)
    public  void updateByButtonId(@Param("btnId") Long btnId,
                                  @Param("name")String name,
                                  @Param("code")String code,
                                  @Param("serial")Long serial);
}
