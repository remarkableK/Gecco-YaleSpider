package com.zk.software.yale;

import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.request.HttpGetRequest;
import org.apache.log4j.BasicConfigurator;

public class Main {
    public static void main(String[] args) {
        BasicConfigurator.configure();
        HttpGetRequest start = new HttpGetRequest("https://som.yale.edu/faculty-research/faculty-directory");
        start.setCharset("GBK");

        GeccoEngine.create()
                //Gecco搜索的包路径
                .classpath("com.zk.software.yale")
                //开始抓取的页面地址
                .start(start)
                //开启几个爬虫线程
                .thread(1)
                //单个爬虫每次抓取完一个请求后的间隔时间
                .interval(5000)
                .run();
    }

}
