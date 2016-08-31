package com.hware.core.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by zhangguojing on 2016/7/31.
 */
@Entity
public class Testdata {

    private Long id;
    //测试日期
    private Date measureDate;
    //编号
    private long userId;
    //年龄
    private int age;
    //性别0男1女
    private int sex;
    //脂肪==体脂肪量
    private double fat;
    //骨质=骨质量
    private double bone;
    //蛋白质
    private double protein;
    //细胞内液
    private double interFluid;
    //细胞外液
    private double outFluid;
    //肌肉=肌肉量
    private double muscle;
    //瘦体重
    private double slimWeight;
    //身高
    private double height;
    //体重
    private double weight;
    //标准体重
    private double criteriaWeight;
    //体重控制
    private double weightControl;
    //脂肪控制
    private double fatControl;
    //肌肉控制
    private double muscleControl;
    // 体脂百分比
    private double ratioFat;
    //骨骼肌
    private double boneMuscle;
    //BMI
    private double bmi;
    //腰臀比
    private double ratioHip;
    //基础代谢率
    private double baseMetabolize;
    //水肿系数
    private double edemaRatio;
    //内脏脂肪
    private double internalFat;
    //皮下脂肪
    private double skinFat;
    //身体年龄
    private int bodyAge;
    //分数
    private double score;

    String uuid;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(nullable = false, insertable = true, updatable = true)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getMeasureDate() {
        return measureDate;
    }

    public void setMeasureDate(Date measureDate) {
        this.measureDate = measureDate;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public double getBone() {
        return bone;
    }

    public void setBone(double bone) {
        this.bone = bone;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getInterFluid() {
        return interFluid;
    }

    public void setInterFluid(double interFluid) {
        this.interFluid = interFluid;
    }

    public double getOutFluid() {
        return outFluid;
    }

    public void setOutFluid(double outFluid) {
        this.outFluid = outFluid;
    }

    public double getMuscle() {
        return muscle;
    }

    public void setMuscle(double muscle) {
        this.muscle = muscle;
    }

    public double getSlimWeight() {
        return slimWeight;
    }

    public void setSlimWeight(double slimWeight) {
        this.slimWeight = slimWeight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getCriteriaWeight() {
        return criteriaWeight;
    }

    public void setCriteriaWeight(double criteriaWeight) {
        this.criteriaWeight = criteriaWeight;
    }

    public double getWeightControl() {
        return weightControl;
    }

    public void setWeightControl(double weightControl) {
        this.weightControl = weightControl;
    }

    public double getFatControl() {
        return fatControl;
    }

    public void setFatControl(double fatControl) {
        this.fatControl = fatControl;
    }

    public double getMuscleControl() {
        return muscleControl;
    }

    public void setMuscleControl(double muscleControl) {
        this.muscleControl = muscleControl;
    }

    public double getRatioFat() {
        return ratioFat;
    }

    public void setRatioFat(double ratioFat) {
        this.ratioFat = ratioFat;
    }

    public double getBoneMuscle() {
        return boneMuscle;
    }

    public void setBoneMuscle(double boneMuscle) {
        this.boneMuscle = boneMuscle;
    }

    public double getBmi() {
        return bmi;
    }

    public void setBmi(double bmi) {
        this.bmi = bmi;
    }

    public double getRatioHip() {
        return ratioHip;
    }

    public void setRatioHip(double ratioHip) {
        this.ratioHip = ratioHip;
    }

    public double getBaseMetabolize() {
        return baseMetabolize;
    }

    public void setBaseMetabolize(double baseMetabolize) {
        this.baseMetabolize = baseMetabolize;
    }

    public double getEdemaRatio() {
        return edemaRatio;
    }

    public void setEdemaRatio(double edemaRatio) {
        this.edemaRatio = edemaRatio;
    }

    public double getInternalFat() {
        return internalFat;
    }

    public void setInternalFat(double internalFat) {
        this.internalFat = internalFat;
    }

    public double getSkinFat() {
        return skinFat;
    }

    public void setSkinFat(double skinFat) {
        this.skinFat = skinFat;
    }

    public int getBodyAge() {
        return bodyAge;
    }

    public void setBodyAge(int bodyAge) {
        this.bodyAge = bodyAge;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
