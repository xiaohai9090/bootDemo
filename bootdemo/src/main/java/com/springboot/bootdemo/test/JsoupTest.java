package com.springboot.bootdemo.test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author: Queen
 * @date: 2021/3/19 11:43
 * @name: JsoupTest.class
 */
public class JsoupTest {

    public static void main(String[] args) throws Exception {
        Document document = Jsoup.connect("https://332tom.com/yazhouqingse/").get();
        Elements elements = document.getElementsByClass("video-list");
        Elements tag = elements.get(0).getElementsByTag("li");
        for (Element element : tag){
            String url = element.getElementsByTag("a").eq(0).attr("href");

            Document documentIn = Jsoup.connect("https://332tom.com/"+url).get();
            Element nr1 = documentIn.getElementById("xunlei_nr1");

            Elements nr2 = documentIn.getElementsByClass("ddv1");

            String title = nr2.get(0).getElementsByTag("h3").html();

            String downUrl = nr1.getElementsByTag("input").eq(0).attr("value");
            System.out.println(title);
            System.out.println(downUrl);
//            System.out.println();
        }


    }
}
