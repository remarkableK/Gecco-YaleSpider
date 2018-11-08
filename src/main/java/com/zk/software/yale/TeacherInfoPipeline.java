package com.zk.software.yale;

import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.pipeline.Pipeline;
import com.zk.software.excel.ExcelWriter;


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@PipelineName("teacherInfoPipeline")
public class TeacherInfoPipeline implements Pipeline<TeacherInfo> {
    /**
     * 写入文件的路径
     */
   private String filePath = "D:\\Yale.xls";
    @Override
    public void process(TeacherInfo teacherInfo){
        //如果文件已经存在
        if (fileExist(filePath)){
            List<Map> list = new ArrayList<>();
            list.add(createMap(teacherInfo));
            try {
                ExcelWriter.writeToExcel(filePath,"sheet1",list);
            }catch (Exception e){
                e.printStackTrace();
            }
        }else{
            //表头
            String title[] = {"name","association","email","expertise","webPage","background"};
            try {
                ExcelWriter.createExcel(filePath,"sheet1",title);
            }catch (Exception e){
                e.printStackTrace();
            }
            List<Map> list = new ArrayList<>();
            list.add(createMap(teacherInfo));
            try {
                ExcelWriter.writeToExcel(filePath,"sheet1",list);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public static boolean fileExist(String fileDir){
        boolean flag;
        File file = new File(fileDir);
        flag = file.exists();
        return  flag;
    }
    public Map createMap(TeacherInfo teacherInfo){
        Map<String,String> map = new HashMap<>(6);
        map.put("name",teacherInfo.getName());
        map.put("association",teacherInfo.getAssociation());
        map.put("email",teacherInfo.getEmail());
        //研究领域是List型的
        String expertiseSet = "";
        for (String expertise:teacherInfo.getExpertise()){
            expertiseSet = expertiseSet + expertise;
        }
        map.put("expertise",expertiseSet);
        map.put("webPage",teacherInfo.getWebPage());
        map.put("background",teacherInfo.getBackground());
        return map;
    }
}
