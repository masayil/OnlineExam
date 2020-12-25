package com.myutil;

import com.bean.entity.QuestionBank;
import com.bean.entity.Teacher;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class ReadExcelFile {
    private int totalRows=0;
    private int totalCells=0;
    private String errorMsg;

    public ReadExcelFile() {
    }
    public int getTotalCells(){
        return totalCells;
    }
    public int getTotalRows(){
        return totalRows;
    }
    public String getErrorMsg(){
        return errorMsg;
    }
    /*
    * 验证
    * */
    public boolean validateExcel(String filePath){
        if(filePath==null||!(isExcel2003(filePath)||isExcel2007(filePath))){
            errorMsg="文件名不是xls";
            return false;
        }
        return true;
    }
    public static boolean isExcel2003(String filePath){
        return filePath.matches("^.+\\.(?i)(xls)$");
    }
    public static boolean isExcel2007(String filePath){
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }

    /*
    * 读excel文件，获取信息合集
    * */
    public List<QuestionBank> getExcelInfo1(File mFile,String teacherid){
        String fileName=mFile.getName();
        List<QuestionBank> questionBankList=null;
        try{
            if(!validateExcel(fileName)){
                return null;
            }
            boolean isExcel2003=true;
            if(isExcel2007(fileName)){
                isExcel2003=false;
            }
            questionBankList=createExcel1(mFile,isExcel2003,teacherid);
        }catch (Exception e){
            e.printStackTrace();
        }
        return questionBankList;
    }

    public List<Teacher> getExcelInfoTeacherlist(File mFile){
        String fileName=mFile.getName();
        List<Teacher> teacherList=null;
        try{
            if(!validateExcel(fileName)){
                return null;
            }
            boolean isExcel2003=true;
            if(isExcel2007(fileName)){
                isExcel2003=false;
            }
            teacherList=createExcelInfoTeacherlist(mFile,isExcel2003);
        }catch (Exception e){
            e.printStackTrace();
        }
        return teacherList;
    }

    private List<Teacher> createExcelInfoTeacherlist(File mFile, boolean isExcel2003) {
        List<Teacher> teacherList=null;
        try{
            Workbook wb=null;
            if(isExcel2003){
                wb=new HSSFWorkbook(new FileInputStream(mFile));
            }else{
                wb=new XSSFWorkbook(mFile);
            }
            teacherList=readExcelInfoTeacherlist(wb);
        }catch (Exception e){
            e.printStackTrace();
        }
        return teacherList;
    }


    public List<QuestionBank> createExcel1(File mFile,boolean isExcel2003,String teacherid){
        List<QuestionBank> questionBankList=null;
        try{
            Workbook wb=null;
            if(isExcel2003){
                wb=new HSSFWorkbook(new FileInputStream(mFile));
            }else{
                wb=new XSSFWorkbook(mFile);
            }
            questionBankList=readExcelValue1(wb,teacherid);
        }catch (Exception e){
            e.printStackTrace();
        }
        return questionBankList;
    }
    private List<Teacher> readExcelInfoTeacherlist(Workbook wb) {
        List<Teacher> teacherList=new ArrayList<>();
        Sheet sheet=wb.getSheetAt(0);
        this.totalRows=sheet.getPhysicalNumberOfRows();
        if(totalRows>1&&sheet.getRow(0)!=null){
            this.totalCells=sheet.getRow(0).getPhysicalNumberOfCells();
        }
        for(int r=1;r<totalRows;r++){
            Row row=sheet.getRow(r);
            if(row==null){
                continue;
            }
            Teacher teacher=new Teacher();
            for(int c=0;c<this.totalCells;c++){
                Cell cell=row.getCell(c);
                if(null!=cell){
                    if(c==0){
                        int k= (int) cell.getNumericCellValue();
                        teacher.setT_id(String.valueOf(k));
                    }else if(c==1){
                        teacher.setT_name(cell.getStringCellValue());
                    }else if(c==2){
                        teacher.setT_password(cell.getStringCellValue());
                    }else if(c==3){
                        teacher.setT_sex(cell.getStringCellValue());
                    }else if(c==4){
                        teacher.setT_department(cell.getStringCellValue());
                    }else if(c==5){
                        teacher.setT_college(cell.getStringCellValue());
                    }
                }
            }
            teacherList.add(teacher);
        }
        return teacherList;
    }

    public List<QuestionBank> readExcelValue1(Workbook wb,String teacherid){
        String time=Generatetime.gettime();
        List<QuestionBank> questionBankList=new ArrayList<QuestionBank>();
        /*
         * 单选题
         * */
        Sheet sheet2=wb.getSheetAt(1);
        this.totalRows=sheet2.getPhysicalNumberOfRows();
        if(totalRows>1&&sheet2.getRow(0)!=null){
            this.totalCells=sheet2.getRow(0).getPhysicalNumberOfCells();
        }
        for(int r=1;r<totalRows;r++){
            Row row=sheet2.getRow(r);
            if(row==null){
                continue;
            }
            QuestionBank questionBank=new QuestionBank();
            for(int c=0;c<this.totalCells;c++){
                Cell cell=row.getCell(c);
                if(null!=cell){
                    if(c==0){
                        questionBank.setQuestionBank_course(cell.getStringCellValue());
                    }else if(c==1){
                        questionBank.setQuestionBank_point(cell.getStringCellValue());
                    }else if(c==2){
                        questionBank.setQuestionBank_type((int) cell.getNumericCellValue());
                    }else if(c==3){
                        questionBank.setQuestionBank_title(cell.getStringCellValue());
                    }else if(c==4){
                        questionBank.setQuestionBank_option1(cell.getStringCellValue());
                    }else if(c==5){
                        questionBank.setQuestionBank_option2(cell.getStringCellValue());
                    }else if(c==6){
                        questionBank.setQuestionBank_option3(cell.getStringCellValue());
                    }else if(c==7){
                        questionBank.setQuestionBank_option4(cell.getStringCellValue());
                    }else if(c==8){
                        questionBank.setQuestionBank_answer(cell.getStringCellValue());
                    }
                }
            }
            questionBank.setQuestionBank_serialNumber(1);
            questionBank.setQuestionBank_createDate(time);
            questionBank.setQuestionBank_titleimage("");
            questionBank.setQuestionBank_creatorID(teacherid);
            questionBankList.add(questionBank);
        }
        /*
        * 多选题
        * */

        Sheet sheet3=wb.getSheetAt(2);
        this.totalRows=sheet3.getPhysicalNumberOfRows();
        if(totalRows>1&&sheet3.getRow(0)!=null){
            this.totalCells=sheet3.getRow(0).getPhysicalNumberOfCells();
        }
        for(int r=1;r<totalRows;r++){
            Row row=sheet3.getRow(r);
            if(row==null){
                continue;
            }
            QuestionBank questionBank=new QuestionBank();
            for(int c=0;c<this.totalCells;c++){
                Cell cell=row.getCell(c);
                if(null!=cell){
                    if(c==0){
                        questionBank.setQuestionBank_course(cell.getStringCellValue());
                    }else if(c==1){
                        questionBank.setQuestionBank_point(cell.getStringCellValue());
                    }else if(c==2){
                        questionBank.setQuestionBank_type((int) cell.getNumericCellValue());
                    }else if(c==3){
                        questionBank.setQuestionBank_title(cell.getStringCellValue());
                    }else if(c==4){
                        questionBank.setQuestionBank_option1(cell.getStringCellValue());
                    }else if(c==5){
                        questionBank.setQuestionBank_option2(cell.getStringCellValue());
                    }else if(c==6){
                        questionBank.setQuestionBank_option3(cell.getStringCellValue());
                    }else if(c==7){
                        questionBank.setQuestionBank_option4(cell.getStringCellValue());
                    }else if(c==8){
                        questionBank.setQuestionBank_answer(cell.getStringCellValue());
                    }
                }
            }
            questionBank.setQuestionBank_serialNumber(1);
            questionBank.setQuestionBank_createDate(time);
            questionBank.setQuestionBank_titleimage("");
            questionBank.setQuestionBank_creatorID(teacherid);
            questionBankList.add(questionBank);
        }
        /*
        * 判断题
        * */
        Sheet sheet4=wb.getSheetAt(3);
        this.totalRows=sheet4.getPhysicalNumberOfRows();
        if(totalRows>1&&sheet4.getRow(0)!=null){
            this.totalCells=sheet4.getRow(0).getPhysicalNumberOfCells();
        }
        for(int r=1;r<totalRows;r++){
            Row row=sheet4.getRow(r);
            if(row==null){
                continue;
            }
            QuestionBank questionBank=new QuestionBank();
            for(int c=0;c<this.totalCells;c++){
                Cell cell=row.getCell(c);
                if(null!=cell){
                    if(c==0){
                        questionBank.setQuestionBank_course(cell.getStringCellValue());
                    }else if(c==1){
                        questionBank.setQuestionBank_point(cell.getStringCellValue());
                    }else if(c==2){
                        questionBank.setQuestionBank_type((int) cell.getNumericCellValue());
                    }else if(c==3){
                        questionBank.setQuestionBank_title(cell.getStringCellValue());
                    }else if(c==4){
                        questionBank.setQuestionBank_answer(cell.getStringCellValue());
                    }
                }
            }
            questionBank.setQuestionBank_serialNumber(1);
            questionBank.setQuestionBank_createDate(time);
            questionBank.setQuestionBank_titleimage("");
            questionBank.setQuestionBank_option1("");
            questionBank.setQuestionBank_option2("");
            questionBank.setQuestionBank_option3("");
            questionBank.setQuestionBank_option4("");
            questionBank.setQuestionBank_creatorID(teacherid);
            questionBankList.add(questionBank);
        }

        /*
        * 简答题
        * */
        Sheet sheet5=wb.getSheetAt(4);
        this.totalRows=sheet5.getPhysicalNumberOfRows();
        if(totalRows>1&&sheet5.getRow(0)!=null){
            this.totalCells=sheet5.getRow(0).getPhysicalNumberOfCells();
        }
        for(int r=1;r<totalRows;r++){
            Row row=sheet5.getRow(r);
            if(row==null){
                continue;
            }
            QuestionBank questionBank=new QuestionBank();
            for(int c=0;c<this.totalCells;c++){
                Cell cell=row.getCell(c);
                if(null!=cell){
                    if(c==0){
                        questionBank.setQuestionBank_course(cell.getStringCellValue());
                    }else if(c==1){
                        questionBank.setQuestionBank_point(cell.getStringCellValue());
                    }else if(c==2){
                        questionBank.setQuestionBank_type((int) cell.getNumericCellValue());
                    }else if(c==3){
                        questionBank.setQuestionBank_title(cell.getStringCellValue());
                    }else if(c==4){
                        questionBank.setQuestionBank_answer(cell.getStringCellValue());
                    }
                }
            }
            questionBank.setQuestionBank_serialNumber(1);
            questionBank.setQuestionBank_createDate(time);
            questionBank.setQuestionBank_titleimage("");
            questionBank.setQuestionBank_option1("");
            questionBank.setQuestionBank_option2("");
            questionBank.setQuestionBank_option3("");
            questionBank.setQuestionBank_option4("");
            questionBank.setQuestionBank_creatorID(teacherid);
            questionBankList.add(questionBank);
        }

        return questionBankList;
    }
}
