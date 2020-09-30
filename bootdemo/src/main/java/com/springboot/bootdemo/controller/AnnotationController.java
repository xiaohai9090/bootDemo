package com.springboot.bootdemo.controller;

import com.springboot.bootdemo.base.config.MyConfig;
import com.springboot.bootdemo.domain.Annotation;
import com.springboot.bootdemo.service.IAnnotationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/annotation")
public class AnnotationController {

    @Autowired
    private IAnnotationService annotationService;

    @Autowired
    private MyConfig myConfig;

    @ResponseBody
    @GetMapping("/getA")
    public int GetAnnotation(){
//        iAnnotationService.getAnnotation();
        if (annotationService != null){
            return annotationService.getAnnotation();
        }else {
            return 0;
        }
    }


    @ResponseBody
    @GetMapping("/getB")
    public int GetAnnotationB(){
        if (myConfig != null){
            Annotation annotation = myConfig.getAnnotation();
            if (annotation != null){
                return 1;
            }
        }

        return 0;
    }
}
