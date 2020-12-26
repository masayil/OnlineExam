package com.service;

import com.bean.entity.ExamAssign;
import com.bean.entity.Newlesson;
import com.dao.ExamIngDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ExamIngService {
    public static ArrayList<ExamAssign> getstuExamAssignlistService(ArrayList<Newlesson> Newlessonlist_class, Connection con,String now) throws SQLException, ParseException {
        String [] newlessonuuid = new String[Newlessonlist_class.size()];
        for(int i=0;i<Newlessonlist_class.size();i++){
            Newlesson newlesson=Newlessonlist_class.get(i);
            newlessonuuid[i]=newlesson.getNewlesson_uuid();
        }
        String sql="select * from examAssign where lessonuuid=? order by examAssign_createDate desc";
        return ExamIngDao.getstuExamAssignlistDao(newlessonuuid,sql,con,now);
    }

    public static ArrayList<ExamAssign> getstuExamAssignListSpecialService(Connection con, String lessonuuid,String now) {
        String sql="select * from examAssign where lessonuuid=?";
        return ExamIngDao.getstuExamAssignListSpecialDao(con,lessonuuid,sql,now);
    }
}
