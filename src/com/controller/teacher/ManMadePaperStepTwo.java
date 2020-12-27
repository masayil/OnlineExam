package com.controller.teacher;

import com.bean.entity.QuestionBank;
import com.bean.entity.Teacher;
import com.myutil.Pool;
import com.service.teacher.CreateService;

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

@WebServlet(name = "ManMadePaperStepTwo",urlPatterns = {"/ManMadePaperStepTwo"})
public class ManMadePaperStepTwo extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        Teacher teacher=(Teacher)session.getAttribute("teacher");
        PrintWriter out = response.getWriter();
        ServletContext application=getServletContext();
        Pool dbpool=(Pool)application.getAttribute("dbpool");
        Connection con=dbpool.getOneCon();
        if(null==con) {
            out.print("<script language='javascript'>alert('服务器繁忙请稍后再试!');window.history.go(-1);</script>");
        }else {
            String papername=request.getParameter("papername");
            String course=request.getParameter("course");
            ArrayList<QuestionBank> questionBanks_manmade= CreateService.getQuestionBanks_manmadeService(con,course);
            ArrayList<Teacher> teachers_manmade=CreateService.getteachers_manmadeService(con);
            dbpool.close(con);
            request.setAttribute("questionBanks_manmade",questionBanks_manmade);
            request.setAttribute("teachers_manmade",teachers_manmade);
            request.setAttribute("papername",papername);
            request.setAttribute("course",course);
            request.getRequestDispatcher("./teacher/ManMadePaperStepTwo.jsp").forward(request,response);
        }
    }
}
