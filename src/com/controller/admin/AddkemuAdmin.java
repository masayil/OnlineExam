package com.controller.admin;

import com.myutil.Pool;
import com.service.admin.UpdateService;

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

@WebServlet(name = "AddkemuAdmin",urlPatterns = {"/AddkemuAdmin"})
public class AddkemuAdmin extends HttpServlet {
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
            String depart=request.getParameter("depart");
            String course=request.getParameter("course");
            if(UpdateService.addcourseService(con,depart,course)){
                dbpool.close(con);
                out.print("<script language='javascript'>alert('科目增加成功!');window.location.href='KeMu';</script>");
            }else {
                dbpool.close(con);
                out.print("<script language='javascript'>alert('失败!科目已经存在');window.history.go(-1);</script>");
            }
        }
    }

}
