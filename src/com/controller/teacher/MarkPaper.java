package com.controller.teacher;

import com.bean.entity.Paper;
import com.myutil.Pool;
import com.service.teacher.CreateService;

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

@WebServlet(name = "MarkPaper",urlPatterns = {"/MarkPaper"})
public class MarkPaper extends HttpServlet {
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
            String studentid=request.getParameter("studentid");
            String studentname=request.getParameter("studentname");
            ArrayList<Paper> papers= CreateService.markStuPapersService(con,examuuid,studentid);
            dbpool.close(con);
            request.setAttribute("examname", examname);
            request.setAttribute("examuuid", examuuid);
            request.setAttribute("studentname", studentname);
            request.setAttribute("papers", papers);
            request.setAttribute("studentid", studentid);
            request.getRequestDispatcher("./teacher/Mark.jsp").forward(request, response);
        }
    }
}
