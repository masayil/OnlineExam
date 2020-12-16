package com.controller.teacher;

import com.bean.entity.Part_paperbase;
import com.bean.entity.Teacher;
import com.myutil.Pool;
import com.service.teacher.FindService;

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

@WebServlet(name = "FindPaperBaseServlet",urlPatterns = {"/FindPaperBaseServlet"})
public class FindPaperBaseServlet extends HttpServlet {
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
            Teacher teacher=(Teacher)session.getAttribute("teacher");
            ArrayList<Part_paperbase> part_paperbaseArrayList= FindService.getPart_paperbaseServcie(con,teacher.getT_id());
            dbpool.close(con);
            request.setAttribute("part_paperbaseArrayList",part_paperbaseArrayList);
            request.getRequestDispatcher("./teacher/managePaper.jsp").forward(request, response);
        }
    }
}
