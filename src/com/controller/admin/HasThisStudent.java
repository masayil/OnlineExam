package com.controller.admin;

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

@WebServlet(name = "HasThisStudent",urlPatterns = {"/HasThisStudent"})
public class HasThisStudent extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        ServletContext application=getServletContext();
        Pool dbpool=(Pool)application.getAttribute("dbpool");
        Connection con=dbpool.getOneCon();
        if(null==con) {
            response.getWriter().print("busy");
        }else {
            String studentid=request.getParameter("studentid");
            if(QueryService.hasStuService(con,studentid)){
                dbpool.close(con);
                response.getWriter().print("yes");
            }else {
                dbpool.close(con);
                response.getWriter().print("no");
            }
        }
    }
}
