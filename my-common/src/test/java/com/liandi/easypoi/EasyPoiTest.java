package com.liandi.easypoi;

import java.io.File;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.google.common.collect.Lists;
import com.liandi.common.easypoi.entity.Course;
import com.liandi.common.easypoi.entity.DataModel;
import com.liandi.common.easypoi.entity.Extra;
import com.liandi.common.easypoi.entity.Student;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;

public class EasyPoiTest {

    @Test
    public void importExcel() {

        ImportParams params = new ImportParams();
        params.setHeadRows(1);
        params.setNeedVerify(true);

        List<DataModel> dataModelList =
            ExcelImportUtil.importExcel(new File("D://testEasyPoi.xlsx"), DataModel.class, params);

        List<Student> studentList = Lists.newArrayListWithCapacity(dataModelList.size());

        for (DataModel dataModel : dataModelList) {
            if (StringUtils.isBlank(dataModel.getName())) {

                if (studentList.isEmpty()) {
                    throw new RuntimeException("名称不能为空");
                }

                List<Course> courseList = studentList.get(studentList.size() - 1).getCourseList();
                if (StringUtils.isBlank(dataModel.getCourseName())) {

                    Course course = courseList.get(courseList.size() - 1);

                    List<Extra> extraList = course.getExtraList();
                    extraList.add(new Extra().setGrade(dataModel.getGrade()));

                    continue;
                }

                courseList.add(new Course().setScore(dataModel.getScore()).setCourseName(dataModel.getCourseName())
                    .setTeacher(dataModel.getTeacher())
                    .setExtraList(Lists.newArrayList(new Extra().setGrade(dataModel.getGrade()))));

                continue;

            }
            List<Course> courseList = Lists.newArrayList(new Course().setCourseName(dataModel.getCourseName())
                .setScore(dataModel.getScore()).setTeacher(dataModel.getTeacher())
                .setExtraList(Lists.newArrayList(new Extra().setGrade(dataModel.getGrade()))));
            studentList.add(new Student().setName(dataModel.getName()).setCount(dataModel.getCount())
                .setSex(dataModel.getSex()).setCourseList(courseList));
        }

        System.out.println("studentList：" + studentList);

    }
}