package com.controller;

import com.bean.entity.Administrator;
import com.bean.entity.Student;
import com.bean.entity.Teacher;
import com.myutil.Pool;
import com.service.ChangePasswordService;
import org.apache.poi.ss.formula.functions.T;

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

@WebServlet(name = "ChangePwdServlet",urlPatterns = {"/ChangePwdServlet"})
public class ChangePwdServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        String password=request.getParameter("pwd");
        String shenfen=request.getParameter("shenfen");
        String id=request.getParameter("id");
        ServletContext application=getServletContext();
        Pool dbpool=(Pool)application.getAttribute("dbpool");
        Connection con=dbpool.getOneCon();
        boolean ischanged=false;
        if(null==con) {
            response.getWriter().print("busy");
        }else {
            switch (shenfen){
                case "student":
                    ischanged= ChangePasswordService.StudentPasswordService(password,id,con);
                    dbpool.close(con);
                    if(ischanged){
                        Student s=(Student)session.getAttribute("student");
                        Student student=new Student(s.getS_serialNumber(),s.getS_id(),s.getS_name(),password,s.getS_sex(),s.getS_college(),s.getS_department(),s.getS_class(),s.getS_major());
                        session.setAttribute("student",student);
                        response.getWriter().print("yes");
                    }else{
                        response.getWriter().print("no");
                    }
                    break;
                case "teacher":
                    ischanged=ChangePasswordService.TeacherPasswordService(password,id,con);
                    dbpool.close(con);
                    if(ischanged){
                        Teacher t=(Teacher) session.getAttribute("teacher");
                        Teacher teacher=new Teacher(t.getT_serialNumber(),t.getT_id(),t.getT_name(),password,t.getT_sex(),t.getT_college(),t.getT_department());
                        session.setAttribute("teacher",teacher);
                        response.getWriter().print("yes");
                    }else{
                        response.getWriter().print("no");
                    }
                    break;
                case "admin":
                    ischanged=ChangePasswordService.AdminPasswordService(password,id,con);
                    dbpool.close(con);
                    if(ischanged){
                        Administrator a=(Administrator)session.getAttribute("admin");
                        Administrator admin=new Administrator(a.getA_serialNumber(),a.getA_id(),a.getA_name(),password,a.getA_sex());
                        session.setAttribute("admin",admin);
                        response.getWriter().print("yes");
                    }else {
                        response.getWriter().print("no");
                    }
                    break;
            }
        }
    }

}
