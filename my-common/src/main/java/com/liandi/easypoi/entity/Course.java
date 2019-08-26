package com.liandi.easypoi.entity;

import java.util.List;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author czg
 * @date 2019/8/16 10:53
 * @description 课程
 */
@Data
@Accessors(chain = true)
public class Course {

    @Excel(name = "课程", needMerge = true, isImportField = "true_st")
    private String courseName;

    @Excel(name = "代课老师", needMerge = true, isImportField = "true_st")
    private String teacher;

    @Excel(name = "分数", needMerge = true, isImportField = "true_st")
    private String score;

    @ExcelCollection(name = "附加")
    private List<Extra> extraList;

}
