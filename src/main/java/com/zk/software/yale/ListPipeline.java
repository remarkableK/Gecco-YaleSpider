package com.zk.software.yale;

import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.pipeline.Pipeline;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.scheduler.SchedulerContext;
import com.geccocrawler.gecco.spider.HrefBean;

import java.util.List;

@PipelineName("listPipeline")
public class ListPipeline implements Pipeline<TeachersList> {
    @Override
    public void process(TeachersList teachersList){
        List<HrefBean> teachers = teachersList.getLink();
        for (HrefBean teacher:teachers){
            String url = teacher.getUrl();
            String name= teacher.getTitle();
            HttpRequest currentRequest = teachersList.getRequest();
            //等待进一步爬取
            SchedulerContext.into(currentRequest.subRequest(url));
        }
    }
}
