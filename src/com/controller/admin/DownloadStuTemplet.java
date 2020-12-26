package com.controller.admin;

import com.myutil.toUTF8String;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet(name = "DownloadStuTemplet",urlPatterns = {"/DownloadStuTemplet"})
public class DownloadStuTemplet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        File downLoadFileDir=new File(getServletContext().getRealPath("/uploadfile/list/student"));
        String aFileName=null;
        FileInputStream in=null;
        ServletOutputStream out=null;
        try{
            aFileName=request.getParameter("resPath");
            response.setHeader("Content-Type","application/x-msdownload");
            response.setHeader("Content-Disposition","attachment;filename="+toUTF8String.utf8word(aFileName));
            in=new FileInputStream(downLoadFileDir+File.separator+aFileName);
            out=response.getOutputStream();
            out.flush();
            int aRead=0;
            byte[] b =new byte[1024];
            while ((aRead=in.read(b))!=-1&in!=null){
                out.write(b,0,aRead);
            }
            out.flush();
            in.close();
            out.close();
        }catch (Throwable e){
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
