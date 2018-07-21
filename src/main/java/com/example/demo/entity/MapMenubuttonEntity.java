package com.example.demo.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "map_menubutton")
@IdClass(MapMenubuttonEntityPK.class)
public class MapMenubuttonEntity {
    private long menuId;
    private long buttonId;

    public  MapMenubuttonEntity(){ }
    public  MapMenubuttonEntity(long menuId,long buttonId){
        this.buttonId=buttonId;
        this.menuId=menuId;
    }

    @Id
    @Column(name = "menuID", nullable = false)
    public long getMenuId() {
        return menuId;
    }

    public void setMenuId(long menuId) {
        this.menuId = menuId;
    }

    @Id
    @Column(name = "buttonID", nullable = false)
    public long getButtonId() {
        return buttonId;
    }

    public void setButtonId(long buttonId) {
        this.buttonId = buttonId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MapMenubuttonEntity that = (MapMenubuttonEntity) o;
        return menuId == that.menuId &&
                buttonId == that.buttonId;
    }

    @Override
    public int hashCode() {

        return Objects.hash(menuId, buttonId);
    }
}
