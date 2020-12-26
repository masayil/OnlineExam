package com.controller.teacher;

import com.bean.entity.Grade;
import com.bean.entity.Student;
import com.chart.BarChart_ByDatasetUtilities;
import com.myutil.Pool;
import com.service.teacher.CreateService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

@WebServlet(name = "ExamSituation",urlPatterns = {"/ExamSituation"})
public class ExamSituation extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        ServletContext application=getServletContext();
        Pool dbpool=(Pool)application.getAttribute("dbpool");
        Connection con=dbpool.getOneCon();
        if(null==con) {
            out.print("<script language='javascript'>alert('服务器繁忙请稍后再试!');window.history.go(-1);</script>");
        }else {
            String examuuid=request.getParameter("examuuid");
            String examname=request.getParameter("examname");
            String lessonuuid=request.getParameter("lessonuuid");
            ArrayList<Student> wantnoGrade=new ArrayList<>();
            ArrayList<Student> list_noGrade=CreateService.getNoGradeListService(con,lessonuuid);
            double[][] data= CreateService.getPictureService(con,examuuid);
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
            String imgName = BarChart_ByDatasetUtilities.generateBarChart(session,data);
            double average=0;
            double totalscore=0;
            for(int i=0;i<gradeslist.size();i++){
                totalscore+=gradeslist.get(i).getTotal();
            }
            average=totalscore/gradeslist.size();
            request.setAttribute("imgName",imgName);
            request.setAttribute("gradeslist",gradeslist);
            request.setAttribute("examname",examname);
            request.setAttribute("student_list",student_list);
            request.setAttribute("wantnoGrade",wantnoGrade);
            request.setAttribute("average",average);
            request.setAttribute("examuuid",examuuid);
            request.setAttribute("lessonuuid",lessonuuid);
            request.getRequestDispatcher("./teacher/ExamSituation.jsp").forward(request, response);
        }
    }
}
