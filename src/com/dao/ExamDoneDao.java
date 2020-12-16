package com.dao;

import com.bean.entity.ExamAssign;
import com.bean.entity.Grade;
import com.myutil.Pool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ExamDoneDao {
    public static ArrayList<ExamAssign> getstuExamAssignlistDao(String [] newlessonuuid, String sql, Connection con, String now) throws SQLException, ParseException {
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
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                Date d1 = df.parse(startTime);
                Date d2 = df.parse(endTime);
                Date d3 = df.parse(now);
                if (d3.getTime() >= d2.getTime()) {
                    examassign = new ExamAssign(examAssign_serialNumber, examAssign_uuid, lessonuuid, startTime, endTime, lasttime, examAssign_createDate, paperbaseuuid, examAssign_name,totalscore);
                    ExamAssignlist.add(examassign);
                }
            }
        }
        Pool.closeDBResource(rs,ps);
        return ExamAssignlist;
    }

    public static ArrayList<Grade> getstuExamGradelistDao(String studentid,Connection con,String sql) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Grade grade=null;
        ArrayList<Grade> gradelist=new ArrayList<Grade>();
        ps = con.prepareStatement(sql);
        ps.setString(1, studentid);
        rs = ps.executeQuery();
        while (rs.next()){
            Long grade_serialNumber=rs.getLong("grade_serialNumber");
            double objectivequestion=rs.getDouble("objectivequestion");
            double subjectivequestion=rs.getDouble("subjectivequestion");
            double total=rs.getDouble("total");
            String studentID=rs.getString("studentID");
            String examAssignuuid=rs.getString("examAssignuuid");
            grade=new Grade(grade_serialNumber,objectivequestion,subjectivequestion,total,studentID,examAssignuuid);
            gradelist.add(grade);
        }
        Pool.closeDBResource(rs,ps);
        return gradelist;
    }
}
