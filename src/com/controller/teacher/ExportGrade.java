package com.controller.teacher;

import com.bean.entity.ExportGradeModel;
import com.bean.entity.Grade;
import com.bean.entity.Student;
import com.myutil.Pool;
import com.myutil.WriteExcelFile;
import com.service.teacher.CreateService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@WebServlet(name = "ExportGrade",urlPatterns = {"/ExportGrade"})
public class ExportGrade extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        ServletContext application=getServletContext();
        Pool dbpool=(Pool)application.getAttribute("dbpool");
        Connection con=dbpool.getOneCon();
            String examuuid=request.getParameter("examuuid");
            String examname=request.getParameter("examname");
            String lessonuuid=request.getParameter("lessonuuid");
            ArrayList<Student> wantnoGrade=new ArrayList<>();
            ArrayList<Student> list_noGrade= CreateService.getNoGradeListService(con,lessonuuid);
            ArrayList<Grade> gradeslist=CreateService.getScorelistService(con,examuuid);
            ArrayList<Student> student_list=CreateService.getSpecialStuListService(con,gradeslist);
            for(int i=0;i<list_noGrade.size();i++){
                int j=0;
                for( j=0;j<gradeslist.size();j++){
                    if(list_noGrade.get(i).getS_id().equals(gradeslist.get(j).getStudentID())){
                        break;
                    }
                }
                if(j==gradeslist.size()){
                    wantnoGrade.add(list_noGrade.get(i));
                }
            }
            dbpool.close(con);
            ExportGradeModel exportGradeModel=null;
            ArrayList<ExportGradeModel> exportlist=new ArrayList<>();
            for(int i=0;i<gradeslist.size();i++){
                for(int j=0;j<student_list.size();j++){
                    if(student_list.get(j).getS_id().equals(gradeslist.get(i).getStudentID())){
                        exportGradeModel=new ExportGradeModel(student_list.get(j).getS_id(),student_list.get(j).getS_name(),gradeslist.get(i).getTotal());
                        exportlist.add(exportGradeModel);
                        break;
                    }
                }
            }
            for(int i=0;i<wantnoGrade.size();i++){
                exportGradeModel=new ExportGradeModel(wantnoGrade.get(i).getS_id(),wantnoGrade.get(i).getS_name(),0);
                exportlist.add(exportGradeModel);
            }
            File downLoadFileDir=new File(getServletContext().getRealPath("/uploadfile"));
            Date date=new Date();
            SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmssSSS");
            String fileName=sdf.format(date)+".xls";
            WriteExcelFile.writeExcel(exportlist,downLoadFileDir+File.separator+fileName);
            FileInputStream in=null;
            ServletOutputStream outputStream=null;
            try{
                response.setHeader("Content-Type","application/x-msdownload");
                response.setHeader("Content-Disposition","attachment;filename="+fileName);
                in=new FileInputStream(downLoadFileDir+File.separator+fileName);
                outputStream=response.getOutputStream();
                outputStream.flush();
                int aRead=0;
                byte [] b=new byte[1024];
                while ((aRead=in.read(b))!=-1&in!=null){
                    outputStream.write(b,0,aRead);
                }
                outputStream.flush();
                in.close();
                outputStream.close();
            }catch (Throwable e){
                e.printStackTrace();
            }
        }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
