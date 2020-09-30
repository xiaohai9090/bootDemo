package com.springboot.bootdemo.test;

import com.springboot.bootdemo.domain.Generic;
import com.springboot.bootdemo.domain.Student;
import com.springboot.bootdemo.domain.Teacher;

import java.util.*;

public class ListDemo {
    public static void main(String[] args) {
        List<Integer> num = new ArrayList<>();
        for (int i = 0; i < 100; i ++){
            num.add(i * 2);
        }
        System.out.println(num);
        List<Integer> ints = new ArrayList<>();
        ints.add(null);
        ints.add(null);
        ints.add(null);
        System.out.println(ints);
    }

    public void arrayContain(){
        List<Integer> num = new ArrayList<>();
        int[] arr = new int[]{11,22,12,12,45,85,45,32,32,0,11,12,52};
        for (int i = 0; i < arr.length; i++){
            if (!num.contains(arr[i])) {
                num.add(arr[i]);
            }
        }

        for (Integer i : num){
            System.out.println(i);
        }
    }


    public void newSort(){
        Map<String,Object> objMap = new HashMap<>(3);
        objMap.put("date","2019-03-25");
        objMap.put("num",102);
        objMap.put("id",2001);

        Map<String,Object> objMap1 = new HashMap<>(3);
        objMap1.put("date","2019-08-21");
        objMap1.put("num",95);
        objMap1.put("id",2002);

        Map<String,Object> objMap2 = new HashMap<>(3);
        objMap2.put("date","2019-12-24");
        objMap2.put("num",133);
        objMap2.put("id",2003);

        Map<String,Object> objMap3 = new HashMap<>(3);
        objMap3.put("date","2019-05-08");
        objMap3.put("num",184);
        objMap3.put("id",2004);

        List<Map<String,Object>> resultMap = new ArrayList<>();
        resultMap.add(objMap);
        resultMap.add(objMap1);
        resultMap.add(objMap2);
        resultMap.add(objMap3);

        Collections.sort(resultMap,(Map a,Map b) -> a.get("date").toString().compareTo(b.get("date").toString()));

        for (Map teacher : resultMap){
            System.out.println(teacher);
        }


        GenericDemo genericDemo = new GenericDemo();
        genericDemo.getOne();
    }

    public static void studentDemo(){
        List<Student> students = new ArrayList<>();
        Student stu1 = new Student();
        stu1.setName("111asd");stu1.setId(1);

        Student stu7 = new Student();stu7.setName("11145");stu7.setId(1);

        Student stu2 = new Student();stu2.setName("11531");stu2.setId(2);

        Student stu3 = new Student();stu3.setName("111786");stu3.setId(2);

        Student stu4 = new Student();stu4.setName("aaaaa");stu4.setId(3);

        Student stu5 = new Student();stu5.setName("dddddd");stu5.setId(3);

        Student stu6 = new Student();stu6.setName("111");stu6.setId(4);
        Student stu10 = new Student();stu10.setName("fs111");stu10.setId(4);
        Student stu8 = new Student();stu8.setName("11fsf1");stu8.setId(5);
        Student stu9 = new Student();stu9.setName("11wq41");stu9.setId(5);
        students.add(stu1);
        students.add(stu2);
        students.add(stu3);
        students.add(stu4);
        students.add(stu5);
        students.add(stu6);
        students.add(stu7);
        students.add(stu8);
        students.add(stu9);
        students.add(stu10);
//        List<Student> resultList = PageUtil.startPage(students,1,5);
        for (int i = 0; i < students.size(); i++) {
            Student stu = students.get(i);
            if (stu.getId() == 3){
                students.remove(stu);
                i--;
//                continue;
            }
            System.out.println(i);
        }
//        for (Student stu : students){
//            if (stu.getId() == 3){
//                students.remove(stu);
//            }
//        }

        for (Student stu : students){
            System.out.println(stu.toString());
        }
    }
}
