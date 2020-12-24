package com.controller.admin;

import com.myutil.Pool;
import com.service.FirstLoadService;

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

@WebServlet(name = "rootDeletelesson",urlPatterns = {"/rootDeletelesson"})
public class rootDeletelesson extends HttpServlet {
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
            String lessonuuid=request.getParameter("lessonuuid");
            String t_id=request.getParameter("t_id");
            FirstLoadService.DeletelessonService(con,lessonuuid);
            dbpool.close(con);
            response.sendRedirect("ViewCourseDetail?t_id="+java.net.URLEncoder.encode(t_id,"utf-8"));
        }
    }
}
