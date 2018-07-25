package com.example.demo.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "menu")
@DynamicUpdate(true)
@DynamicInsert(true)
@SelectBeforeUpdate
public class MenuEntity {
    private long menuId;
    private long supId;
    private String menuCode;
    private String menuName;
    private Long serial;
    private String url;
    private Byte visibleState;
    private Byte enabledState;

    public MenuEntity(long menuId,String menuName){
        this.menuId=menuId;
        this.menuName=menuName;

    }
    public MenuEntity(){ }

    //不想映射的属性函数名不能弄成get/set这种格式
    private List<MenuEntity> children;
    private List<ButtonEntity> buttonList;

    public   List<MenuEntity> SelectChildren(){
        return  children;
    }
    public  List<ButtonEntity> SelectButtonList(){
        return buttonList;
    }
    /*
     @GeneratedValue(strategy = GenerationType.IDENTITY) 返回一个自动生成的ID主键
    * */
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menuID")
    public long getMenuId() {
        return menuId;
    }

    public void setMenuId(long menuId) {
        this.menuId = menuId;
    }

    @Basic
    @Column(name = "supmenuid", nullable = false, length = 20)
    public long getSupId(){
        return  supId;
    }
    public void setSupId(long supId){
        this.supId=supId;
    }

    @Basic
    @Column(name = "menucode",  length = 50)
    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    @Basic
    @Column(name = "menuname",  length = 50)
    public String getMenuName() {
        return menuName;
    }
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    @Basic
    @Column(name = "serial")
    public Long getSerial() {
        return serial;
    }

    public void setSerial(Long serial) {
        this.serial = serial;
    }

    @Basic
    @Column(name = "url", length = 200)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    @Basic
    @Column(name = "visiblestate")
    public Byte getVisibleState() {
        return visibleState;
    }

    public void setVisibleState(Byte visibleState) {
        this.visibleState = visibleState;
    }

    @Basic
    @Column(name = "enabledstate")
    public Byte getEnabledState() {
        return enabledState;
    }
    public void setEnabledState(Byte enabledState) {
        this.enabledState = enabledState;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuEntity that = (MenuEntity) o;
        return menuId == that.menuId &&
                Objects.equals(menuCode, that.menuCode) &&
                Objects.equals(menuName, that.menuName) &&
                Objects.equals(serial, that.serial) &&
                Objects.equals(url, that.url) &&
                Objects.equals(visibleState, that.visibleState) &&
                Objects.equals(enabledState, that.enabledState);

    }

    @Override
    public int hashCode() {

        return Objects.hash(menuId, menuCode, menuName, serial, url,  visibleState, enabledState);
    }
    /*
    *  param 根据函数名的特征来调用函数....不建议使用
    * */
    public Object getXXX(String XXX){
        if (XXX.equals("menuId")){
             return  this.getMenuId();
        }else if (XXX.equals("supId")){
             return  this.getSupId();
        }else if (XXX.equals("menuCode")){
         return this.getMenuCode();
        }else if (XXX.equals("menuName")){
             return this.getMenuName();
        }else if (XXX.equals("serial")){
           return this.getSerial();
        }else if (XXX.equals("url")){
           return this.getUrl();
        }else if (XXX.equals("visibleState")){
            return this.getVisibleState();
        }else if (XXX.equals("enabledState")){
           return this.getEnabledState();
          //  return null;
        }else{
            return null;
        }
    }
    /*
    * param  需要委托的对象，如果没有的话就得在函数体内新建一个对象实例
    *param   传入得参数名，可以直接是函数名，也可以是函数名的一部分，之后进行字符串的拼接
    * */
    public Object getXXXUseReflect(MenuEntity menuEntity,String propertyName) throws Exception{
        String Classname="com.example.demo.entity.MenuEntity";
        Class clz=Class.forName(Classname);
        //对象新实例，如果你要在已经有的对象中执行函数，就必须把已有的对象返回过来。
        Object obj = clz.newInstance();
        //获取方法
        /*
        * getDeclaredMethod 方法参数介绍
        * param   name：方法的名字
        * argments :example: new Class [] {int.class,int.class} 一个数组，代表着传入的参数的类
        * */
        Method m =menuEntity.getClass().getDeclaredMethod("get"+propertyName);
        //调用函数
        /*
         * invoke param 1.动态委托的对象
         *              2.调用时所传递的参数
        */
        /*
         *如果方法正常完成，则将该方法返回的值返回给调用者；如果该值为基本类型，则首先适当地将其包装在对象中。但是，
         * 如果该值的类型为一组基本类型，则数组元素不 被包装在对象中；换句话说，将返回基本类型的数组。
         * 如果底层方法返回类型为 void，则该调用返回 null。
         */
        Object result=m.invoke(menuEntity);
        return result;
    }
}
