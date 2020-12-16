package com.dao.teacher;

import com.myutil.Pool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddnewlessonDao {
    public static Boolean teacher_addnewlessonDao(Connection con, String lesson_class,
                                                  String lesson_course, String teacherid,
                                                  String sql1,String sql2,String uuid,String nowtime){
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            ps=con.prepareStatement(sql1);
            ps.setString(1,lesson_course);
            ps.setString(2,teacherid);
            ps.setString(3,lesson_class);
            rs=ps.executeQuery();
            if(!rs.next()){
                ps=con.prepareStatement(sql2);
                ps.setString(1,uuid);
                ps.setString(2,lesson_course);
                ps.setString(3,teacherid);
                ps.setString(4,lesson_class);
                ps.setString(5,nowtime);
                int i=ps.executeUpdate();
                return true;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Pool.closeDBResource(rs,ps);
        }
        return false;
    }
}
