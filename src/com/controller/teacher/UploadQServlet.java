package com.controller.teacher;

import com.bean.entity.QuestionBank;
import com.bean.entity.Teacher;
import com.myutil.Pool;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.myutil.ReadExcelFile;
import com.myutil.fileupload;

@WebServlet(name = "UploadQServlet",urlPatterns = {"/UploadQServlet"})
@MultipartConfig
public class UploadQServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        ServletContext application = getServletContext();
        Pool dbpool = (Pool) application.getAttribute("dbpool");
        Connection con = dbpool.getOneCon();
        if (null == con) {
            out.print("<script language='javascript'>alert('服务器繁忙请稍后再试!');window.history.go(-1);</script>");
        } else {
            Teacher teacher = (Teacher) session.getAttribute("teacher");
                    String savePath = request.getServletContext().getRealPath("/uploadfile");
                    File f = new File(savePath);
                    Part part=request.getPart("filepath1");
                    String fileName=fileupload.getFileName(part);
                    String newFileName=null;
                    if(fileName!=null&&fileName.length()>0){
                        int lastIndex=fileName.lastIndexOf(".");
                        String fileType=fileName.substring(lastIndex);
                        Date now=new Date();
                        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmssSSSS");
                        newFileName=sdf.format(now)+teacher.getT_id()+fileType;
                        part.write(savePath+File.separator+newFileName);
                    }
                    ReadExcelFile ref=new ReadExcelFile();
                    List<QuestionBank> ls=ref.getExcelInfo1(new File(savePath,newFileName),teacher.getT_id());
                    PreparedStatement ps=null;
                    try {
                        ps=con.prepareStatement("insert into questionBank values (?,?,?,?,?,?,?,?,?,?,?,?)");
                        if(ls!=null){
                            for(int i=0;i<ls.size();i++){
                                ps.setString(1,ls.get(i).getQuestionBank_creatorID());
                                ps.setString(2,ls.get(i).getQuestionBank_course());
                                ps.setString(3,ls.get(i).getQuestionBank_createDate());
                                ps.setString(4,ls.get(i).getQuestionBank_point());
                                ps.setInt(5,ls.get(i).getQuestionBank_type());
                                ps.setString(6,ls.get(i).getQuestionBank_title());
                                ps.setString(7,ls.get(i).getQuestionBank_titleimage());
                                ps.setString(8,ls.get(i).getQuestionBank_option1());
                                ps.setString(9,ls.get(i).getQuestionBank_option2());
                                ps.setString(10,ls.get(i).getQuestionBank_option3());
                                ps.setString(11,ls.get(i).getQuestionBank_option4());
                                ps.setString(12,ls.get(i).getQuestionBank_answer());
                                ps.addBatch();
                            }
                            ps.executeBatch();
                        }
                        Pool.close(ps);
                    }catch (SQLException e){
                        e.printStackTrace();
                    }
                    dbpool.close(con);
                    out.print("<script language='javascript'>alert('导入成功!');self.location=document.referrer;</script>");
        }
    }

}
