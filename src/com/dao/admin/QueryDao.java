package com.dao.admin;

import com.bean.entity.Newlesson;
import com.bean.entity.Teacher;
import com.myutil.Pool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QueryDao {
    public static ArrayList<Teacher> getTeacherListDao(Connection con, String sql) {
        PreparedStatement ps=null;
        ResultSet rs=null;
        Teacher teacher=null;
        ArrayList<Teacher> teacherArrayList=new ArrayList<>();
        try{
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()){
                int t_serialNumber=rs.getInt("t_serialNumber");
                String t_id=rs.getString("t_id");
                String t_name=rs.getString("t_name");
                String t_password=rs.getString("t_password");
                String t_sex=rs.getString("t_sex");
                String t_college=rs.getString("t_college");
                String t_department=rs.getString("t_department");
                teacher=new Teacher(t_serialNumber,t_id,t_name,t_password,t_sex,t_college,t_department);
                teacherArrayList.add(teacher);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Pool.closeDBResource(rs,ps);
        }
        return teacherArrayList;
    }

    public static ArrayList<Newlesson> getNewLessonListDao(Connection con, String t_id, String sql) {
        PreparedStatement ps=null;
        ResultSet rs=null;
        Newlesson newlesson=null;
        ArrayList<Newlesson> newlessonArrayList=new ArrayList<>();
        try{
            ps=con.prepareStatement(sql);
            ps.setString(1,t_id);
            rs=ps.executeQuery();
            while (rs.next()){
                int newlesson_serialNumber=rs.getInt("newlesson_serialNumber");
                String newlesson_uuid=rs.getString("newlesson_uuid");
                String newlesson_name=rs.getString("newlesson_name");
                String newlesson_creatorID=rs.getString("newlesson_creatorID");
                String newlesson_class=rs.getString("newlesson_class");
                String newlesson_createDate=rs.getString("newlesson_createDate");
                newlesson=new Newlesson(newlesson_serialNumber,newlesson_uuid,newlesson_name,newlesson_creatorID,newlesson_class,newlesson_createDate);
                newlessonArrayList.add(newlesson);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Pool.closeDBResource(rs,ps);
        }
        return newlessonArrayList;
    }

    public static Teacher getThisTeacherDao(Connection con, String teacherid, String sql) {
        PreparedStatement ps=null;
        ResultSet rs=null;
        Teacher teacher=null;
        try {
            ps=con.prepareStatement(sql);
            ps.setString(1,teacherid);
            rs=ps.executeQuery();
            while (rs.next()){
                int t_serialNumber=rs.getInt("t_serialNumber");
                String t_id=rs.getString("t_id");
                String t_name=rs.getString("t_name");
                String t_password=rs.getString("t_password");
                String t_sex=rs.getString("t_sex");
                String t_college=rs.getString("t_college");
                String t_department=rs.getString("t_department");
                teacher=new Teacher(t_serialNumber,t_id,t_name,t_password,t_sex,t_college,t_department);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Pool.closeDBResource(rs,ps);
        }
        return teacher;
    }

    public static ArrayList<Teacher> manageTeacher1Dao(Connection con, String sql) {
        PreparedStatement ps=null;
        ResultSet rs=null;
        Teacher teacher=null;
        ArrayList<Teacher> teacherArrayList=new ArrayList<>();
        try{
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()){
                int t_serialNumber=rs.getInt("t_serialNumber");
                String t_id=rs.getString("t_id");
                String t_name=rs.getString("t_name");
                String t_password=rs.getString("t_password");
                String t_sex=rs.getString("t_sex");
                String t_college=rs.getString("t_college");
                String t_department=rs.getString("t_department");
                teacher=new Teacher(t_serialNumber,t_id,t_name,t_password,t_sex,t_college,t_department);
                teacherArrayList.add(teacher);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Pool.closeDBResource(rs,ps);
        }
        return teacherArrayList;
    }

    public static ArrayList<Teacher> manageTeacher2Dao(Connection con, String sql, String depart) {
        PreparedStatement ps=null;
        ResultSet rs=null;
        Teacher teacher=null;
        ArrayList<Teacher> teacherArrayList=new ArrayList<>();
        try{
            ps=con.prepareStatement(sql);
            ps.setString(1,depart);
            rs=ps.executeQuery();
            while (rs.next()){
                int t_serialNumber=rs.getInt("t_serialNumber");
                String t_id=rs.getString("t_id");
                String t_name=rs.getString("t_name");
                String t_password=rs.getString("t_password");
                String t_sex=rs.getString("t_sex");
                String t_college=rs.getString("t_college");
                String t_department=rs.getString("t_department");
                teacher=new Teacher(t_serialNumber,t_id,t_name,t_password,t_sex,t_college,t_department);
                teacherArrayList.add(teacher);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Pool.closeDBResource(rs,ps);
        }
        return teacherArrayList;
    }

    public static ArrayList<Teacher> manageTeacher3Dao(Connection con, String sql, String teachername) {
        PreparedStatement ps=null;
        ResultSet rs=null;
        Teacher teacher=null;
        ArrayList<Teacher> teacherArrayList=new ArrayList<>();
        try{
            ps=con.prepareStatement(sql);
            ps.setString(1,"%"+teachername+"%");
            rs=ps.executeQuery();
            while (rs.next()){
                int t_serialNumber=rs.getInt("t_serialNumber");
                String t_id=rs.getString("t_id");
                String t_name=rs.getString("t_name");
                String t_password=rs.getString("t_password");
                String t_sex=rs.getString("t_sex");
                String t_college=rs.getString("t_college");
                String t_department=rs.getString("t_department");
                teacher=new Teacher(t_serialNumber,t_id,t_name,t_password,t_sex,t_college,t_department);
                teacherArrayList.add(teacher);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Pool.closeDBResource(rs,ps);
        }
        return teacherArrayList;
    }
}
