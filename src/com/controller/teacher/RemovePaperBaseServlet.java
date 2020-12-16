package com.controller.teacher;

import com.myutil.Pool;
import com.service.teacher.FindService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

@WebServlet(name = "RemovePaperBaseServlet",urlPatterns = {"/RemovePaperBaseServlet"})
public class RemovePaperBaseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        //HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        String paperuuid=request.getParameter("paperuuid");
        ServletContext application=getServletContext();
        Pool dbpool=(Pool)application.getAttribute("dbpool");
        Connection con=dbpool.getOneCon();
        boolean isRemove;
        if(null==con) {
            response.getWriter().print("busy");
        }else {
            isRemove= FindService.removePaperBaseService(con,paperuuid);
            dbpool.close(con);
            if(isRemove){
                response.getWriter().print("yes");
            }else{
                response.getWriter().print("no");
            }
        }
    }
}
