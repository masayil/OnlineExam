package com.service;

import com.dao.AddRetakeDao;
import java.sql.Connection;

public class AddRetakeService {
    public static Boolean stu_AddRetakeService(String lessonuuid, String id, Connection con){
        String sql="insert into retake values (?,?)";
        String sql2="select * from retake where lessonuuid=? and retake_studentID=?";
        return AddRetakeDao.stu_AddRetakeDao(lessonuuid,id,con,sql,sql2);
    }

    public static void teacherDeleteRetakeService(Connection con,String studentid,String lessonuuid){
        String sql="delete from retake where lessonuuid=? and retake_studentID=?";
        AddRetakeDao.teacherDeleteRetakeDao(con,studentid,lessonuuid,sql);
    }
}
