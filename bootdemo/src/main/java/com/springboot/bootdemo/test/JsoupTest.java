package com.springboot.bootdemo.test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * @author: Queen
 * @date: 2021/3/19 11:43
 * @name: JsoupTest.class
 */
public class JsoupTest {

    public static void main(String[] args) throws Exception {
        Document document = Jsoup.connect("https://list.jd.com/list.html?cat=670,729,2603").get();

        Element jGoodsList = document.getElementById("J_goodsList");
        System.out.println(jGoodsList.html());
    }
}
