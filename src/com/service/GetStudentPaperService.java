package com.service;

import com.bean.entity.Paper;
import com.dao.GetStudentPaperDao;
import com.myutil.Pool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GetStudentPaperService {
    public static ArrayList<Paper> getPaperService(String studentID,String examAssignuuid, Connection con) throws SQLException {
        String sql="select * from paper where studentID=? and examAssignuuid=?";
        return GetStudentPaperDao.getPaperDao(studentID,examAssignuuid,con,sql);
    }
    public static ArrayList<Paper> ComeToExamService(String studentID,String examAssignuuid, Connection con,String paperbaseuuid) throws SQLException {
        String sql1="select * from paperbase where paperbase_uuid=?";
        String sql2="insert into paper values (?,?,?,?,?,?,?,?,?,?,?,?)";
        return GetStudentPaperDao.ComeToExamDao(studentID,examAssignuuid,con,paperbaseuuid,sql1,sql2);
    }
    public static Boolean isDoneexam(Connection con,String studentid,String examuuid,String sql){
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, studentid);
            ps.setString(2, examuuid);
            rs = ps.executeQuery();
            if(rs.next()){
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Pool.closeDBResource(rs,ps);
        }
        return false;
    }

    public static void updatePaper(Connection con,Long serialNumber, String answer){
        String sql="update paper set youranswer=? where paper_serialNumber=?";
        GetStudentPaperDao.updatePaperDao(con,serialNumber,answer,sql);
    }
}
