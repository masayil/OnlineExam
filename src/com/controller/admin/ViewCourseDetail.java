package com.controller.admin;

import com.bean.entity.Newlesson;
import com.bean.entity.Teacher;
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
import java.util.ArrayList;

@WebServlet(name = "ViewCourseDetail",urlPatterns = {"/ViewCourseDetail"})
public class ViewCourseDetail extends HttpServlet {
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
            String t_id=request.getParameter("t_id");
            ArrayList<Newlesson> newlessons_admin= QueryService.getNewLessonListService(con,t_id);
            Teacher thisteacher=QueryService.getThisTeacherService(con,t_id);
            dbpool.close(con);
            request.setAttribute("newlessons_admin",newlessons_admin);
            request.setAttribute("thisteacher",thisteacher);
            request.getRequestDispatcher("./admin/course.jsp").forward(request,response);
        }
    }
}
