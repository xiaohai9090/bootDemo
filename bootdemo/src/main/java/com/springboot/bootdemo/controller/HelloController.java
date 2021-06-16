package com.springboot.bootdemo.controller;

import com.alibaba.fastjson.JSON;
import com.springboot.bootdemo.domain.Something;
import com.springboot.bootdemo.domain.Student;
import com.springboot.bootdemo.service.impl.PoiService;
import com.springboot.bootdemo.test.GenericDemo;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.basic.BasicTreeUI;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/hello")
public class HelloController {

    private static final Logger LOG = Logger.getLogger(HelloController.class);

    @Autowired
    private PoiService poiService;

    @ModelAttribute("something")
    public Something get(){ return new Something();}

    @ModelAttribute("addStudents")
    public List<Student> addStudents(){ return new ArrayList<>();}

    @RequestMapping("/first")
    @ResponseBody
    public String first(@RequestHeader(value = "User-Agent") String userAgent){


        if(userAgent.contains("Linux"))
        {
            return "welcomeToLinux";
        }
        else if(userAgent.contains("iPhone"))
        {
            return "welcomeToIos";
        }
        else if(userAgent.contains("Android"))
        {
            return "welcomeToAndroid";
        }
        return "welcome";

    }


    @ResponseBody
    @RequestMapping("/checkName")
    public String checkName(String name){
        if ("rookie".equals(name)){
            return "true";
        }
        return "false";
    }

    @ResponseBody
    @RequestMapping("/getIpAddress")
    public String getIpAddress(HttpServletRequest request){
        String remoteAddr = request.getRemoteAddr();
        String forwarded = request.getHeader("X-Forwarded-For");
        String realIp = request.getHeader("X-Real-IP");

        String ip = null;
        if (realIp == null) {
            if (forwarded == null) {
                ip = remoteAddr;
            } else {
                ip = remoteAddr + "/" + forwarded.split(",")[0];
            }
        } else {
            if (realIp.equals(forwarded)) {
                ip = realIp;
            } else {
                if(forwarded != null){
                    forwarded = forwarded.split(",")[0];
                }
                ip = realIp + "/" + forwarded;
            }
        }

        return ip;
    }


    @RequestMapping("/turnHtml")
    public String turnHtml(Something st,Model model){
        st.setName("2");
//        st.setName2("1");

        model.addAttribute("Something",st);
        return "index";
    }


    @ResponseBody
    @RequestMapping("/test")
    public String testRequest(Something st){
        if (st.getName() != null){
            LOG.info(st.getName());
        }
        if (st.getName2() != null){
            LOG.info(st.getName2());
        }
        if (st.getId() != null){
            LOG.info(st.getId());
        }
        return st.getName();
    }


    @ResponseBody
    @RequestMapping("/whileTest")
    public Integer whileTest(int flag){
        try {
            for (int i = 0; i < 10; i++){
                Thread.sleep(1000);
                LOG.info(flag);
                flag ++;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return flag;
    }


    @RequestMapping("/testUpload")
    public String testUpload(){
        return "upload";
    }


    @RequestMapping("/inputPage")
    public String inputPage(){
        return "input";
    }

    @RequestMapping("/input")
    public void input(String clearType){

        LOG.info(clearType);
    }


    @RequestMapping("querySomeThing")
    public String querySomeThing(Something something,Model model){
        if (something == null || something.getId() == null) {
            something = new Something();
            something.setId(1);
            something.setName("东西1");
            something.setName2("名字123");

            Student stu1 = new Student();
            stu1.setId(11);
            stu1.setName("学生1");
            stu1.setBirthday(new Date());

            Student stu2 = new Student();
            stu2.setId(55);
            stu2.setName("学生2号");
            stu2.setBirthday(new Date());


            List<Student> studentList = new ArrayList<>();
            studentList.add(stu1);
            studentList.add(stu2);

            something.setStudents(studentList);

        }
        model.addAttribute("someThing",something);
        model.addAttribute("students",something.getStudents());

        return "someThingInfo";
    }


//    @ResponseBody
    @RequestMapping("/getSomeThing")
    public String getSomeThing(Something something,Model model,String addStudents){
        System.out.println(something.toString());
//        System.out.println(index);
        List<Student> addStudent = JSON.parseArray(addStudents,Student.class);
        something.getStudents().addAll(addStudent);
        return querySomeThing(something,model);
    }

    @RequestMapping("/download")
    public void downloadPayhistoryList(HttpServletResponse response){

        System.out.println(11111);

        try {
            poiService.exportDateExcel(response);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @GetMapping("/t1")
    public void t1(@RequestParam("id") String id) {
        System.out.println("t1" + id);
    }

    @GetMapping("/t2")
    public void t2(@Value("id") String id) {
        System.out.println("t2" + id);
    }

    @GetMapping("/t3")
    public void t3(@RequestBody String id) {
        System.out.println("t3" + id);
    }

    @GetMapping("/t4")
    public void t4(int id) {
        System.out.println("t4" + id);
    }
}
