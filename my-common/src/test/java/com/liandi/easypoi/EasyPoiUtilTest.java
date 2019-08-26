package com.liandi.easypoi;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.google.common.collect.Lists;
import com.liandi.easypoi.entity.Course;
import com.liandi.easypoi.entity.DataModel;
import com.liandi.easypoi.entity.Extra;
import com.liandi.easypoi.entity.Student;
import com.liandi.easypoi.util.EasyPoiUtil;

import cn.afterturn.easypoi.excel.entity.ImportParams;

public class EasyPoiUtilTest {

    @Test
    public void testImportExcel() {

        ImportParams params = EasyPoiUtil.getImportParams();
        params.setHeadRows(3);
        List<Student> studentList = EasyPoiUtil.importExcel("D://testEasyPoi.xlsx", Student.class, params);

        System.out.println("studentList：" + studentList);

    }

    @Test
    public void importExcel() {

        List<DataModel> dataModelList =
            EasyPoiUtil.importExcel("D://testEasyPoi.xlsx", DataModel.class, EasyPoiUtil.getImportParams());

        System.out.println("dataModelList：" + dataModelList);

        List<Student> studentList = Lists.newArrayListWithCapacity(dataModelList.size());
        dataModelList.forEach(dataModel -> {
            if (StringUtils.isBlank(dataModel.getName())) {

                if (studentList.isEmpty()) {
                    throw new RuntimeException("名称不能为空");
                }

                List<Course> courseList = studentList.get(studentList.size() - 1).getCourseList();
                String courseName = dataModel.getCourseName();
                if (StringUtils.isBlank(courseName)) {

                    Course course = courseList.get(courseList.size() - 1);

                    List<Extra> extraList = course.getExtraList();
                    extraList.add(new Extra().setGrade(dataModel.getGrade()));

                }

                courseList.add(new Course().setScore(dataModel.getScore()).setCourseName(dataModel.getCourseName())
                    .setTeacher(dataModel.getTeacher())
                    .setExtraList(Lists.newArrayList(new Extra().setGrade(dataModel.getGrade()))));

            }
            List<Course> courseList = Lists.newArrayList(new Course().setCourseName(dataModel.getCourseName())
                .setScore(dataModel.getScore()).setTeacher(dataModel.getTeacher())
                .setExtraList(Lists.newArrayList(new Extra().setGrade(dataModel.getGrade()))));
            studentList.add(new Student().setName(dataModel.getName()).setCount(dataModel.getCount())
                .setSex(dataModel.getSex()).setCourseList(courseList));
        });

        System.out.println("studentList：" + studentList);

    }
}