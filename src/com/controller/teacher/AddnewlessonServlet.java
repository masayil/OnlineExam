package com.controller.teacher;

import com.myutil.Pool;
import com.service.teacher.AddnewlessonService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "AddnewlessonServlet",urlPatterns = {"/AddnewlessonServlet"})
public class AddnewlessonServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        String lesson_course=request.getParameter("lesson_course");
        String lesson_class=request.getParameter("lesson_class");
        String teacherid=request.getParameter("teacherid");
        ServletContext application=getServletContext();
        Pool dbpool=(Pool)application.getAttribute("dbpool");
        Connection con=dbpool.getOneCon();
        Boolean isAdd;
        if(null==con) {
            response.getWriter().print("busy");
        }else{
            isAdd= AddnewlessonService.teacher_addnewlessonService(con,lesson_class,lesson_course,teacherid);
            dbpool.close(con);
            if(isAdd){
                response.getWriter().print("yes");
            }else {
                response.getWriter().print("no");
            }
        }
    }
}
