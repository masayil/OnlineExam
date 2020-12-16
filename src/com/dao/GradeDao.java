package com.dao;

import com.myutil.Pool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GradeDao {
    public static void updateGradeDao(Connection con,Double objectivequestion,String studentid,String examuuid,String sql){
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setDouble(1,objectivequestion);
            ps.setString(2,studentid);
            ps.setString(3,examuuid);
            int i=ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Pool.close(ps);
        }
    }
}
