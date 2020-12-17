package com.service.teacher;

import com.bean.entity.*;
import com.dao.teacher.FindDao;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FindService {
    public static ArrayList<Majorclass> majorclassService(Connection con){
        String sql="select * from majorclass";
        return FindDao.majorclassDao(con,sql);
    }
    public static ArrayList<Course> courseService(Connection con,String department){
        String sql="select * from course where course_creator=?";
        return FindDao.courseDao(con,department,sql);
    }

    public static ArrayList<Student> getStudentList_classService(Connection con,String stuclass){
        String sql="select * from student where s_class=?";
        return FindDao.getStudentListDao(con,stuclass,sql);
    }

    public static ArrayList<Student> getStudentList_otherService(Connection con,String lessonuuid){
        String sql="select * from student where s_id in (select retake_studentID from retake where lessonuuid=?)";
        return FindDao.getStudentListDao(con,lessonuuid,sql);
    }
    public static  ArrayList<Part_paperbase> getPart_paperbaseServcie(Connection con,String teacherid){
        String sql="select distinct paperbase_name,paperbase_uuid,paperbase_createDate,course  from paperbase where paperbase_creatorID=? order by paperbase_createDate desc";
        return FindDao.getPart_paperbaseDao(con,teacherid,sql);
    }
    public static ArrayList<PaperBase> getPaper_baseService(Connection con,String paperuuid){
        String sql="select * from paperbase where paperbase_uuid=?";
        return FindDao.getPaper_baseDao(con,paperuuid,sql);
    }
    public static boolean removePaperBaseService(Connection con, String paperuuid){
        String sql="delete from paperbase where paperbase_uuid=?";
        return FindDao.removePaperBaseDao(con,paperuuid,sql);
    }
    public static ArrayList<Teacher> getTeacherManageBankService(Connection con,String t_department){
        String sql="select * from teacher where t_department=?";
        return FindDao.getTeacherManageBankDao(con,t_department,sql);
    }
    public static ArrayList<Course> getCourseListService(Connection con,String t_department){
        String sql="select * from course where course_creator=?";
        return FindDao.getCourseListDao(con,t_department,sql);
    }
    public static boolean deleteBankService(Connection con,String serialNumber){
        String sql="delete from questionBank where questionBank_serialNumber=?";
        return FindDao.deleteBankDao(con,serialNumber,sql);
    }
    public static HashMap<String, Integer> CreatePaperService(Connection con,String course){
        String sql="select questionBank_type from questionBank where questionBank_course=?";
        ArrayList<Integer> count;
        count=FindDao.CreatePaperDao(con,course,sql);
        HashMap<String, Integer> map= new HashMap<>();
        int type1=0;
        int type2=0;
        int type3=0;
        int type4=0;
        int a;
        for (Integer integer : count) {
            a = integer;
            switch (a) {
                case 1:
                    type1++;
                    break;
                case 2:
                    type2++;
                    break;
                case 3:
                    type3++;
                    break;
                case 4:
                    type4++;
                    break;
            }
        }
        map.put("单选题",type1);
        map.put("多选题",type2);
        map.put("判断题",type3);
        map.put("简答题",type4);
        return map;
    }
}
