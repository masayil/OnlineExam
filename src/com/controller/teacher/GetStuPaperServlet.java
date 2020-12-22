package com.controller.teacher;

import com.bean.entity.Paper;
import com.bean.entity.Student;
import com.dao.GetStudentPaperDao;
import com.myutil.Pool;
import com.service.GetStudentPaperService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "GetStuPaperServlet",urlPatterns = {"/GetStuPaperServlet"})
public class GetStuPaperServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        String studentID = request.getParameter("studentid");
        String examAssignuuid = request.getParameter("examuuid");
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
        }else  {
            try {
                papers = GetStudentPaperService.getPaperService(studentID, examAssignuuid, con);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            Student student= GetStudentPaperService.getOneStudentService(con,studentID);
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
            String examname=request.getParameter("examname");
            double total=Double.parseDouble(request.getParameter("total"));
            request.setAttribute("papers", papers);
            request.setAttribute("paper1", paper1);
            request.setAttribute("paper2", paper2);
            request.setAttribute("paper3", paper3);
            request.setAttribute("paper4", paper4);
            request.setAttribute("studentID", studentID);
            request.setAttribute("studentname", student.getS_name());
            request.setAttribute("studentclass", student.getS_class());
            request.setAttribute("examname", examname);
            request.setAttribute("total", total);
            request.getRequestDispatcher("./teacher/StudentPaper.jsp").forward(request, response);
        }
    }
}
