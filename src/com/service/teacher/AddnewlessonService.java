package com.service.teacher;

import com.dao.teacher.AddnewlessonDao;
import com.myutil.Generatetime;
import com.myutil.Generateuuid;

import java.sql.Connection;

public class AddnewlessonService {
    public static Boolean teacher_addnewlessonService(Connection con,String lesson_class,String lesson_course,String teacherid){
        String sql1="select newlesson_serialNumber from newlesson where newlesson_name=? and newlesson_creatorID=? and newlesson_class=?";
        String sql2="insert into newlesson values (?,?,?,?,?)";
        String uuid= Generateuuid.getuuid();
        String nowtime= Generatetime.gettime();
        return AddnewlessonDao.teacher_addnewlessonDao(con,lesson_class,lesson_course,teacherid,sql1,sql2,uuid,nowtime);
    }
}
