package com.zk.software.yale;

import com.geccocrawler.gecco.annotation.*;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.spider.HtmlBean;
import lombok.Data;

import javax.swing.text.html.CSS;
import java.util.List;

@Data
@Gecco(matchUrl = "https://som.yale.edu/faculty/{name}",pipelines = {"consolePipeline", "teacherInfoPipeline"}, timeout = 10000)
public class TeacherInfo implements HtmlBean {
    private static final long serialVersionUID = 2L;
    @Request
    HttpRequest request;


    /**
     * 名字
     */
    @Text
    @HtmlField(cssPath = "#page-title")
    private String name;
    /**
     * 职称
     */
    @Text
    @HtmlField(cssPath = "#main > div.site-prefix > header > hgroup > h2")
    private String association;
    /**
     * 邮箱
     */
    @Text
    @HtmlField(cssPath = "#block-system-main > div > section > div.panel--three.first > ul > li.email > a")
    private String email;
    /**
     * 研究领域
     */
    @Text
    @HtmlField(cssPath = "div.faculty--profile > ul > li >a")
    private List<String> expertise;
    /**
     * 个人主页地址
     */
    @Href
    @HtmlField(cssPath = "#block-system-main > div > section > div.panel--three.first > ul > li.url > a")
    private String webPage;
    /**
     * 个人背景经历
     */
    @Text
    @HtmlField(cssPath = "div.faculty--profile > div > p")
    private String background;

}
