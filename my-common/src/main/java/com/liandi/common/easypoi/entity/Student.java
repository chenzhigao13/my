package com.liandi.common.easypoi.entity;

import java.util.List;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author czg
 * @date 2019/8/16 10:52
 * @description 学生
 */
@Data
@Accessors(chain = true)
public class Student {

    @Excel(name = "姓名", needMerge = true, isImportField = "true_st")
    private String name;

    @Excel(name = "性别", needMerge = true, isImportField = "true_st")
    private String sex;

    @Excel(name = "总分", needMerge = true, isImportField = "true_st")
    private String count;

    @ExcelCollection(name = "成绩")
    private List<Course> courseList;

}
