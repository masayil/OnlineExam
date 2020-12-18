package com.controller.teacher;

import com.bean.entity.Teacher;
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

@WebServlet(name = "GeneratePaperServlet",urlPatterns = {"/GeneratePaperServlet"})
public class GeneratePaperServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
            Teacher teacher=(Teacher)session.getAttribute("teacher");
            String papername=request.getParameter("papername");
            int danxuan=Integer.parseInt(request.getParameter("danxuan"));
            double score1=Double.parseDouble(request.getParameter("score1"));
            int duoxuan=Integer.parseInt(request.getParameter("duoxuan"));
            double score2=Double.parseDouble(request.getParameter("score2"));
            int panduan=Integer.parseInt(request.getParameter("panduan"));
            double score3=Double.parseDouble(request.getParameter("score3"));
            int jianda=Integer.parseInt(request.getParameter("jianda"));
            double score4=Double.parseDouble(request.getParameter("score4"));
            String thiscourse=request.getParameter("thiscourse");
            boolean isGenerate= CreateService.generatePaperBaseService(con,teacher.getT_id(),danxuan,duoxuan,panduan,
                    jianda,score1,score2,score3,score4,thiscourse,papername);
        }
    }
}
