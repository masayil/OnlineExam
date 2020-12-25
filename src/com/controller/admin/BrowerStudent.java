package com.controller.admin;

import com.bean.entity.Student;
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

@WebServlet(name = "BrowerStudent",urlPatterns = {"/BrowerStudent"})
public class BrowerStudent extends HttpServlet {
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
            if("全部".equals(depart)){
                ArrayList<Student> students_list_admin_all= QueryService.getAllStudentAdminService(con);
                dbpool.close(con);
                request.setAttribute("students_list_admin",students_list_admin_all);
                request.setAttribute("depart",depart);
                request.setAttribute("classselect",classselect);
                request.getRequestDispatcher("./admin/student_list.jsp").forward(request,response);
            }else {
                ArrayList<Student> students_list_admin_special=QueryService.getSpecialStudentAdminService(con,depart,classselect);
                dbpool.close(con);
                request.setAttribute("students_list_admin",students_list_admin_special);
                request.setAttribute("depart",depart);
                request.setAttribute("classselect",classselect);
                request.getRequestDispatcher("./admin/student_list.jsp").forward(request,response);
            }
        }
    }
}
