package com.myutil;

import com.bean.entity.ExportGradeModel;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.*;
import java.util.ArrayList;

public class WriteExcelFile {
    private static HSSFWorkbook wb;
    public static void writeExcel(ArrayList<ExportGradeModel> result,String path){
        if(result==null){
            return;
        }
        wb=new HSSFWorkbook();
        HSSFSheet sheet=wb.createSheet("sheet1");
        HSSFRow row0=sheet.createRow(0);
        row0.createCell(0).setCellValue("学号");
        row0.createCell(1).setCellValue("姓名");
        row0.createCell(2).setCellValue("分数");
        for(int i=0;i<result.size();i++){
            HSSFRow row=sheet.createRow(i+1);
            if(result.get(i)!=null){
                HSSFCell cell0=row.createCell(0);
                cell0.setCellValue(result.get(i).getId());
                HSSFCell cell1=row.createCell(1);
                cell1.setCellValue(result.get(i).getName());
                HSSFCell cell2=row.createCell(2);
                cell2.setCellValue(result.get(i).getMyscore());
            }
        }
        ByteArrayOutputStream os=new ByteArrayOutputStream();
        try{
            wb.write(os);
        }catch (IOException e){
            e.printStackTrace();
        }
        byte [] content=os.toByteArray();
        File file=new File(path);
        OutputStream fos=null;
        try{
            fos=new FileOutputStream(file);
            fos.write(content);
            os.close();
            fos.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
