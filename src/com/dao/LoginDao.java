package com.dao;

import com.bean.entity.Administrator;
import com.bean.entity.Student;
import com.bean.entity.Teacher;
import com.myutil.Pool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {
    public static Student stuloginDao(String id, String password, Connection con,String sql) throws SQLException {
        Student student=null;
        PreparedStatement ps=con.prepareStatement(sql);
        ps.setString(1,id);
        ps.setString(2, password);
        ResultSet rs=ps.executeQuery();
        if(rs.next()) {
            long s_serialNumber=rs.getLong("s_serialNumber");
            String s_id=rs.getString("s_id");
            String s_name=rs.getString("s_name");
            String s_password=rs.getString("s_password");
            String s_sex=rs.getString("s_sex");
            String s_college=rs.getString("s_college");
            String s_department=rs.getString("s_department");
            String s_class=rs.getString("s_class");
            String s_major=rs.getString("s_major");
            student=new Student(s_serialNumber,s_id,s_name,s_password,s_sex,s_college,s_department,s_class,s_major);
        }
        Pool.closeDBResource(rs,ps);
        return student;
    }
    public static Teacher tealoginDao(String id, String password, Connection con,String sql) throws SQLException {
        Teacher teacher=null;
        PreparedStatement ps=con.prepareStatement(sql);
        ps.setString(1,id);
        ps.setString(2, password);
        ResultSet rs=ps.executeQuery();
        if(rs.next()) {
            int t_serialNumber=rs.getInt("t_serialNumber");
            String t_id=rs.getString("t_id");
            String t_name=rs.getString("t_name");
            String t_password=rs.getString("t_password");
            String t_sex=rs.getString("t_sex");
            String t_college=rs.getString("t_college");
            String t_department=rs.getString("t_department");
            teacher=new Teacher(t_serialNumber,t_id,t_name,t_password,t_sex,t_college,t_department);
        }
        Pool.closeDBResource(rs,ps);
        return teacher;
    }
    public static Administrator adminloginDao(String id, String password, Connection con, String sql) throws SQLException {
        Administrator admin=null;
        PreparedStatement ps=con.prepareStatement(sql);
        ps.setString(1,id);
        ps.setString(2, password);
        ResultSet rs=ps.executeQuery();
        if(rs.next()) {
            int a_serialNumber=rs.getInt("a_serialNumber");
            String a_id=rs.getString("a_id");
            String a_name=rs.getString("a_name");
            String a_password=rs.getString("a_password");
            String a_sex=rs.getString("a_sex");
            admin=new Administrator(a_serialNumber,a_id,a_name,a_password,a_sex);
        }
        Pool.closeDBResource(rs,ps);
        return admin;
    }
}
