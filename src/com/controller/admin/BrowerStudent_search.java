package com.controller.admin;

import com.bean.entity.Student;
import com.bean.entity.Teacher;
import com.myutil.Pool;
import com.service.admin.QueryService;

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

@WebServlet(name = "BrowerStudent_search",urlPatterns = {"/BrowerStudent_search"})
public class BrowerStudent_search extends HttpServlet {
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
            String depart=request.getParameter("depart");
            String classselect=request.getParameter("classselect");
            String studentname=request.getParameter("studentname");
            ArrayList<Student> students_list_admin_search= QueryService.getSearchStudentAdminService(con,studentname);
            dbpool.close(con);
            request.setAttribute("students_list_admin",students_list_admin_search);
            request.setAttribute("depart","全部");
            request.setAttribute("classselect","全部");
            request.getRequestDispatcher("./admin/student_list.jsp").forward(request,response);
        }
    }
}
