package com.zk.software.yale;

import com.geccocrawler.gecco.spider.HtmlBean;
import lombok.Data;

@Data
public class Teacher implements HtmlBean {
     private String title;
     private String url;
}
