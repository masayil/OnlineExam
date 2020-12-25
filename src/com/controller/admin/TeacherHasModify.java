package com.controller.admin;

import com.myutil.Pool;
import com.service.admin.UpdateService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

@WebServlet(name = "TeacherHasModify",urlPatterns = {"/TeacherHasModify"})
public class TeacherHasModify extends HttpServlet {
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
            String teacherid=request.getParameter("teacherid");
            String teachername=request.getParameter("teachername");
            String sex=request.getParameter("sex");
            String depart=request.getParameter("depart");
            if(UpdateService.updateTeaService(con,teacherid,teachername,sex,depart)){
                dbpool.close(con);
                response.getWriter().print("yes");
            }else {
                dbpool.close(con);
                response.getWriter().print("no");
            }
        }
    }
}
