package com.service;

import com.dao.GradeDao;

import java.sql.Connection;

public class GradeService {
    public static void updateGradeService(Connection con,Double objectivequestion,String studentid,String examuuid){
        String sql="update grade set objectivequestion=? where studentID=? and examAssignuuid=?";
        GradeDao.updateGradeDao(con,objectivequestion,studentid,examuuid,sql);
    }
}
