package com.example.demo.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "button")
public class ButtonEntity {
    private long buttonId;
    private String buttonCode;
    private String buttonName;
    private Long serial;
    private int IsSelected;

    public  void  ChangeIsSelected(){
        IsSelected=1;
    }
    public  int  ObtainIsSelected(){
        return IsSelected;
    }


    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "buttonID", nullable = false)
    public long getButtonId() {
        return buttonId;
    }

    public void setButtonId(long buttonId) {
        this.buttonId = buttonId;
    }

    @Basic
    @Column(name = "buttonCode", nullable = false, length = 50)
    public String getButtonCode() {
        return buttonCode;
    }

    public void setButtonCode(String buttonCode) {
        this.buttonCode = buttonCode;
    }

    @Basic
    @Column(name = "buttonName", nullable = false, length = 50)
    public String getButtonName() {
        return buttonName;
    }

    public void setButtonName(String buttonName) {
        this.buttonName = buttonName;
    }


    @Basic
    @Column(name = "serial", nullable = true)
    public Long getSerial() {
        return serial;
    }

    public void setSerial(Long serial) {
        this.serial = serial;
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ButtonEntity that = (ButtonEntity) o;
        return buttonId == that.buttonId &&
                Objects.equals(buttonCode, that.buttonCode) &&
                Objects.equals(buttonName, that.buttonName) &&
                Objects.equals(serial, that.serial);
    }

    @Override
    public int hashCode() {

        return Objects.hash(buttonId, buttonCode, buttonName, serial);
    }
}
