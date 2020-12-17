package com.controller.teacher;

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
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "CreatePaperServlet",urlPatterns = {"/CreatePaperServlet"})
public class CreatePaperServlet extends HttpServlet {
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
            String course=request.getParameter("course");
            HashMap<String, Integer> map;
            map= FindService.CreatePaperService(con,course);
            int type1=map.get("单选题");
            int type2=map.get("多选题");
            int type3=map.get("判断题");
            int type4=map.get("简答题");
            dbpool.close(con);
            request.setAttribute("type1",type1);
            request.setAttribute("type2",type2);
            request.setAttribute("type3",type3);
            request.setAttribute("type4",type4);
            request.setAttribute("course",course);
            request.getRequestDispatcher("./teacher/step1_createpaper.jsp").forward(request, response);
        }
    }
}
