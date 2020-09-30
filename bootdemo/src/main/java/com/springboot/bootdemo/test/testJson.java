package com.springboot.bootdemo.test;

import org.apache.log4j.Logger;


public class testJson {

    public static Logger logger = Logger.getLogger(testJson.class);

    public static void main(String[] args) {

        StringBuilder sb = new StringBuilder();
        sb.append("<xml>");
        sb.append("<return_code><![CDATA[FAIL]]></return_code>");
        sb.append("<return_msg><![CDATA[商户号该产品权限预开通中，请等待产品开通后重试]]></return_msg>");
        sb.append("</xml>");

//        <xml>
//        <return_code><![CDATA[FAIL]]></return_code>
//        <return_msg><![CDATA[商户号该产品权限预开通中，请等待产品开通后重试]]></return_msg>
//        </xml>

        int a = sb.toString().indexOf("FAIL");
        System.out.println(a);
    }

}
