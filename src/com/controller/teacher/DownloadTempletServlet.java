package com.controller.teacher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@WebServlet(name = "DownloadTempletServlet",urlPatterns = {"/DownloadTempletServlet"})
public class DownloadTempletServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        File downLoadFileDir=new File(getServletContext().getRealPath("/uploadfile/templet"));
        File [] templetlist=downLoadFileDir.listFiles();
        request.setAttribute("templetlist",templetlist);
        request.getRequestDispatcher("./teacher/uploadBank.jsp").forward(request,response);
    }
}
