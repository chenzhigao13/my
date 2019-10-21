package com.liandi.common.easypoi.entity;

import java.io.Serializable;

import cn.afterturn.easypoi.excel.annotation.Excel;

/**
 * @author czg
 * @date 2019/8/16 10:52
 * @description 学生
 */
public class DataModel implements Serializable {

    @Excel(name = "姓名", isImportField = "true_st")
    private String name;

    @Excel(name = "性别", isImportField = "true_st")
    private String sex;

    @Excel(name = "总分", isImportField = "true_st")
    private String count;

    @Excel(name = "课程", isImportField = "true_st")
    private String courseName;

    @Excel(name = "代课老师", isImportField = "true_st")
    private String teacher;

    @Excel(name = "分数", isImportField = "true_st")
    private String score;

    @Excel(name = "附加分", isImportField = "true_st")
    private String grade;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "DataModel{" + "name='" + name + '\'' + ", sex='" + sex + '\'' + ", count='" + count + '\''
            + ", courseName='" + courseName + '\'' + ", teacher='" + teacher + '\'' + ", score='" + score + '\''
            + ", grade='" + grade + '\'' + '}';
    }
}
