package com.controller;

import com.myutil.Pool;
import com.service.AddRetakeService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

@WebServlet(name = "AddRetakeServlet",urlPatterns = {"/AddRetakeServlet"})
public class AddRetakeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        //HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        String lessonuuid=request.getParameter("lessonuuid");
        String id=request.getParameter("id");
        ServletContext application=getServletContext();
        Pool dbpool=(Pool)application.getAttribute("dbpool");
        Connection con=dbpool.getOneCon();
        Boolean isAdd;
        if(null==con) {
            response.getWriter().print("busy");
        }else {
            isAdd= AddRetakeService.stu_AddRetakeService(lessonuuid,id,con);
            dbpool.close(con);
            if(isAdd){
                response.getWriter().print("yes");
            }else{
                response.getWriter().print("no");
            }
        }
    }
}
