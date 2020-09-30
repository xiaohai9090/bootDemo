package com.springboot.bootdemo.service.impl;

import com.springboot.bootdemo.dao.AnnotationDao;
import com.springboot.bootdemo.service.IAnnotationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnnotationService implements IAnnotationService {

    @Autowired
    private AnnotationDao annotationDao;

    @Override
    public int getAnnotation() {
        if (annotationDao != null){
            return 20;
        }
        return 10;
    }
}
