package com.zk.software.yale;

import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Request;
import com.geccocrawler.gecco.annotation.Text;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.spider.HrefBean;
import com.geccocrawler.gecco.spider.HtmlBean;

import java.util.List;

@Gecco(matchUrl = "https://som.yale.edu/faculty-research/faculty-directory",pipelines = {"consolePipeline","listPipeline"})
public class TeachersList implements HtmlBean {
    private static final long  serialVersionUID = 1L;

    @Request
    private HttpRequest request;

    @Text
    @HtmlField(cssPath = "#block-system-main > div > div.view-content > div > h4")
    private String teacherName;

    @HtmlField(cssPath = "#block-system-main > div > div.view-content > div > h4 > a")
    private List<HrefBean> link;

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public List<HrefBean> getLink() {
        return link;
    }

    public void setLink(List<HrefBean> link) {
        this.link = link;
    }

    public HttpRequest getRequest() {
        return request;
    }

    public void setRequest(HttpRequest request) {
        this.request = request;
    }
}
