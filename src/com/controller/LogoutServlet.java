package com.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LogoutServlet",urlPatterns = {"/LogoutServlet"})
public class LogoutServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        String status=request.getParameter("status");
        if(status.equals("admin")){
            session.invalidate();
            out.print("<script language='javascript'>window.top.location.href='admin.jsp';</script>");
        }else if(status.equals("teacher")||status.equals("student")){
            session.invalidate();
            out.print("<script language='javascript'>window.top.location.href='main.jsp';</script>");
        }
    }
}
