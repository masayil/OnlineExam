package com.service;

import com.bean.entity.Newlesson;
import com.bean.entity.Teacher;
import com.dao.FirstLoadDao;
import java.sql.Connection;
import java.util.ArrayList;

public class FirstLoadService {
    public static ArrayList<Newlesson> getnewlesson1Service(Connection con, String s_class){
        String sql="select * from newlesson where newlesson_class=? order by newlesson_createDate desc";
        return FirstLoadDao.getNewlessonsDao(con, s_class,  sql);
    }
    public static ArrayList<Newlesson> getnewlesson2Service(Connection con, String s_id){
        String sql="select * from newlesson where newlesson_uuid in(select lessonuuid from retake where retake_studentID=?) order by newlesson_createDate desc ";
        return FirstLoadDao.getNewlessonsDao(con, s_id, sql);
    }
    public static ArrayList<Teacher> geteacherlistService(Connection con){
        String sql="select * from teacher";
        return FirstLoadDao.geteacherlistDao(con,sql);
    }
    public static void ExitlessonService(Connection con,String lessonuu_id,String studentid){
        String sql="delete from retake where lessonuuid=? and retake_studentID=?";
        FirstLoadDao.ExitlessonDao(con,lessonuu_id,studentid,sql);
    }

    public static ArrayList<Newlesson> teachernewLessonService(Connection con,String t_id){
        String sql="select * from newlesson where newlesson_creatorID=? order by newlesson_serialNumber desc";
        return FirstLoadDao.getNewlessonsDao(con, t_id, sql);
    }
    public static void DeletelessonService(Connection con,String lessonuu_id){
        String sql="delete from newlesson where newlesson_uuid=?";
        FirstLoadDao.DeletelessonDao(con,lessonuu_id,sql);
    }

}
