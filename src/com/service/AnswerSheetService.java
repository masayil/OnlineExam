package com.service;

import com.myutil.Pool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AnswerSheetService {
    public static void firstAnswerSheet(Connection con, String studentid,String examuuid,String filepath){
        String sql="insert into answersheet values (?,?,?,0)";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1,examuuid);
            ps.setString(2,studentid);
            ps.setString(3,filepath);
            int i=ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Pool.close(ps);
        }
    }
}
