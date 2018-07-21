package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class MapMenubuttonEntityPK implements Serializable {
    private long menuId;
    private long buttonId;

    @Column(name = "menuID", nullable = false)
    @Id
    public long getMenuId() {
        return menuId;
    }

    public void setMenuId(long menuId) {
        this.menuId = menuId;
    }

    @Column(name = "buttonID", nullable = false)
    @Id
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
        MapMenubuttonEntityPK that = (MapMenubuttonEntityPK) o;
        return menuId == that.menuId &&
                buttonId == that.buttonId;
    }

    @Override
    public int hashCode() {

        return Objects.hash(menuId, buttonId);
    }
}
