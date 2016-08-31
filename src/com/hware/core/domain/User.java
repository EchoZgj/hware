package com.hware.core.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2016/7/29.
 */
@Entity
public class User {
    private Long id;
    private int userNo;
    private String userName;
    private int sex;
    private String phone;
    private int age;
    private double height;

    // 0 未同步，1.同步成功 2 同步失败
    private int flag;
    private Date measureDate;
    private double weight;
    private int bodyConfirm;
    private String address;
    private String comment;
    private String birthday;
    String uuid;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user_no", nullable = false, insertable = true, updatable = true)
    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    @Basic
    @Column(name = "user_name", nullable = true, insertable = true, updatable = true, length = 20)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "sex", nullable = false, insertable = true, updatable = true)
    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "phone", nullable = true, insertable = true, updatable = true, length = 255)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "age", nullable = false, insertable = true, updatable = true)
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Basic
    @Column(name = "height", nullable = false, insertable = true, updatable = true, precision = 0)
    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Basic
    @Column(name = "flag", nullable = false, insertable = true, updatable = true)
    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public Date getMeasureDate() {
        return measureDate;
    }

    public void setMeasureDate(Date measureDate) {
        this.measureDate = measureDate;
    }

    @Basic
    @Column(name = "weight", nullable = false, insertable = true, updatable = true, precision = 1)
    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Basic
    @Column(name = "body_confirm", nullable = false, insertable = true, updatable = true)
    public int getBodyConfirm() {
        return bodyConfirm;
    }

    public void setBodyConfirm(int bodyConfirm) {
        this.bodyConfirm = bodyConfirm;
    }

    @Basic
    @Column(name = "address", nullable = true, insertable = true, updatable = true, length = 255)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "comment", nullable = true, insertable = true, updatable = true, length = 255)
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Basic
    @Column(name = "birthday", nullable = true, insertable = true, updatable = true, length = 100)
    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }


}
