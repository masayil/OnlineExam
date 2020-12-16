package com.controller.teacher;

import com.bean.entity.PaperBase;
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

@WebServlet(name = "ManagePaperServlet",urlPatterns = {"/ManagePaperServlet"})
public class ManagePaperServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
            String paperuuid=request.getParameter("paperuuid");
            String method=request.getParameter("method");
            switch (method){
                case "read":
                    ArrayList<PaperBase> paperBases= FindService.getPaper_baseService(con,paperuuid);
                    ArrayList<PaperBase> paperbase1=new ArrayList<PaperBase>();
                    ArrayList<PaperBase> paperbase2=new ArrayList<PaperBase>();
                    ArrayList<PaperBase> paperbase3=new ArrayList<PaperBase>();
                    ArrayList<PaperBase> paperbase4=new ArrayList<PaperBase>();
                    double totalscore=0;
                    for(PaperBase p:paperBases){
                        if (p.getQuestiontype() == 1) {
                            totalscore +=p.getScore();
                            paperbase1.add(p);
                        } else if (p.getQuestiontype() == 2) {
                            totalscore +=p.getScore();
                            paperbase2.add(p);
                        } else if (p.getQuestiontype() == 3) {
                            totalscore +=p.getScore();
                            paperbase3.add(p);
                        } else if (p.getQuestiontype() == 4) {
                            totalscore +=p.getScore();
                            paperbase4.add(p);
                        }
                    }
                    String course=request.getParameter("course");
                    request.setAttribute("course", course);
                    request.setAttribute("totalscore", totalscore);
                    request.setAttribute("examname", paperBases.get(0).getPaperbase_name());
                    request.setAttribute("paperbase1", paperbase1);
                    request.setAttribute("paperbase2", paperbase2);
                    request.setAttribute("paperbase3", paperbase3);
                    request.setAttribute("paperbase4", paperbase4);
                    dbpool.close(con);
                    request.getRequestDispatcher("./teacher/preview.jsp").forward(request, response);
                    break;
                case "delete":
                    break;
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
