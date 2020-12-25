package com.service.admin;

import com.bean.entity.Majorclass;
import com.bean.entity.Newlesson;
import com.bean.entity.Student;
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

    public static boolean hasTeaService(Connection con, String teacherid) {
        String sql="select * from teacher where t_id=?";
        return QueryDao.hasTeaDao(con,teacherid,sql);
    }

    public static ArrayList<Majorclass> getMajorClasslistService(Connection con) {
        String sql="select * from majorclass order by major desc";
        return QueryDao.getMajorClasslistDao(con,sql);
    }

    public static ArrayList<Student> getAllStudentAdminService(Connection con) {
        String sql="select * from student";
        return QueryDao.getAllStudentAdminDao(con,sql);
    }

    public static ArrayList<Student> getSpecialStudentAdminService(Connection con, String depart, String classselect) {
        if("全部".equals(classselect)){
            String sql="select * from student where s_department=? order by s_class desc";
            return QueryDao.getSpecialStudentAdminDao(con,depart,sql);
        }else {
            String sql="select * from student where s_class=?";
            return QueryDao.getSpecialStudentAdminDao(con,classselect,sql);
        }
    }

    public static boolean hasStuService(Connection con, String studentid) {
        String sql="select * from student where s_id=?";
        return QueryDao.hasTeaDao(con,studentid,sql);
    }

    public static boolean addstuSingleService(Connection con, String studentid, String studentname, String studentpwd,
                                              String sex, String college, String depart, String classselect, String major) {
        String sql="insert into student values (?,?,?,?,?,?,?,?)";
        return QueryDao.addstuSingleDao(con,studentid,studentname,studentpwd,sex,college,depart,classselect,major,sql);
    }

    public static ArrayList<Student> getSearchStudentAdminService(Connection con, String studentname) {
        String sql="select * from student where s_name LIKE ?";
        return QueryDao.getSearchStudentAdminDao(con,studentname,sql);
    }
}
