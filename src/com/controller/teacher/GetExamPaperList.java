package com.controller.teacher;

import com.bean.entity.Student;
import com.myutil.Pool;
import com.service.teacher.CreateService;
import com.service.teacher.FindService;

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

@WebServlet(name = "GetExamPaperList",urlPatterns = {"/GetExamPaperList"})
public class GetExamPaperList extends HttpServlet {
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
            ArrayList<Student> studentIDlist= CreateService.getStudentIDService(con,examuuid);
            dbpool.close(con);
            request.setAttribute("examname", examname);
            request.setAttribute("examuuid", examuuid);
            request.setAttribute("studentIDlist", studentIDlist);
            request.getRequestDispatcher("./teacher/markExam_step1_studentlist.jsp").forward(request, response);
        }
    }
}
