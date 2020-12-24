package com.controller.teacher;

import com.myutil.Pool;
import com.service.teacher.CreateService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

@WebServlet(name = "DeleteExam",urlPatterns = {"/DeleteExam"})
public class DeleteExam extends HttpServlet {
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
            String examuuid=request.getParameter("examuuid");
            if(CreateService.deleteExamTService(con,examuuid)) {
                dbpool.close(con);
                out.print("<script language='javascript'>alert('撤销成功!');window.location.href='CheckExamT?type=released';</script>");
            }else {
                dbpool.close(con);
                out.print("<script language='javascript'>alert('请再试一次!');window.location.href='CheckExamT?type=released';</script>");
            }
        }
    }
}
