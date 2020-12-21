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
            double[][] data= CreateService.getPictureService(con,examuuid);
            ArrayList<Grade> gradeslist=CreateService.getScorelistService(con,examuuid);
            ArrayList<Student> student_list=CreateService.getSpecialStuListService(con,gradeslist);
            dbpool.close(con);
            String imgName = BarChart_ByDatasetUtilities.generateBarChart(session,data);
            request.setAttribute("imgName",imgName);
            request.setAttribute("gradeslist",gradeslist);
            request.setAttribute("student_list",student_list);
            request.getRequestDispatcher("./teacher/ExamSituation.jsp").forward(request, response);
        }
    }
}
