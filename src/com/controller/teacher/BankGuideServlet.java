package com.controller.teacher;

import com.bean.entity.Course;
import com.bean.entity.Teacher;
import com.myutil.Pool;
import com.service.teacher.FindService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;

@WebServlet(name = "BankGuideServlet",urlPatterns = {"/BankGuideServlet"})
public class BankGuideServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        ServletContext application=getServletContext();
        Pool dbpool=(Pool)application.getAttribute("dbpool");
        Connection con=dbpool.getOneCon();
        if(null==con) {
            out.print("<script language='javascript'>alert('服务器繁忙请稍后再试!');window.history.go(-1);</script>");
        }else {
            String t_department=request.getParameter("t_department");
            ArrayList<Teacher> teacherArrayList= FindService.getTeacherManageBankService(con,t_department);
            ArrayList<Course> courseArrayList=FindService.getCourseListService(con,t_department);
            dbpool.close(con);
            request.setAttribute("teacherArrayList",teacherArrayList);
            request.setAttribute("courseArrayList",courseArrayList);
            request.getRequestDispatcher("./teacher/manageBank.jsp").forward(request, response);
        }
    }
}
