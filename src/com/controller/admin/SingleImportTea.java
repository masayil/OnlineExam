package com.controller.admin;

import com.bean.entity.Teacher;
import com.myutil.Pool;
import com.service.admin.UpdateService;

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

@WebServlet(name = "SingleImportTea",urlPatterns = {"/SingleImportTea"})
public class SingleImportTea extends HttpServlet {
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
            String teacherid=request.getParameter("teacherid");
            String teachername=request.getParameter("teachername");
            String teacherpwd=request.getParameter("teacherpwd");
            String sex=request.getParameter("sex");
            String college=request.getParameter("college");
            String depart=request.getParameter("depart");
            if(UpdateService.importSingleTService(con,teacherid,teachername,teacherpwd,sex,college,depart)){
                dbpool.close(con);
                out.print("<script language='javascript'>alert('导入成功!');window.location.href='./admin/teacher_guide.jsp'</script>");
            }else {
                dbpool.close(con);
                out.print("<script language='javascript'>alert('导入失败!');window.location.href='./admin/teacher_guide.jsp'</script>");
            }
        }
    }
}
