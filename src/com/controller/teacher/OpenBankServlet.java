package com.controller.teacher;

import com.bean.entity.QuestionBank;
import com.bean.entity.Teacher;
import com.bean.pagination.Bank;
import com.myutil.Pool;
import com.service.PaginationService;
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

@WebServlet(name = "OpenBankServlet",urlPatterns = {"/OpenBankServlet"})
public class OpenBankServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        Teacher teacher=(Teacher)session.getAttribute("teacher");
        ServletContext application=getServletContext();
        Pool dbpool=(Pool)application.getAttribute("dbpool");
        Connection con=dbpool.getOneCon();
        if(null==con) {
            out.print("<script language='javascript'>alert('服务器繁忙请稍后再试!');window.history.go(-1);</script>");
        }else {
            String category=request.getParameter("category");
            String curPage=request.getParameter("curPage");
            String totalPage=request.getParameter("totalPage");
            int curPageInt=1;
            int totalPageInt=0;
            if(curPage!=null) {
                curPageInt=Integer.parseInt(curPage);
            }
            if(totalPage!=null) {
                totalPageInt=Integer.parseInt(totalPage);
            }
            switch (category){
                case "all":
                    String text1=request.getParameter("title");
                    String questiontype1=request.getParameter("questiontype");
                    Bank bank1=PaginationService.t_ToBank_allService(con,questiontype1,curPageInt,totalPageInt);
                    int p11=bank1.getCurPage();
                    int p21=bank1.getTotalpage();
                    ArrayList<QuestionBank> banklist1=bank1.getQuestionBankList();
                    request.setAttribute("nowpage",p11);
                    request.setAttribute("allpage",p21);
                    request.setAttribute("banklist",banklist1);
                    request.setAttribute("questiontype",questiontype1);
                    request.setAttribute("category",category);
                    String type1="所有";
                    request.setAttribute("check",type1);
                    request.setAttribute("title",text1);
                    dbpool.close(con);
                    request.getRequestDispatcher("./teacher/BankNoAction.jsp").forward(request, response);
                    break;
                case "title_name":
                    String text2=request.getParameter("title");
                    String questiontype2=request.getParameter("questiontype");
                    Bank bank2=PaginationService.t_ToBank_titlenameService(con,questiontype2,curPageInt,totalPageInt,text2);
                    int p12=bank2.getCurPage();
                    int p22=bank2.getTotalpage();
                    ArrayList<QuestionBank> banklist2=bank2.getQuestionBankList();
                    request.setAttribute("nowpage",p12);
                    request.setAttribute("allpage",p22);
                    request.setAttribute("banklist",banklist2);
                    request.setAttribute("questiontype",questiontype2);
                    request.setAttribute("category",category);
                    String type2="题目名字检索";
                    request.setAttribute("check",type2);
                    request.setAttribute("title",text2);
                    dbpool.close(con);
                    request.getRequestDispatcher("./teacher/BankNoAction.jsp").forward(request, response);
                    break;
                case "course_name":
                    String text3=request.getParameter("title");
                    String questiontype3=request.getParameter("questiontype");
                    Bank bank3=PaginationService.t_ToBank_coursenameService(con,questiontype3,curPageInt,totalPageInt,text3);
                    int p13=bank3.getCurPage();
                    int p23=bank3.getTotalpage();
                    ArrayList<QuestionBank> banklist3=bank3.getQuestionBankList();
                    request.setAttribute("nowpage",p13);
                    request.setAttribute("allpage",p23);
                    request.setAttribute("banklist",banklist3);
                    request.setAttribute("questiontype",questiontype3);
                    request.setAttribute("category",category);
                    String type3="科目检索";
                    request.setAttribute("check",type3);
                    request.setAttribute("title",text3);
                    dbpool.close(con);
                    request.getRequestDispatcher("./teacher/BankNoAction.jsp").forward(request, response);
                    break;
                case "teacher_name":
                    String text4=request.getParameter("title");
                    String questiontype4=request.getParameter("questiontype");
                    Bank bank4=PaginationService.t_ToBank_teachernameService(con,questiontype4,curPageInt,totalPageInt,text4);
                    int p14=bank4.getCurPage();
                    int p24=bank4.getTotalpage();
                    ArrayList<QuestionBank> banklist4=bank4.getQuestionBankList();
                    ArrayList<Teacher> t_list= FindService.getTeacherManageBankService(con,teacher.getT_department());
                    for (Teacher value : t_list) {
                        if (text4.equals(value.getT_id())) {
                            request.setAttribute("t_realname", value.getT_name());
                            break;
                        }
                    }
                    request.setAttribute("nowpage",p14);
                    request.setAttribute("allpage",p24);
                    request.setAttribute("banklist",banklist4);
                    request.setAttribute("questiontype",questiontype4);
                    request.setAttribute("category",category);
                    String type4="教师检索";
                    request.setAttribute("check",type4);
                    request.setAttribute("title",text4);
                    dbpool.close(con);
                    request.getRequestDispatcher("./teacher/BankNoAction.jsp").forward(request, response);
                    break;
                case "mine":
                    String text5=request.getParameter("title");
                    String questiontype5=request.getParameter("questiontype");
                    Bank bank5=PaginationService.t_ToBank_teachernameService(con,questiontype5,curPageInt,totalPageInt,teacher.getT_id());
                    int p15=bank5.getCurPage();
                    int p25=bank5.getTotalpage();
                    ArrayList<QuestionBank> banklist5=bank5.getQuestionBankList();
                    request.setAttribute("nowpage",p15);
                    request.setAttribute("allpage",p25);
                    request.setAttribute("banklist",banklist5);
                    request.setAttribute("questiontype",questiontype5);
                    request.setAttribute("category",category);
                    String type5="我的题库";
                    request.setAttribute("check",type5);
                    request.setAttribute("title",text5);
                    dbpool.close(con);
                    request.getRequestDispatcher("./teacher/BankHaveAction.jsp").forward(request, response);
                    break;
            }
        }
    }
}
