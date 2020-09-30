package com.springboot.bootdemo.test;

import com.springboot.bootdemo.domain.Teacher;

public class TeacherExtend extends Teacher implements Cloneable {

    public TeacherExtend getTeacher(TeacherExtend teacher){

        try {
            TeacherExtend t1 = (TeacherExtend) teacher.clone();
            return t1;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }


    }
}
