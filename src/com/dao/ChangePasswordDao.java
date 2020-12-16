package com.dao;

import com.myutil.Pool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ChangePasswordDao {
    public static Boolean PasswordChangeDao(String password, String id, Connection con, String sql){
        PreparedStatement ps = null;
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1,password);
            ps.setString(2,id);
            int flag=ps.executeUpdate();
            if(flag==1){
                return true;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Pool.close(ps);
        }
        return false;
    }
}
