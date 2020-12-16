package com.listener;

import com.myutil.Pool;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class DateBaseConn
 *
 */
@WebListener
public class DateBaseConn implements ServletContextListener {

    /**
     * Default constructor. 
     */
	private ServletContext application=null;
    public DateBaseConn() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
       /*application=sce.getServletContext();
        Pool dbpool=(Pool)application.getAttribute("dbpool");
        for(int i=0;i<30;i++){
            Connection con=dbpool.getOneCon();
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        //注销JDBC驱动程序
        Enumeration drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = (Driver) drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        application.removeAttribute("dbpool");*/
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    	application=sce.getServletContext();
    	Pool dbpool=new Pool();
    	this.application.setAttribute("dbpool", dbpool);
    }
	
}
