package com.dao.teacher;

import com.bean.entity.ExamAssign;
import com.bean.entity.Newlesson;
import com.myutil.Pool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CreateDao {
    public static boolean CreateExamDao(Connection con, String lessonuuid, String paperuuid, String starttime,
                                     String endtime, int lasttime, String examname,String examAssign_uuid,
                                        String examAssign_createDate,double totalscore,String sql){
        PreparedStatement ps = null;
        try{
            ps=con.prepareStatement(sql);
            ps.setString(1,examAssign_uuid);
            ps.setString(2,lessonuuid);
            ps.setString(3,starttime);
            ps.setString(4,endtime);
            ps.setInt(5,lasttime);
            ps.setString(6,examAssign_createDate);
            ps.setString(7,paperuuid);
            ps.setString(8,examname);
            ps.setDouble(9,totalscore);
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

    public static ArrayList<Newlesson> getnewlessonTDao(Connection con, String t_id,String sql){
        PreparedStatement ps = null;
        ResultSet rs = null;
        Newlesson newlesson=new Newlesson();
        ArrayList<Newlesson> newlessonArrayList=new ArrayList<>();
        try{
            ps=con.prepareStatement(sql);
            ps.setString(1,t_id);
            rs=ps.executeQuery();
            while (rs.next()){
                String newlesson_uuid=rs.getString("newlesson_uuid");
                String newlesson_name=rs.getString("newlesson_name");
                newlesson.setNewlesson_uuid(newlesson_uuid);
                newlesson.setNewlesson_name(newlesson_name);
                newlessonArrayList.add(newlesson);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Pool.closeDBResource(rs,ps);
        }
        return newlessonArrayList;
    }

    public static ArrayList<ExamAssign> getTExamAssignlistDao(String [] newlessonuuid, String sql, Connection con) throws SQLException{
        PreparedStatement ps = null;
        ResultSet rs = null;
        ExamAssign examassign=null;
        ArrayList<ExamAssign> ExamAssignlist=new ArrayList<ExamAssign>();
        try {
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
                    examassign = new ExamAssign(examAssign_serialNumber, examAssign_uuid, lessonuuid, startTime, endTime, lasttime, examAssign_createDate, paperbaseuuid, examAssign_name, totalscore);
                    ExamAssignlist.add(examassign);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Pool.closeDBResource(rs,ps);
        }
        return ExamAssignlist;
    }
}
