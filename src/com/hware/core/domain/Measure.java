package com.hware.core.domain;

import javax.persistence.*;

/**
 * Created by zhangguojing on 2016/7/30.
 */
@Entity
public class Measure {
    private long id;
    private int user_id;
    private double fattingWeight;
    private double fatWeight;
    private double muscleWeight;
    private double boneWeight;
    private double proteinWeight;
    private double waterWeight;
    private double ratioFat;
    private double indexFat;
    private double ratioHip;
    private int flag;

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    @Basic
    @Column(name = "user_id", nullable = false, insertable = true, updatable = true)
    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Basic
    @Column(name = "fatting_weight", nullable = true, insertable = true, updatable = true)
    public double getFattingWeight() {
        return fattingWeight;
    }

    public void setFattingWeight(double fattingWeight) {
        this.fattingWeight = fattingWeight;
    }

    @Basic
    @Column(name = "fat_weight", nullable = true, insertable = true, updatable = true)
    public double getFatWeight() {
        return fatWeight;
    }

    public void setFatWeight(double fatWeight) {
        this.fatWeight = fatWeight;
    }

    @Basic
    @Column(name = "muscle_weight", nullable = true, insertable = true, updatable = true)
    public double getMuscleWeight() {
        return muscleWeight;
    }

    public void setMuscleWeight(double muscleWeight) {
        this.muscleWeight = muscleWeight;
    }

    @Basic
    @Column(name = "bone_weight", nullable = true, insertable = true, updatable = true)
    public double getBoneWeight() {
        return boneWeight;
    }

    public void setBoneWeight(double boneWeight) {
        this.boneWeight = boneWeight;
    }

    @Basic
    @Column(name = "protein_weight", nullable = true, insertable = true, updatable = true)
    public double getProteinWeight() {
        return proteinWeight;
    }

    public void setProteinWeight(double proteinWeight) {
        this.proteinWeight = proteinWeight;
    }

    @Basic
    @Column(name = "water_weight", nullable = true, insertable = true, updatable = true)
    public double getWaterWeight() {
        return waterWeight;
    }

    public void setWaterWeight(double waterWeight) {
        this.waterWeight = waterWeight;
    }

    @Basic
    @Column(name = "ratio_fat", nullable = true, insertable = true, updatable = true)
    public double getRatioFat() {
        return ratioFat;
    }

    public void setRatioFat(double ratioFat) {
        this.ratioFat = ratioFat;
    }

    @Basic
    @Column(name = "index_fat", nullable = true, insertable = true, updatable = true)
    public double getIndexFat() {
        return indexFat;
    }

    public void setIndexFat(double indexFat) {
        this.indexFat = indexFat;
    }

    @Basic
    @Column(name = "ratio_hip", nullable = true, insertable = true, updatable = true)
    public double getRatioHip() {
        return ratioHip;
    }

    public void setRatioHip(double ratioHip) {
        this.ratioHip = ratioHip;
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
