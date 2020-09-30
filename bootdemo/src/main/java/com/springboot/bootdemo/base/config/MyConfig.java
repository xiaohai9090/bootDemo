package com.springboot.bootdemo.base.config;

import com.springboot.bootdemo.domain.Annotation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {

    @Bean
    public Annotation getAnnotation(){
        Annotation annotation = new Annotation();
        annotation.setAnnotationId(12);
        return annotation;
    }
}
