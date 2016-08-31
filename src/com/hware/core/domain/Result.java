package com.hware.core.domain;

import javax.persistence.*;

/**
 * Created zhangguojing on 2016/7/30.
 */
@Entity
public class Result {
    private Long id;
    private int UserId;
    private double internalFat;
    private double standardWeight;
    private double controlWeight;
    private double baseMetabolize;
    private double controlFat;
    private double controlMuscle;
    private int phAge;
    private double healthGrade;
    private int flag;



    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user_id", nullable = false, insertable = true, updatable = true)
    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    @Basic
    @Column(name = "internal_fat", nullable = true, insertable = true, updatable = true)
    public double getInternalFat() {
        return internalFat;
    }

    public void setInternalFat(double internalFat) {
        this.internalFat = internalFat;
    }

    @Basic
    @Column(name = "standard_weight", nullable = true, insertable = true, updatable = true)
    public double getStandardWeight() {
        return standardWeight;
    }

    public void setStandardWeight(double standardWeight) {
        this.standardWeight = standardWeight;
    }

    @Basic
    @Column(name = "control_weight", nullable = true, insertable = true, updatable = true)
    public double getControlWeight() {
        return controlWeight;
    }

    public void setControlWeight(double controlWeight) {
        this.controlWeight = controlWeight;
    }

    @Basic
    @Column(name = "base_metabolize", nullable = true, insertable = true, updatable = true)
    public double getBaseMetabolize() {
        return baseMetabolize;
    }

    public void setBaseMetabolize(double baseMetabolize) {
        this.baseMetabolize = baseMetabolize;
    }

    @Basic
    @Column(name = "control_fat", nullable = true, insertable = true, updatable = true)
    public double getControlFat() {
        return controlFat;
    }

    public void setControlFat(double controlFat) {
        this.controlFat = controlFat;
    }

    @Basic
    @Column(name = "control_muscle", nullable = true, insertable = true, updatable = true)
    public double getControlMuscle() {
        return controlMuscle;
    }

    public void setControlMuscle(double controlMuscle) {
        this.controlMuscle = controlMuscle;
    }

    @Basic
    @Column(name = "ph_age", nullable = true, insertable = true, updatable = true)
    public int getPhAge() {
        return phAge;
    }

    public void setPhAge(int phAge) {
        this.phAge = phAge;
    }

    @Basic
    @Column(name = "health_grade", nullable = true, insertable = true, updatable = true)
    public double getHealthGrade() {
        return healthGrade;
    }

    public void setHealthGrade(double healthGrade) {
        this.healthGrade = healthGrade;
    }

    @Basic
    @Column(name = "flag", nullable = false, insertable = true, updatable = true)
    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
