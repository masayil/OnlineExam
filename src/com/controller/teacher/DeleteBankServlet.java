package com.controller.teacher;

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

@WebServlet(name = "DeleteBankServlet",urlPatterns = {"/DeleteBankServlet"})
public class DeleteBankServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        ServletContext application=getServletContext();
        Pool dbpool=(Pool)application.getAttribute("dbpool");
        Connection con=dbpool.getOneCon();
        boolean isDelete;
        if(null==con) {
            response.getWriter().print("busy");
        }else {
            String serialNumber=request.getParameter("serialNumber");
            isDelete= FindService.deleteBankService(con,serialNumber);
            dbpool.close(con);
            if(isDelete){
                response.getWriter().print("yes");
            }else{
                response.getWriter().print("no");
            }
        }
    }

}
