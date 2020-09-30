package com.springboot.bootdemo.domain;

import org.springframework.stereotype.Component;

@Component
public class Annotation {

    private int annotationId;

    private String annotationStr;

    public int getAnnotationId() {
        return annotationId;
    }

    public void setAnnotationId(int annotationId) {
        this.annotationId = annotationId;
    }

    public String getAnnotationStr() {
        return annotationStr;
    }

    public void setAnnotationStr(String annotationStr) {
        this.annotationStr = annotationStr;
    }
}
