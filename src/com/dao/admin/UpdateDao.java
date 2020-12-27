package com.dao.admin;

import com.myutil.Pool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateDao {
    public static boolean resetPwdDao(Connection con, String id, String sql) {
        PreparedStatement ps = null;
        try{
            ps=con.prepareStatement(sql);
            ps.setString(1,id);
            ps.setString(2,id);
            int flag=ps.executeUpdate();
            if(flag==1){
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Pool.close(ps);
        }
        return false;
    }

    public static boolean deleteTeaAndStuDao(Connection con, String id, String sql) {
        PreparedStatement ps = null;
        try{
            ps=con.prepareStatement(sql);
            ps.setString(1,id);
            int flag=ps.executeUpdate();
            if(flag==1){
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Pool.close(ps);
        }
        return false;
    }

    public static boolean updateTeaDao(Connection con, String teacherid, String teachername, String sex, String depart, String sql) {
        PreparedStatement ps = null;
        try{
            ps=con.prepareStatement(sql);
            ps.setString(1,teachername);
            ps.setString(2,sex);
            ps.setString(3,depart);
            ps.setString(4,teacherid);
            int flag=ps.executeUpdate();
            if(flag==1){
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Pool.close(ps);
        }
        return false;
    }

    public static boolean importSingleTDao(Connection con, String teacherid, String teachername, String teacherpwd, String sex, String college, String depart, String sql) {
        PreparedStatement ps = null;
        try{
            ps=con.prepareStatement(sql);
            ps.setString(1,teacherid);
            ps.setString(2,teachername);
            ps.setString(3,teacherpwd);
            ps.setString(4,sex);
            ps.setString(5,college);
            ps.setString(6,depart);
            int i=ps.executeUpdate();
            if(i==1){
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Pool.close(ps);
        }
        return false;
    }

    public static boolean RemoveClassAdminDao(Connection con, String chosenclass, String sql1, String sql2) {
        PreparedStatement ps = null;
        try{
            ps=con.prepareStatement(sql1);
            ps.setString(1,chosenclass);
            int i=ps.executeUpdate();
            ps=con.prepareStatement(sql2);
            ps.setString(1,chosenclass);
            int j=ps.executeUpdate();
            if(i==1&&j>=0){
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Pool.close(ps);
        }
        return false;
    }

    public static boolean addclassDao(Connection con, String depart, String major, String class_1, String sql) {
        PreparedStatement ps = null;
        try{
            ps=con.prepareStatement(sql);
            ps.setString(1,depart);
            ps.setString(2,major);
            ps.setString(3,class_1);
            int i=ps.executeUpdate();
            if(i==1){
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Pool.close(ps);
        }
        return false;
    }

    public static boolean addcourseDao(Connection con, String depart, String course, String sql) {
        PreparedStatement ps = null;
        try{
            ps=con.prepareStatement(sql);
            ps.setString(1,course);
            ps.setString(2,depart);
            int i=ps.executeUpdate();
            if(i==1){
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Pool.close(ps);
        }
        return false;
    }

    public static boolean RemovekemuAdminDao(Connection con, String chosencourse, String sql) {
        PreparedStatement ps = null;
        try{
            ps=con.prepareStatement(sql);
            ps.setString(1,chosencourse);
            int i=ps.executeUpdate();
            if(i==1){
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Pool.close(ps);
        }
        return false;
    }
}
