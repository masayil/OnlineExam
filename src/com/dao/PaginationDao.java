package com.dao;

import com.bean.entity.QuestionBank;
import com.bean.pagination.Bank;
import com.myutil.Pool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaginationDao {
    public static Bank t_ToBank_allDao(Connection con,int curPageInt, int totalPageInt,String sql1,
                                       String sql2,String sql3){
        int filterNum=(curPageInt-1)*Bank.OnePageNum;
        String sql=null;
        if(filterNum==0){
            sql=sql1;
        }else {
            sql=sql2;
        }
        PreparedStatement ps=null;
        ResultSet rs=null;
        QuestionBank questionBank=null;
        ArrayList<QuestionBank> questionBankArrayList=new ArrayList<QuestionBank>();
        try{
            if(totalPageInt==0){
                String sqlForTotalNum=sql3;
                ps=con.prepareStatement(sqlForTotalNum,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
                rs=ps.executeQuery();
                rs.last();
                int rowCount=rs.getRow();
                totalPageInt=(rowCount+Bank.OnePageNum-1)/Bank.OnePageNum;
            }
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()){
                long questionBank_serialNumber=rs.getLong("questionBank_serialNumber");
                String questionBank_creatorID=rs.getString("questionBank_creatorID");
                String questionBank_course=rs.getString("questionBank_course");
                String questionBank_createDate=rs.getString("questionBank_createDate");
                String questionBank_point=rs.getString("questionBank_point");
                int questionBank_type=rs.getInt("questionBank_type");
                String questionBank_title=rs.getString("questionBank_title");
                String questionBank_titleimage=rs.getString("questionBank_titleimage");
                String questionBank_option1=rs.getString("questionBank_option1");
                String questionBank_option2=rs.getString("questionBank_option2");
                String questionBank_option3=rs.getString("questionBank_option3");
                String questionBank_option4=rs.getString("questionBank_option4");
                String questionBank_answer=rs.getString("questionBank_answer");
                questionBank=new QuestionBank(questionBank_serialNumber,questionBank_creatorID,questionBank_course,
                        questionBank_createDate,questionBank_point,questionBank_type,questionBank_title,questionBank_titleimage,
                        questionBank_option1,questionBank_option2,questionBank_option3,questionBank_option4,
                        questionBank_answer);
                questionBankArrayList.add(questionBank);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Pool.closeDBResource(rs,ps);
        }
        return new Bank(curPageInt,totalPageInt,questionBankArrayList);
    }

    public static Bank t_ToBank_titlenameDao(Connection con,int curPageInt, int totalPageInt,String sql1,
                                       String sql2,String sql3,String text){
        int filterNum=(curPageInt-1)*Bank.OnePageNum;
        String sql=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        QuestionBank questionBank=null;
        ArrayList<QuestionBank> questionBankArrayList=new ArrayList<QuestionBank>();
        try{
        if(filterNum==0){
            sql=sql1;
            ps=con.prepareStatement(sql);
            ps.setString(1, "%"+text+"%");
        }else {
            sql=sql2;
            ps=con.prepareStatement(sql);
            ps.setString(1, "%"+text+"%");
            ps.setString(2, "%"+text+"%");
        }
        if(totalPageInt==0){
            PreparedStatement ps2=null;
            ResultSet rs2=null;
                ps2=con.prepareStatement(sql3,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
                ps2.setString(1, "%"+text+"%");
                rs2=ps2.executeQuery();
                rs2.last();
                int rowCount=rs2.getRow();
                Pool.closeDBResource(rs2,ps2);
                totalPageInt=(rowCount+Bank.OnePageNum-1)/Bank.OnePageNum;
        }
        rs=ps.executeQuery();
            while (rs.next()){
                long questionBank_serialNumber=rs.getLong("questionBank_serialNumber");
                String questionBank_creatorID=rs.getString("questionBank_creatorID");
                String questionBank_course=rs.getString("questionBank_course");
                String questionBank_createDate=rs.getString("questionBank_createDate");
                String questionBank_point=rs.getString("questionBank_point");
                int questionBank_type=rs.getInt("questionBank_type");
                String questionBank_title=rs.getString("questionBank_title");
                String questionBank_titleimage=rs.getString("questionBank_titleimage");
                String questionBank_option1=rs.getString("questionBank_option1");
                String questionBank_option2=rs.getString("questionBank_option2");
                String questionBank_option3=rs.getString("questionBank_option3");
                String questionBank_option4=rs.getString("questionBank_option4");
                String questionBank_answer=rs.getString("questionBank_answer");
                questionBank=new QuestionBank(questionBank_serialNumber,questionBank_creatorID,questionBank_course,
                        questionBank_createDate,questionBank_point,questionBank_type,questionBank_title,questionBank_titleimage,
                        questionBank_option1,questionBank_option2,questionBank_option3,questionBank_option4,
                        questionBank_answer);
                questionBankArrayList.add(questionBank);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Pool.closeDBResource(rs,ps);
        }
        return new Bank(curPageInt,totalPageInt,questionBankArrayList);
    }
}
