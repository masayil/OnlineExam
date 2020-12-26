package com.controller.admin;

import com.bean.entity.Administrator;
import com.bean.entity.Student;
import com.bean.entity.Teacher;
import com.myutil.Pool;
import com.myutil.ReadExcelFile;
import com.myutil.fileupload;

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

@WebServlet(name = "UploadStuList",urlPatterns = {"/UploadStuList"})
@MultipartConfig
public class UploadStuList extends HttpServlet {
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
            Administrator administrator = (Administrator) session.getAttribute("admin");
            String savePath = request.getServletContext().getRealPath("/uploadfile");
            File f = new File(savePath);
            Part part = request.getPart("inputfile");
            String fileName = fileupload.getFileName(part);
            String newFileName = null;
            if (fileName != null && fileName.length() > 0) {
                int lastIndex = fileName.lastIndexOf(".");
                String fileType = fileName.substring(lastIndex);
                Date now = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSSS");
                newFileName = sdf.format(now) + administrator.getA_id() + fileType;
                part.write(savePath + File.separator + newFileName);
            }
            ReadExcelFile ref=new ReadExcelFile();
            List<Student> ls=ref.getExcelInfoStudentlist(new File(savePath,newFileName));
            PreparedStatement ps=null;
            try{
                ps=con.prepareStatement("insert into student values (?,?,?,?,?,?,?,?)");
                if(ls!=null){
                    for(int i=0;i<ls.size();i++){
                        ps.setString(1,ls.get(i).getS_id());
                        ps.setString(2,ls.get(i).getS_name());
                        ps.setString(3,ls.get(i).getS_password());
                        ps.setString(4,ls.get(i).getS_sex());
                        ps.setString(5,ls.get(i).getS_college());
                        ps.setString(6,ls.get(i).getS_department());
                        ps.setString(7,ls.get(i).getS_class());
                        ps.setString(8,ls.get(i).getS_major());
                        ps.addBatch();
                    }
                    ps.executeBatch();
                }
                Pool.close(ps);
            }catch (SQLException e){
                e.printStackTrace();
            }
            dbpool.close(con);
            out.print("<script language='javascript'>alert('导入成功!');window.location.href='ManageStudent';</script>");
        }
    }
}
