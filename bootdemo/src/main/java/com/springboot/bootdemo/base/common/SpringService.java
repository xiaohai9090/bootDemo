package com.springboot.bootdemo.base.common;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;

/**
 * Spring服务
 */

public class SpringService {
	
	private static ServletContext servletContext;
	private static WebApplicationContext webApplictionContext;
	
	public static Object getBean(String name){
		return webApplictionContext.getBean(name);
	}

    public static <T> T getBean(Class<T> tClass) {
        return webApplictionContext.getBean(tClass);
    }
	
	public static ServletContext getServletContext() {
		return servletContext;
	}

	public static void setServletContext(ServletContext servletContext) {
		SpringService.servletContext = servletContext;
		SpringService.webApplictionContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
	}

	public static ApplicationContext getApplicationContext() {
		ApplicationContext ctx = new FileSystemXmlApplicationContext("/WebRoot/WEB-INF/applicationContext.xml");
		//ApplicationContext ctx = new FileSystemXmlApplicationContext("../webapps/War/WEB-INF/applicationContext.xml");
		return ctx;
	}

	public static ApplicationContext getApplicationContext(String path) {
		ApplicationContext ctx = new FileSystemXmlApplicationContext(path);
		//ApplicationContext ctx = new FileSystemXmlApplicationContext("../webapps/War/WEB-INF/applicationContext.xml");
		return ctx;
	}


}
