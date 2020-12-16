package com.controller;

import com.bean.entity.*;
import com.myutil.Pool;
import com.service.FirstLoadService;
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

@WebServlet(name = "FirstLoad",urlPatterns = {"/FirstLoad"})
public class FirstLoad extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        String status = request.getParameter("status");
        ServletContext application=getServletContext();
        Pool dbpool=(Pool)application.getAttribute("dbpool");
        Connection con=dbpool.getOneCon();
        if(null==con) {
            out.print("<script language='javascript'>alert('服务器繁忙请稍后再试!');window.location.href='welcome.jsp';</script>");
        }else {
            switch (status){
                case "student":
                    Student student=(Student)session.getAttribute("student");
                    String s_id = student.getS_id();
                    String s_class=student.getS_class();
                    ArrayList<Newlesson> Newlessonlist_class = FirstLoadService.getnewlesson1Service(con, s_class);
                    ArrayList<Newlesson> Newlessonlist_other = FirstLoadService.getnewlesson2Service(con, s_id);
                    ArrayList<Teacher> Teacherlist = FirstLoadService.geteacherlistService(con);
                    dbpool.close(con);
                    request.setAttribute("Newlessonlist_class", Newlessonlist_class);
                    request.setAttribute("Newlessonlist_other", Newlessonlist_other);
                    request.setAttribute("Teacherlist", Teacherlist);
                    request.getRequestDispatcher("displayCourse.jsp").forward(request, response);
                    break;
                case "teacher":
                    Teacher teacher=(Teacher)session.getAttribute("teacher");
                    String t_id=teacher.getT_id();
                    ArrayList<Newlesson> teacherlesson=FirstLoadService.teachernewLessonService(con,t_id);
                    ArrayList<Majorclass> classlist= FindService.majorclassService(con);
                    ArrayList<Course> courselist=FindService.courseService(con,teacher.getT_department());
                    dbpool.close(con);
                    request.setAttribute("teacherlesson", teacherlesson);
                    request.setAttribute("classlist", classlist);
                    request.setAttribute("courselist", courselist);
                    request.getRequestDispatcher("./teacher/t_displaycourse.jsp").forward(request, response);
                    break;
            }
        }
    }
}
