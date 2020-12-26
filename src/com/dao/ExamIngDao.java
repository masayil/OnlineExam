package com.dao;

import com.bean.entity.ExamAssign;
import com.myutil.Pool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ExamIngDao {
    public static ArrayList<ExamAssign> getstuExamAssignlistDao(String [] newlessonuuid,String sql, Connection con,String now) throws SQLException, ParseException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ExamAssign examassign=null;
        ArrayList<ExamAssign> ExamAssignlist=new ArrayList<ExamAssign>();
        for (String s : newlessonuuid) {
            ps = con.prepareStatement(sql);
            ps.setString(1, s);
            rs = ps.executeQuery();
            while (rs.next()) {
                int examAssign_serialNumber = rs.getInt("examAssign_serialNumber");
                String examAssign_uuid = rs.getString("examAssign_uuid");
                String lessonuuid = rs.getString("lessonuuid");
                String startTime = rs.getString("startTime");
                String endTime = rs.getString("endTime");
                int lasttime = rs.getInt("lasttime");
                String examAssign_createDate = rs.getString("examAssign_createDate");
                String paperbaseuuid = rs.getString("paperbaseuuid");
                String examAssign_name = rs.getString("examAssign_name");
                double totalscore = rs.getDouble("totalscore");
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date d1 = df.parse(startTime);
                Date d2 = df.parse(endTime);
                Date d3 = df.parse(now);
                if (d3.getTime() < d1.getTime() || d3.getTime() < d2.getTime()) {
                    examassign = new ExamAssign(examAssign_serialNumber, examAssign_uuid, lessonuuid, startTime, endTime, lasttime, examAssign_createDate, paperbaseuuid, examAssign_name,totalscore);
                    ExamAssignlist.add(examassign);
                }
            }
        }
        Pool.closeDBResource(rs,ps);
        return ExamAssignlist;
    }

    public static ArrayList<ExamAssign> getstuExamAssignListSpecialDao(Connection con, String newlessonuuid, String sql,String now) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ExamAssign examassign=null;
        ArrayList<ExamAssign> ExamAssignlist=new ArrayList<ExamAssign>();
        try{
            ps=con.prepareStatement(sql);
            ps.setString(1,newlessonuuid);
            rs=ps.executeQuery();
            while (rs.next()) {
                int examAssign_serialNumber = rs.getInt("examAssign_serialNumber");
                String examAssign_uuid = rs.getString("examAssign_uuid");
                String lessonuuid = rs.getString("lessonuuid");
                String startTime = rs.getString("startTime");
                String endTime = rs.getString("endTime");
                int lasttime = rs.getInt("lasttime");
                String examAssign_createDate = rs.getString("examAssign_createDate");
                String paperbaseuuid = rs.getString("paperbaseuuid");
                String examAssign_name = rs.getString("examAssign_name");
                double totalscore = rs.getDouble("totalscore");
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date d1 = df.parse(startTime);
                Date d2 = df.parse(endTime);
                Date d3 = df.parse(now);
                if (d3.getTime() < d1.getTime() || d3.getTime() < d2.getTime()) {
                    examassign = new ExamAssign(examAssign_serialNumber, examAssign_uuid, lessonuuid, startTime, endTime, lasttime, examAssign_createDate, paperbaseuuid, examAssign_name,totalscore);
                    ExamAssignlist.add(examassign);
                }
            }
        }catch (SQLException | ParseException e){
            e.printStackTrace();
        }finally {
            Pool.closeDBResource(rs,ps);
        }
        return ExamAssignlist;
    }
}
