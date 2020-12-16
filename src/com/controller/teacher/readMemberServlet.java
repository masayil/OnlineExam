package com.controller.teacher;

import com.bean.entity.Student;
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

@WebServlet(name = "readMemberServlet",urlPatterns = {"/readMemberServlet"})
public class readMemberServlet extends HttpServlet {
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
        }else{
            String lessonuuid=request.getParameter("lessonuuid");
            String stuclass=request.getParameter("stuclass");
            ArrayList<Student> studentlist_class= FindService.getStudentList_classService(con,stuclass);
            ArrayList<Student> studentlist_retake=FindService.getStudentList_otherService(con,lessonuuid);
            dbpool.close(con);
            request.setAttribute("studentlist_class", studentlist_class);
            request.setAttribute("classname", stuclass);
            request.setAttribute("lessonuuid", lessonuuid);
            request.setAttribute("studentlist_retake", studentlist_retake);
            request.getRequestDispatcher("./teacher/student_list.jsp").forward(request, response);
        }
    }
}
