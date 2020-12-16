package com.dao;

import com.myutil.Pool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddRetakeDao {
    public static Boolean stu_AddRetakeDao(String lessonuuid, String id, Connection con,String sql,String sql2){
        PreparedStatement ps = null;
        ResultSet rs= null;
        try{
            ps = con.prepareStatement(sql2);
            ps.setString(1,lessonuuid);
            ps.setString(2,id);
            rs=ps.executeQuery();
            if(rs.next()){
                return false;
            }
            ps = con.prepareStatement(sql);
            ps.setString(1,lessonuuid);
            ps.setString(2,id);
            int flag=ps.executeUpdate();
            if(flag==1){
                return true;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Pool.closeDBResource(rs,ps);
        }
        return false;

    }

    public static void teacherDeleteRetakeDao(Connection con,String studentid,String lessonuuid,String sql){
        PreparedStatement ps = null;
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1,lessonuuid);
            ps.setString(2,studentid);
            int i=ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Pool.close(ps);
        }
    }
}
