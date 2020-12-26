package com.controller;

import com.bean.entity.ExamAssign;
import com.bean.entity.Grade;
import com.bean.entity.Newlesson;
import com.bean.entity.Student;
import com.myutil.Pool;
import com.service.ExamDoneService;
import com.service.ExamIngService;
import com.service.FirstLoadService;

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
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@WebServlet(name = "StuExamServlet",urlPatterns = {"/StuExamServlet"})
public class StuExamServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String now = null;
        now=df.format(new Date());
        String method=request.getParameter("method");
        HttpSession session = request.getSession();
        Student student=(Student)session.getAttribute("student");
        PrintWriter out = response.getWriter();
        ServletContext application=getServletContext();
        Pool dbpool=(Pool)application.getAttribute("dbpool");
        Connection con=dbpool.getOneCon();
        if(null==con) {
            out.print("<script language='javascript'>alert('服务器繁忙请稍后再试!');window.top.location.href='stuHome.jsp';</script>");
        }else {
            String s_id = student.getS_id();
            String s_class = student.getS_class();
            ArrayList<Newlesson> Newlessonlist_class = FirstLoadService.getnewlesson1Service(con, s_class);
            ArrayList<Newlesson> Newlessonlist_other = FirstLoadService.getnewlesson2Service(con, s_id);
            ArrayList<ExamAssign> ExamAssignlist_class = null;
            ArrayList<ExamAssign> ExamAssignlist_other = null;
            ArrayList<Grade> gradelist = null;

            if (method.equals("ing")) {
                try {
                    ExamAssignlist_class = ExamIngService.getstuExamAssignlistService(Newlessonlist_class, con, now);
                } catch (SQLException | ParseException throwables) {
                    throwables.printStackTrace();
                }
                try {
                    ExamAssignlist_other = ExamIngService.getstuExamAssignlistService(Newlessonlist_other, con, now);
                } catch (SQLException | ParseException throwables) {
                    throwables.printStackTrace();
                }
                dbpool.close(con);
                request.setAttribute("ExamAssignlist_class", ExamAssignlist_class);
                request.setAttribute("ExamAssignlist_other", ExamAssignlist_other);
                request.setAttribute("Newlessonlist_class", Newlessonlist_class);
                request.setAttribute("Newlessonlist_other", Newlessonlist_other);
                request.getRequestDispatcher("./profile/stuExamIng.jsp").forward(request, response);
            } else if(method.equals("done")) {
                try {
                    ExamAssignlist_class = ExamDoneService.getstuExamAssignlistService(Newlessonlist_class, con, now);
                } catch (SQLException | ParseException throwables) {
                    throwables.printStackTrace();
                }
                try {
                    ExamAssignlist_other = ExamDoneService.getstuExamAssignlistService(Newlessonlist_other, con, now);
                } catch (SQLException | ParseException throwables) {
                    throwables.printStackTrace();
                }
                try {
                    gradelist = ExamDoneService.getstuExamGradelistService(s_id, con);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                dbpool.close(con);
                request.setAttribute("ExamAssignlist_class", ExamAssignlist_class);
                request.setAttribute("ExamAssignlist_other", ExamAssignlist_other);
                request.setAttribute("Newlessonlist_other", Newlessonlist_other);
                request.setAttribute("Newlessonlist_class", Newlessonlist_class);
                request.setAttribute("gradelist", gradelist);
                request.getRequestDispatcher("./profile/stuExamDone.jsp").forward(request, response);
            }else if(method.equals("special")){
                String lessonuuid=request.getParameter("lessonuuid");
                ArrayList<ExamAssign> ExamAssignlist_special=ExamIngService.getstuExamAssignListSpecialService(con,lessonuuid,now);
                dbpool.close(con);
                if(ExamAssignlist_special.size()<1){
                    out.print("<script language='javascript'>alert('此课程没有考试！');window.history.go(-1);</script>");
                }else {
                    request.setAttribute("ExamAssignlist_special", ExamAssignlist_special);
                    request.setAttribute("Newlessonlist_class", Newlessonlist_class);
                    request.setAttribute("Newlessonlist_other", Newlessonlist_other);
                    request.getRequestDispatcher("./profile/stuExamIng.jsp").forward(request, response);
                }
            }
        }
    }
}
