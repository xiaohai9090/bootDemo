package com.springboot.bootdemo.domain;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

public class Something {
    private Integer id;
    private String name;
    private String name2;
    private List<Student> students = new ArrayList<>();


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

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public void clearStudent(Student student){
        students.remove(student);
    }
}
