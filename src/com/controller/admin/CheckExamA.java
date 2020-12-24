package com.controller.admin;

import com.bean.entity.ExamAssign;
import com.bean.entity.Newlesson;
import com.bean.entity.Teacher;
import com.myutil.Generatetime;
import com.myutil.Pool;
import com.service.admin.QueryService;
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
import java.util.HashMap;

@WebServlet(name = "CheckExamA",urlPatterns = {"/CheckExamA"})
public class CheckExamA extends HttpServlet {
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
            String teacherid=request.getParameter("t_id");
            String type=request.getParameter("type");
            ArrayList<Newlesson> newlessons_list= FindService.getnewlessonTService(con,teacherid);
            Teacher thisteacher= QueryService.getThisTeacherService(con,teacherid);
            ArrayList<ExamAssign> ExamAssignlist_before;
            ArrayList<ExamAssign> ExamAssignlist_ing;
            ArrayList<ExamAssign> ExamAssignlist_normal;
            ArrayList<ExamAssign> ExamAssignlist_done;
            HashMap<String,ArrayList<ExamAssign>> map;
            switch (type){
                case "released":
                    map=FindService.getExamAssign_list1Service(con,newlessons_list, Generatetime.gettime());
                    dbpool.close(con);
                    ExamAssignlist_before=map.get("before");
                    ExamAssignlist_ing=map.get("ing");
                    request.setAttribute("ExamAssignlist_before", ExamAssignlist_before);
                    request.setAttribute("ExamAssignlist_ing", ExamAssignlist_ing);
                    request.setAttribute("newlessons_list", newlessons_list);
                    request.setAttribute("thisteacher", thisteacher);
                    request.getRequestDispatcher("./admin/released_detail.jsp").forward(request, response);
                    break;
                case "done":
                    ExamAssignlist_done=FindService.getExamAssign_list3Service(con,newlessons_list,Generatetime.gettime());
                    dbpool.close(con);
                    request.setAttribute("ExamAssignlist_done", ExamAssignlist_done);
                    request.setAttribute("newlessons_list", newlessons_list);
                    request.setAttribute("thisteacher", thisteacher);
                    request.getRequestDispatcher("./admin/done_detail.jsp").forward(request, response);
                    break;
            }
        }
    }
}
