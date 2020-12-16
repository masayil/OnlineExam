package com.service;

import com.bean.entity.ExamAssign;
import com.bean.entity.Grade;
import com.bean.entity.Newlesson;
import com.dao.ExamDoneDao;
import com.dao.ExamIngDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public class ExamDoneService {
    public static ArrayList<ExamAssign> getstuExamAssignlistService(ArrayList<Newlesson> Newlessonlist_class, Connection con, String now) throws SQLException, ParseException {
        String [] newlessonuuid = new String[Newlessonlist_class.size()];
        for(int i=0;i<Newlessonlist_class.size();i++){
            Newlesson newlesson=Newlessonlist_class.get(i);
            newlessonuuid[i]=newlesson.getNewlesson_uuid();
        }
        String sql="select * from examAssign where lessonuuid=? order by examAssign_createDate desc";
        return ExamDoneDao.getstuExamAssignlistDao(newlessonuuid,sql,con,now);
    }
    public static ArrayList<Grade> getstuExamGradelistService(String s_id,Connection con) throws SQLException {
        String sql="select * from grade where studentID=?";
        return ExamDoneDao.getstuExamGradelistDao(s_id,con,sql);
    }
}
