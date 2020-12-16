package com.dao;

import com.bean.entity.Newlesson;
import com.bean.entity.Teacher;
import com.myutil.Pool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FirstLoadDao {
    public static ArrayList<Newlesson> getNewlessonsDao(Connection con, String s_id, String sql) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Newlesson newlesson=null;
        ArrayList<Newlesson> Newlessonlist=new ArrayList<Newlesson>();
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, s_id);
            rs = ps.executeQuery();
            while (rs.next()){
                int newlesson_serialNumber=rs.getInt("newlesson_serialNumber");
                String newlesson_uuid=rs.getString("newlesson_uuid");
                String newlesson_name=rs.getString("newlesson_name");
                String newlesson_creatorID=rs.getString("newlesson_creatorID");
                String newlesson_class=rs.getString("newlesson_class");
                String newlesson_createDate=rs.getString("newlesson_createDate");
                newlesson=new Newlesson(newlesson_serialNumber,newlesson_uuid,newlesson_name,newlesson_creatorID,newlesson_class,newlesson_createDate);
                Newlessonlist.add(newlesson);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Pool.closeDBResource(rs,ps);
        }
        return Newlessonlist;
    }

    public static ArrayList<Teacher> geteacherlistDao(Connection con,String sql){
        PreparedStatement ps = null;
        ResultSet rs = null;
        Teacher teacher=null;
        ArrayList<Teacher> teacherlist=new ArrayList<Teacher>();
        try{
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                int t_serialNumber=rs.getInt("t_serialNumber");
                String t_id=rs.getString("t_id");
                String t_name=rs.getString("t_name");
                String t_password=rs.getString("t_password");
                String t_sex=rs.getString("t_sex");
                String t_college=rs.getString("t_college");
                String t_department=rs.getString("t_department");
                teacher=new Teacher(t_serialNumber,t_id,t_name,t_password,t_sex,t_college,t_department);
                teacherlist.add(teacher);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Pool.closeDBResource(rs,ps);
        }
        return teacherlist;
    }

    public static void ExitlessonDao(Connection con,String lessonuu_id,String studentid,String sql){
        PreparedStatement ps = null;
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1,lessonuu_id);
            ps.setString(2,studentid);
            int i=ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Pool.close(ps);
        }
    }

    public static void DeletelessonDao(Connection con,String lessonuu_id,String sql){
        PreparedStatement ps = null;
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1,lessonuu_id);
            int i=ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Pool.close(ps);
        }
    }
}
