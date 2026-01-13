package com.student.entity;

public class Student {
    // 对应students表的id字段
    private Integer id;
    // 对应name字段
    private String name;
    // 对应gender字段
    private String gender;
    // 对应class_name字段（Java规范用驼峰命名）
    private String className;
    // 对应math_score字段
    private Double mathScore;
    // 对应java_score字段
    private Double javaScore;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Double getMathScore() {
        return mathScore;
    }

    public void setMathScore(Double mathScore) {
        this.mathScore = mathScore;
    }

    public Double getJavaScore() {
        return javaScore;
    }

    public void setJavaScore(Double javaScore) {
        this.javaScore = javaScore;
    }
}