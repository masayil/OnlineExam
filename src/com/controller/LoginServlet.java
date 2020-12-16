package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.entity.Administrator;
import com.bean.entity.Student;
import com.bean.entity.Teacher;
import com.myutil.Pool;
import com.service.LoginService;

/**
 * Servlet implementation class LoginServlet
 * 
 */
/*登录验证*/
@WebServlet(name = "LoginServlet",urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String flag=request.getParameter("method");
		String username=request.getParameter("username");
		String status=request.getParameter("status");
		String password=request.getParameter("password");
		ServletContext application=getServletContext();
		Pool dbpool=(Pool)application.getAttribute("dbpool");
		Connection con=dbpool.getOneCon();
		if(null==con) {
			out.print("<script language='javascript'>alert('服务器繁忙请稍后再试!');window.history.go(-1);</script>");
		}else if(flag.equals("main")&&status.equals("student")) {
			try {
				Student student= LoginService.stuloginService(username,password,con);
				dbpool.close(con);
				if(null!=student) {
				request.getSession().setAttribute("student", student);
				response.sendRedirect("stuHome.jsp");
				}else {
					request.setAttribute("msg", "账号密码错误");
					request.getRequestDispatcher("main.jsp").forward(request, response);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(flag.equals("main")&&status.equals("teacher")) {
			try {
				Teacher teacher= LoginService.tealoginService(username,password,con);
				dbpool.close(con);
				if(null!=teacher) {
				request.getSession().setAttribute("teacher", teacher);
				response.sendRedirect("teacherHome.jsp");
				}else {
					request.setAttribute("msg", "账号密码错误");
					request.getRequestDispatcher("main.jsp").forward(request, response);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(flag.equals("admin")) {
			try {
				Administrator admin=LoginService.adminloginService(username,password,con);
				dbpool.close(con);
				if(null!=admin) {
				request.getSession().setAttribute("admin", admin);
				response.sendRedirect("paper.jsp");
				}else {
					request.setAttribute("msg", "管理员不存在");
					request.getRequestDispatcher("admin.jsp").forward(request, response);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	
}
