package com.myutil;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class Pool {
    ArrayList<Connection> list=new ArrayList<Connection>();
    //构造方法，创建30个连接对象，放到连接池中
    public Pool(){
        Properties p=new Properties();
        try {
            p.load(com.myutil.Pool.class.getClassLoader().getResourceAsStream("com/bean/jdbc.properties"));
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        String DBDriverName =p.getProperty("jdbc.driverClass");
        String DBUrl = p.getProperty("jdbc.jdbcUrl");
        String DBUser = p.getProperty("jdbc.username");
        String DBPwd = p.getProperty("jdbc.password");
        try {
            Class.forName(DBDriverName);
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        for(int i=0;i<30;i++) {
            try {
                Connection con=DriverManager.getConnection(DBUrl,DBUser,DBPwd);
                list.add(con);
                if(i==29){
                    System.out.println("30连接建立");
                }
            }catch(SQLException e) {
                e.printStackTrace();
            }
        }
    }
    //从连接池中取出一个对象
    public synchronized Connection getOneCon() {
        if(list.size()>0) {
            //System.out.println("取出一个连接");
            Connection con =list.remove(0);
            //System.out.println("取出后剩余连接数："+list.size());
            return con;
        }else {
            return null;
        }
    }

    //关闭结果集
    public static void close(ResultSet rs) {
        if(rs!=null) {
            try {
                rs.close();
            }catch(SQLException e) {
                e.printStackTrace();
            }
        }
    }
    //关闭预处理
    public static void close(PreparedStatement ps) {
        if(ps!=null) {
            try {
                ps.close();
            }catch(SQLException e) {
                e.printStackTrace();
            }
        }
    }
    //把连接对象放回连接池中
    public synchronized  void close(Connection con) {
        if(con!=null) {
            list.add(con);
            //System.out.println("释放一个连接");
            //System.out.println("释放后剩余连接数："+list.size());
        }
    }
    //关闭所有资源
    public static void closeDBResource(ResultSet rs, PreparedStatement ps) {
        close(rs);
        close(ps);
        //System.out.println("关闭结果集、预处理");
    }


}

