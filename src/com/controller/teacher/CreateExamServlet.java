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

@WebServlet(name = "CreateExamServlet",urlPatterns = {"/CreateExamServlet"})
public class CreateExamServlet extends HttpServlet {
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
            String lessonuuid=request.getParameter("lessonuuid");
            String paperuuid=request.getParameter("paperuuid");
            String starttime=request.getParameter("datetimepicker1");
            String endtime=request.getParameter("datetimepicker2");
            int lasttime=Integer.parseInt(request.getParameter("lasttime"));
            String examname=request.getParameter("examname");
            boolean isCreate= CreateService.CreateExamService(con,lessonuuid,paperuuid,starttime,endtime,lasttime,examname);
            dbpool.close(con);
            if(isCreate){
                out.print("<script language='javascript'>alert('考试发布成功!');window.location.href='FirstLoad?status=teacher';</script>");
            }else {
                out.print("<script language='javascript'>alert('失败!请再试一次！');window.history.go(-1);</script>");
            }
        }
    }

}
