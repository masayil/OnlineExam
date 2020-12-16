package com.dao;

import com.bean.entity.Paper;
import com.myutil.Pool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GetStudentPaperDao {
    public static ArrayList<Paper> getPaperDao(String id, String examuuid, Connection con,String sql) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Paper paper=null;
        ArrayList<Paper> papers=new ArrayList<Paper>();
        ps = con.prepareStatement(sql);
        ps.setString(1,id);
        ps.setString(2,examuuid);
        rs = ps.executeQuery();
        while (rs.next()){
            Long paper_serialNumber=rs.getLong("paper_serialNumber");
            String studentID=rs.getString("studentID");
            String examAssignuuid=rs.getString("examAssignuuid");
            int questiontype=rs.getInt("questiontype");
            String paper_title=rs.getString("paper_title");
            String paper_titleimage=rs.getString("paper_titleimage");
            String paper_option1=rs.getString("paper_option1");
            String paper_option2=rs.getString("paper_option2");
            String paper_option3=rs.getString("paper_option3");
            String paper_option4=rs.getString("paper_option4");
            String paper_answer=rs.getString("paper_answer");
            String youranswer=rs.getString("youranswer");
            Double score=rs.getDouble("score");
            paper=new Paper(paper_serialNumber,studentID,examAssignuuid,questiontype,paper_title,paper_titleimage,
                    paper_option1,paper_option2,paper_option3,paper_option4,paper_answer,youranswer,score);
            papers.add(paper);
        }
        Pool.closeDBResource(rs,ps);
        return papers;
    }

    public static ArrayList<Paper> ComeToExamDao(String id, String examuuid, Connection con,String paperbaseuuid,String sql1,String sql2) throws SQLException {
        PreparedStatement ps1=null;
        PreparedStatement ps2=null;
        String youranswer="";
        ResultSet rs1 = null;
        ArrayList<Paper> papers=new ArrayList<Paper>();
        ps1 = con.prepareStatement(sql1);
        ps1.setString(1,paperbaseuuid);
        rs1=ps1.executeQuery();
        while (rs1.next()){
            int questiontype=rs1.getInt("questiontype");
            String paper_title=rs1.getString("paperbase_title");
            String paper_titleimage=rs1.getString("paperbase_titleimage");
            String paper_option1=rs1.getString("paperbase_option1");
            String paper_option2=rs1.getString("paperbase_option2");
            String paper_option3=rs1.getString("paperbase_option3");
            String paper_option4=rs1.getString("paperbase_option4");
            String paper_answer=rs1.getString("paperbase_answer");
            double score=rs1.getDouble("score");
            ps2=con.prepareStatement(sql2);
            ps2.setString(1,id);
            ps2.setString(2,examuuid);
            ps2.setInt(3,questiontype);
            ps2.setString(4,paper_title);
            ps2.setString(5,paper_titleimage);
            ps2.setString(6,paper_option1);
            ps2.setString(7,paper_option2);
            ps2.setString(8,paper_option3);
            ps2.setString(9,paper_option4);
            ps2.setString(10,paper_answer);
            ps2.setString(11,youranswer);
            ps2.setDouble(12,score);
            int i=ps2.executeUpdate();
        }
        String sql3="select * from paper where studentID=? and examAssignuuid=?";
        String sql="insert into grade values (?,?,?,?,?)";
        papers=getPaperDao(id,examuuid,con,sql3);
        firstCreateGrade(id,examuuid,con,sql);
        Pool.close(rs1);
        Pool.close(ps1);
        Pool.close(ps2);
        return papers;
    }

    public static void firstCreateGrade(String id,String examuuid,Connection con,String sql) throws SQLException {
        PreparedStatement ps = null;
        ps=con.prepareStatement(sql);
        ps.setDouble(1,0);
        ps.setDouble(2,0);
        ps.setDouble(3,-1);
        ps.setString(4,id);
        ps.setString(5,examuuid);
        int i=ps.executeUpdate();
        Pool.close(ps);
    }

    public static void updatePaperDao(Connection con,Long serialNumber, String answer,String sql){
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1,answer);
            ps.setLong(2,serialNumber);
            int i=ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Pool.close(ps);
        }
    }
}
