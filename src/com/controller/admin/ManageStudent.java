package com.controller.admin;

import com.bean.entity.Majorclass;
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

@WebServlet(name = "ManageStudent",urlPatterns = {"/ManageStudent"})
public class ManageStudent extends HttpServlet {
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
            ArrayList<Majorclass> majorclass_list_admin= QueryService.getMajorClasslistService(con);
            dbpool.close(con);
            request.setAttribute("majorclass_list_admin",majorclass_list_admin);
            request.getRequestDispatcher("./admin/student_manage.jsp").forward(request,response);
        }
    }
}
