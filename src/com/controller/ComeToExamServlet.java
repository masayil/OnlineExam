package com.controller;

import com.bean.entity.Paper;
import com.bean.entity.Student;
import com.myutil.Pool;
import com.service.GetStudentPaperService;

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

@WebServlet(name = "ComeToExamServlet",urlPatterns = {"/ComeToExamServlet"})
public class ComeToExamServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        Student student = (Student) session.getAttribute("student");
        PrintWriter out = response.getWriter();
        String paperbaseuuid = request.getParameter("paperbaseuuid");
        String start = request.getParameter("start");
        String end = request.getParameter("end");
        String examAssignuuid = request.getParameter("examAssignuuid");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        Date d1 = new Date();
        Date d2 = new Date();
        Date d3 = new Date();
        String time = df.format(now);
        try {
            d1 = df.parse(start);
            d2 = df.parse(end);
            d3 = df.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (!(d3.getTime() >= d1.getTime() && d3.getTime() < d2.getTime())) {
            out.print("<script language='javascript'>alert('考试尚未开始！');window.history.go(-1);</script>");
        } else {
            ServletContext application = getServletContext();
            Pool dbpool = (Pool) application.getAttribute("dbpool");
            Connection con = dbpool.getOneCon();
            ArrayList<Paper> papers = new ArrayList<Paper>();
            ArrayList<Paper> paper1 = new ArrayList<Paper>();
            ArrayList<Paper> paper2 = new ArrayList<Paper>();
            ArrayList<Paper> paper3 = new ArrayList<Paper>();
            ArrayList<Paper> paper4 = new ArrayList<Paper>();
            if (null == con) {
                out.print("<script language='javascript'>alert('服务器繁忙请稍后再试!');window.history.go(-1);</script>");
            } else {
                if (GetStudentPaperService.isDoneexam(con, student.getS_id(), examAssignuuid, "select * from grade where studentID=? and examAssignuuid=?")) {
                    out.print("<script language='javascript'>alert('您已经进行过考试了！');window.history.go(-1);</script>");
                } else {
                    try {
                        papers = GetStudentPaperService.ComeToExamService(student.getS_id(), examAssignuuid, con, paperbaseuuid);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    dbpool.close(con);
                    for (Paper paper : papers) {
                        if (paper.getQuestiontype() == 1) {
                            paper1.add(paper);
                        } else if (paper.getQuestiontype() == 2) {
                            paper2.add(paper);
                        } else if (paper.getQuestiontype() == 3) {
                            paper3.add(paper);
                        } else if (paper.getQuestiontype() == 4) {
                            paper4.add(paper);
                        }
                    }
                    String timestring = request.getParameter("lasttime");
                    String lasttime = String.valueOf(Integer.parseInt(timestring) * 60);
                    String examname = request.getParameter("examname");
                    String totalscore = request.getParameter("totalscore");
                    int size1 = paper1.size();
                    int size2 = paper2.size();
                    int size3 = paper3.size();
                    int size4 = paper4.size();
                    request.setAttribute("papers", papers);
                    request.setAttribute("paper1", paper1);
                    request.setAttribute("paper2", paper2);
                    request.setAttribute("paper3", paper3);
                    request.setAttribute("paper4", paper4);
                    request.setAttribute("size1", size1);
                    request.setAttribute("size2", size2);
                    request.setAttribute("size3", size3);
                    request.setAttribute("size4", size4);
                    request.setAttribute("totalscore", totalscore);
                    request.setAttribute("examname", examname);
                    request.setAttribute("lasttime", lasttime);
                    session.setAttribute("examuuid", examAssignuuid);
                    request.getRequestDispatcher("./profile/exam.jsp").forward(request, response);
                }
            }
        }
    }
}

