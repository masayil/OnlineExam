package com.controller.admin;

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

@WebServlet(name = "SingleImportStudent",urlPatterns = {"/SingleImportStudent"})
public class SingleImportStudent extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        ServletContext application=getServletContext();
        Pool dbpool=(Pool)application.getAttribute("dbpool");
        Connection con=dbpool.getOneCon();
        if(null==con) {
            response.getWriter().print("busy");
        }else {
            String studentid=request.getParameter("studentid");
            String studentname=request.getParameter("studentname");
            String studentpwd=request.getParameter("studentpwd");
            String sex=request.getParameter("sex");
            String college=request.getParameter("college");
            String depart=request.getParameter("depart");
            String major=request.getParameter("major");
            String classselect=request.getParameter("classselect");
            if(QueryService.addstuSingleService(con,studentid,studentname,studentpwd,sex,college,depart,classselect,major)){
                dbpool.close(con);
                response.getWriter().print("yes");
            }else {
                dbpool.close(con);
                response.getWriter().print("no");
            }
        }
    }
}
