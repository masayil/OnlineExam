package com.controller.teacher;

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

@WebServlet(name = "UpdateGrade",urlPatterns = {"/UpdateGrade"})
public class UpdateGrade extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        ServletContext application=getServletContext();
        Pool dbpool=(Pool)application.getAttribute("dbpool");
        Connection con=dbpool.getOneCon();
        if(null==con) {
            out.print("<script language='javascript'>alert('服务器繁忙请稍后再试!');window.history.go(-1);</script>");
        }else {
            String [] myscore=request.getParameterValues("myscore");
            String [] order=request.getParameterValues("order");
            String examuuid=request.getParameter("examuuid");
            String examname=request.getParameter("examname");
            String studentid=request.getParameter("studentid");
            CreateService.updatePaperService(con,myscore,order);
            CreateService.updateGradeService(con,myscore,examuuid,studentid);
            dbpool.close(con);
            response.sendRedirect("GetExamPaperList?examuuid="+examuuid+"&examname="+java.net.URLEncoder.encode(examname,"utf-8"));
        }
    }
}
