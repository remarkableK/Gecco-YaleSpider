package com.zk.software.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelWriter {
    private static HSSFWorkbook workbook = null;

    /**
     * 判断文件是否存在.
     * @param fileDir  文件路径
     * @return
     */
    public static boolean fileExist(String fileDir){
        boolean flag = false;
        File file = new File(fileDir);
        flag = file.exists();
        return flag;
    }
    /**
     * 判断文件的sheet是否存在.
     * @param fileDir   文件路径
     * @param sheetName  表格索引名
     * @return
     */
    public static boolean sheetExist(String fileDir,String sheetName) throws Exception{
        boolean flag = false;
        File file = new File(fileDir);
        //文件存在
        if(file.exists()){
            //创建workbook
            try {
                workbook = new HSSFWorkbook(new FileInputStream(file));
                //添加Worksheet（不添加sheet时生成的xls文件打开时会报错)
                HSSFSheet sheet = workbook.getSheet(sheetName);
                if(sheet!=null){
                    flag = true;
                }
            } catch (Exception e) {
                throw e;
            }
        }else{    //文件不存在
            flag = false;
        }
        return flag;
    }
    /**
     * 创建新excel.
     * @param fileDir  excel的路径
     * @param sheetName 要创建的表格索引
     * @param titleRow excel的第一行即表格头
     */
    public static void createExcel(String fileDir,String sheetName,String titleRow[]) throws Exception{
        //创建workbook
        workbook = new HSSFWorkbook();
        //添加Worksheet（不添加sheet时生成的xls文件打开时会报错)
        HSSFSheet sheet1 = workbook.createSheet(sheetName);
        //新建文件
        FileOutputStream out = null;
        try {
            //添加表头  //创建第一行
            HSSFRow row = workbook.getSheet(sheetName).createRow(0);
            for(short i = 0;i < titleRow.length;i++){
                HSSFCell cell = row.createCell(i);
                cell.setCellValue(titleRow[i]);
            }
            out = new FileOutputStream(fileDir);
            workbook.write(out);
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 删除文件.
     * @param fileDir  文件路径
     */
    public static boolean deleteExcel(String fileDir) {
        boolean flag = false;
        File file = new File(fileDir);
        // 判断目录或文件是否存在
        if (!file.exists()) {  // 不存在返回 false
            return flag;
        } else {
            // 判断是否为文件
            if (file.isFile()) {  // 为文件时调用删除文件方法
                file.delete();
                flag = true;
            }
        }
        return flag;
    }
    /**
     * 往excel中写入(已存在的数据无法写入).
     * @param fileDir    文件路径
     * @param sheetName  表格索引
     * @param object
     * @throws Exception
     */
    public static void writeToExcel(String fileDir,String sheetName,List<Map> mapList) throws Exception{
        //创建workbook
        File file = new File(fileDir);
        try {
            workbook = new HSSFWorkbook(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //流
        FileOutputStream out = null;
        HSSFSheet sheet = workbook.getSheet(sheetName);
        // 获取表格的总行数 需要加一
         int rowCount = sheet.getLastRowNum();
        // 获取表头的列数
        int columnCount = sheet.getRow(0).getLastCellNum();
        try {
            // 获得表头行对象
            HSSFRow titleRow = sheet.getRow(0);
            if(titleRow!=null){
                for(int rowId=0;rowId<mapList.size();rowId++){
                    Map map = mapList.get(rowId);
                    HSSFRow newRow=sheet.createRow(rowCount+1);
                    rowCount++;
                    //遍历表头
                    for (short columnIndex = 0; columnIndex < columnCount; columnIndex++) {
                        String mapKey = titleRow.getCell(columnIndex).toString().trim().toString().trim();
                        HSSFCell cell = newRow.createCell(columnIndex);
                        cell.setCellValue(map.get(mapKey)==null ? null : map.get(mapKey).toString());
                    }
                }
            }
            out = new FileOutputStream(fileDir);
            workbook.write(out);
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        //判断文件是否存在
        System.out.println(ExcelWriter.fileExist("E:/test2.xls"));
        //创建文件
        String title[] = {"id","name","password"};
        try {
            ExcelWriter.createExcel("E:/test2.xls","sheet1",title);
        }catch (Exception e){
            e.printStackTrace();
        }
        List<Map> list=new ArrayList<Map>();
        Map<String,String> map=new HashMap<String,String>(3);
        map.put("id", "111");
        map.put("name", "张三");
        map.put("password", "111！@#");

        Map<String,String> map2=new HashMap<String,String>(3);
        map2.put("id", "222");
        map2.put("name", "李四");
        map2.put("password", "222！@#");
        list.add(map);
        list.add(map2);
        try {
            ExcelWriter.writeToExcel("E:/test2.xls","sheet1",list);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}