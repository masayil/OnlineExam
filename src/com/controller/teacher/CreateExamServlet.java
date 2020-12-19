package com.controller.teacher;

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

@WebServlet(name = "CreateExamServlet",urlPatterns = {"/CreateExamServlet"})
public class CreateExamServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
            String lessonuuid=request.getParameter("lessonuuid");
            String paperuuid=request.getParameter("paperuuid");
            String datetimepicker1=request.getParameter("datetimepicker1");
            String datetimepicker2=request.getParameter("datetimepicker2");
            int lasttime=Integer.parseInt(request.getParameter("lasttime"));
            String examname=request.getParameter("examname");
            boolean isCreate= CreateService.CreateExam();

        }
    }

}
