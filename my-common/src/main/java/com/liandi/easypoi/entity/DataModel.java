package com.liandi.easypoi.entity;

import java.io.Serializable;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 * @author czg
 * @date 2019/8/16 10:52
 * @description 学生
 */
@Data
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

}
