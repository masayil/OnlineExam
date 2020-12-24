package com.controller.admin;

import com.bean.entity.Teacher;
import com.myutil.Pool;
import com.service.admin.QueryService;

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

@WebServlet(name = "BrowserTDepart",urlPatterns = {"/BrowserTDepart"})
public class BrowserTDepart extends HttpServlet {
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
            String type=request.getParameter("type");
            switch (type){
                case "1":
                    String depart=request.getParameter("depart");
                    ArrayList<Teacher> teacher_list_manage1=QueryService.manageTeacher1Service(con,depart);
                    dbpool.close(con);
                    request.setAttribute("teacher_list_manage",teacher_list_manage1);
                    request.setAttribute("depart",depart);
                    request.setAttribute("type",type);
                    request.getRequestDispatcher("./admin/teacher_list.jsp").forward(request,response);
                    break;
                case "2":
                    String teachername=request.getParameter("teachername");
                    ArrayList<Teacher> teacher_list_manage2=QueryService.manageTeacher2Service(con,teachername);
                    dbpool.close(con);
                    request.setAttribute("teacher_list_manage",teacher_list_manage2);
                    request.setAttribute("teachername",teachername);
                    request.setAttribute("depart","按姓名-"+teachername+"-搜索");
                    request.setAttribute("type",type);
                    request.getRequestDispatcher("./admin/teacher_list.jsp").forward(request,response);
                    break;
            }
        }
    }
}
