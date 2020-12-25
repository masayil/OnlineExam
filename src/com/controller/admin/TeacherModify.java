package com.controller.admin;

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

@WebServlet(name = "TeacherModify",urlPatterns = {"/TeacherModify"})
public class TeacherModify extends HttpServlet {
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
            String teacherid=request.getParameter("teacherid");
            String depart=request.getParameter("depart");
            String type=request.getParameter("type");
            Teacher thisteacher= QueryService.getThisTeacherService(con,teacherid);
            dbpool.close(con);
            request.setAttribute("thisteacher",thisteacher);
            request.setAttribute("depart",depart);
            request.setAttribute("type",type);
            request.getRequestDispatcher("./admin/teacher_modify.jsp").forward(request, response);
        }
    }
}
