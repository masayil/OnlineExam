package com.controller;

import com.bean.entity.Paper;
import com.bean.entity.Student;
import com.myutil.Pool;
import com.myutil.fileupload;
import com.service.AnswerSheetService;
import com.service.GetStudentPaperService;
import com.service.GradeService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

@WebServlet(name = "MarkExamServlet",urlPatterns = {"/MarkExamServlet"})
@MultipartConfig
public class MarkExamServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        Student student=(Student)session.getAttribute("student");
        String paper1size=request.getParameter("paper1size");
        String paper2size=request.getParameter("paper2size");
        String paper3size=request.getParameter("paper3size");
        String paper4size=request.getParameter("paper4size");
        String examuuid=(String)session.getAttribute("examuuid");
        ArrayList<Paper> papers = new ArrayList<Paper>();
        ArrayList<Paper> paper1 = new ArrayList<Paper>();
        ArrayList<Paper> paper2 = new ArrayList<Paper>();
        ArrayList<Paper> paper3 = new ArrayList<Paper>();
        ArrayList<Paper> paper4 = new ArrayList<Paper>();
        ServletContext application = getServletContext();
        Pool dbpool = (Pool) application.getAttribute("dbpool");
        Connection con = dbpool.getOneCon();
        if (null == con) {
            out.print("<script language='javascript'>alert('服务器繁忙请稍后再试!');window.history.go(-1);</script>");
        }else{
            try {
                papers = GetStudentPaperService.getPaperService(student.getS_id(),examuuid,con);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
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
            double objectivequestion=0.0;
            for(int i=0;i<paper1.size();i++){
                String answer=request.getParameter("1_answer_"+(i+1));
                if(answer.equals(paper1.get(i).getPaper_answer())){
                    objectivequestion+=paper1.get(i).getScore();
                }
                GetStudentPaperService.updatePaper(con,paper1.get(i).getPaper_serialNumber(),answer);
            }
            for(int i=0;i<paper2.size();i++){
                String [] answerarray=request.getParameterValues("2_answer2_"+(i+1));
                StringBuilder answer= new StringBuilder();
                for (String s : answerarray) {
                    answer.append(s);
                }
                if(answer.toString().equals(paper2.get(i).getPaper_answer())){
                    objectivequestion+=paper2.get(i).getScore();
                }
                GetStudentPaperService.updatePaper(con,paper2.get(i).getPaper_serialNumber(), answer.toString());
            }
            for(int i=0;i<paper3.size();i++){
                String answer=request.getParameter("3_answer3_"+(i+1));
                if(answer.equals(paper3.get(i).getPaper_answer())){
                    objectivequestion+=paper3.get(i).getScore();
                }
                GetStudentPaperService.updatePaper(con,paper3.get(i).getPaper_serialNumber(),answer);
            }
            GradeService.updateGradeService(con,objectivequestion,student.getS_id(),examuuid);
            File uploadFileDir=new File(getServletContext().getRealPath("/answersheet"));
            for(int i=0;i<paper4.size();i++){
                Part part=request.getPart("4_answer4_"+(i+1));
                String oldname=fileupload.getFileName(part);
                String newname=null;
                if(oldname != null&&oldname.length()>0){
                    int lastindex=oldname.lastIndexOf(".");
                    String filetype=oldname.substring(lastindex);
                    if (".jpg".equals(filetype)) {
                        Date now=new Date();
                        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmssSSSS");
                        newname=sdf.format(now)+student.getS_id()+filetype;
                        String filepath="./answersheet/"+newname;
                        GetStudentPaperService.updatePaper(con,paper4.get(i).getPaper_serialNumber(),filepath);
                        part.write(uploadFileDir+File.separator+newname);
                    }else{
                        GetStudentPaperService.updatePaper(con,paper4.get(i).getPaper_serialNumber(),"");
                    }
                }else{
                    GetStudentPaperService.updatePaper(con,paper4.get(i).getPaper_serialNumber(),"");
                }
            }
            dbpool.close(con);
            out.print("<script language='javascript'>alert('提交成功!');window.top.location.href='stuHome.jsp'</script>");
            /*
            Part part=request.getPart("4_answer4_file");
            File uploadFileDir=new File(getServletContext().getRealPath("/answersheet"));
            String oldname= fileupload.getFileName(part);
            if(oldname != null) {
                String filepath="./answersheet/"+oldname;
                AnswerSheetService.firstAnswerSheet(con,student.getS_id(),examuuid,filepath);
                    part.write(uploadFileDir + File.separator+oldname);
                    dbpool.close(con);
                    out.print("<script language='javascript'>alert('提交成功!');window.top.location.href='stuHome.jsp'</script>");
            }else{
                dbpool.close(con);
                out.print("<script language='javascript'>alert('提交成功!');window.top.location.href='stuHome.jsp'</script>");
            }

             */
        }
    }
}
