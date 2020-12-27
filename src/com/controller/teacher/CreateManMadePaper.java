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

@WebServlet(name = "CreateManMadePaper",urlPatterns = {"/CreateManMadePaper"})
public class CreateManMadePaper extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        Teacher teacher=(Teacher)session.getAttribute("teacher");
        PrintWriter out = response.getWriter();
        ServletContext application=getServletContext();
        Pool dbpool=(Pool)application.getAttribute("dbpool");
        Connection con=dbpool.getOneCon();
        if(null==con) {
            out.print("<script language='javascript'>alert('服务器繁忙请稍后再试!');window.history.go(-1);</script>");
        }else {
            String papername=request.getParameter("papername");
            String [] danxuan=request.getParameterValues("danxuan");
            String [] duoxuan=request.getParameterValues("duoxuan");
            String [] panduan=request.getParameterValues("panduan");
            String [] jianda=request.getParameterValues("jianda");
            String [] danxuanscore=request.getParameterValues("danxuanscore");
            String [] duoxuanscore=request.getParameterValues("duoxuanscore");
            String [] panduanscore=request.getParameterValues("panduanscore");
            String [] jiandascore=request.getParameterValues("jiandascore");
            boolean isCreate= CreateService.createManMadePaper(con,danxuan,danxuanscore,duoxuan,duoxuanscore,
                    panduan,panduanscore,jianda,jiandascore,teacher.getT_id(),papername);
            dbpool.close(con);
            if(isCreate){
                response.sendRedirect("FindPaperBaseServlet");
            }else {
                out.print("<script language='javascript'>alert('创建失败!');window.location.href='FindPaperBaseServlet';</script>");
            }
        }
    }
}
