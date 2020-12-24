package com.service.admin;

import com.bean.entity.Newlesson;
import com.bean.entity.Teacher;
import com.dao.admin.QueryDao;

import java.sql.Connection;
import java.util.ArrayList;

public class QueryService {
    public static ArrayList<Teacher> getTeacherListService(Connection con) {
        String sql="select * from teacher";
        return QueryDao.getTeacherListDao(con,sql);
    }

    public static ArrayList<Newlesson> getNewLessonListService(Connection con, String t_id) {
        String sql="select * from newlesson where newlesson_creatorID=? order by newlesson_createDate desc";
        return QueryDao.getNewLessonListDao(con,t_id,sql);
    }

    public static Teacher getThisTeacherService(Connection con, String t_id) {
        String sql="select * from teacher where t_id=?";
        return QueryDao.getThisTeacherDao(con,t_id,sql);
    }

    public static ArrayList<Teacher> manageTeacher1Service(Connection con, String depart) {
        String sql="";
        if(depart.equals("全部")){
            sql="select * from teacher";
            return QueryDao.manageTeacher1Dao(con,sql);
        }else {
            sql="select * from teacher where t_department=?";
            return QueryDao.manageTeacher2Dao(con,sql,depart);
        }
    }

    public static ArrayList<Teacher> manageTeacher2Service(Connection con, String teachername) {
        String sql="select * from teacher where t_name LIKE ?";
        return QueryDao.manageTeacher3Dao(con,sql,teachername);
    }
}
