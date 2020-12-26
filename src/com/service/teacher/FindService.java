package com.service.teacher;

import com.bean.entity.*;
import com.dao.teacher.CreateDao;
import com.dao.teacher.FindDao;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    public static ArrayList<PaperBase> prepareExamService(Connection con,String course){
        String sql="select distinct paperbase_name,paperbase_uuid from paperbase where course=?";
        return FindDao.prepareExamDao(con,course,sql);
    }

    public static ArrayList<Newlesson> getnewlessonTService(Connection con, String t_id){
        String sql="select * from newlesson where newlesson_creatorID=? order by newlesson_createDate desc";
        return CreateDao.getnewlessonTDao(con,t_id,sql);
    }


    public static HashMap<String, ArrayList<ExamAssign>> getExamAssign_list1Service(Connection con, ArrayList<Newlesson> newlessons_list, String gettime)  {
        String [] newlessonuuid = new String[newlessons_list.size()];
        for(int i=0;i<newlessons_list.size();i++){
            Newlesson newlesson=newlessons_list.get(i);
            newlessonuuid[i]=newlesson.getNewlesson_uuid();
        }
        String sql="select * from examAssign where lessonuuid=? order by examAssign_createDate desc";
        ArrayList<ExamAssign> examAssigns;
        ArrayList<ExamAssign> examAssign_before=new ArrayList<>();
        ArrayList<ExamAssign> examAssign_ing=new ArrayList<>();
        try{
            examAssigns=CreateDao.getTExamAssignlistDao(newlessonuuid,sql,con);
            for (int i=0;i<examAssigns.size();i++){
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date d1 = df.parse(examAssigns.get(i).getStartTime());
                Date d2 = df.parse(examAssigns.get(i).getEndTime());
                Date d3 = df.parse(gettime);
                if (d3.getTime() < d1.getTime()){
                    examAssign_before.add(examAssigns.get(i));
                }else if(d3.getTime() < d2.getTime()){
                    examAssign_ing.add(examAssigns.get(i));
                }
            }
        } catch (SQLException | ParseException throwables) {
            throwables.printStackTrace();
        }
        HashMap<String, ArrayList<ExamAssign>> map=new HashMap<>();
        map.put("before",examAssign_before);
        map.put("ing",examAssign_ing);
        return map;
    }

    public static ArrayList<ExamAssign> getExamAssign_list2Service(Connection con, ArrayList<Newlesson> newlessons_list, String gettime)  {
        String [] newlessonuuid = new String[newlessons_list.size()];
        for(int i=0;i<newlessons_list.size();i++){
            Newlesson newlesson=newlessons_list.get(i);
            newlessonuuid[i]=newlesson.getNewlesson_uuid();
        }
        String sql="select * from examAssign where lessonuuid=? order by examAssign_createDate desc";
        String sql2="select distinct examAssignuuid from grade where examAssignuuid=? and total=-1";
        ArrayList<String> result;
        ArrayList<ExamAssign> examAssigns;
        ArrayList<ExamAssign> examAssign_next=new ArrayList<>();
        ArrayList<ExamAssign> examAssign_normal=new ArrayList<>();
        try{
            examAssigns=CreateDao.getTExamAssignlistDao(newlessonuuid,sql,con);
            for (int i=0;i<examAssigns.size();i++){
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date d1 = df.parse(examAssigns.get(i).getStartTime());
                Date d2 = df.parse(examAssigns.get(i).getEndTime());
                Date d3 = df.parse(gettime);
                if (d3.getTime() >= d2.getTime()){
                    examAssign_next.add(examAssigns.get(i));
                }
            }
            String [] examuuid=new String[examAssign_next.size()];
            for(int i=0;i<examAssign_next.size();i++){
                ExamAssign examAssign=examAssign_next.get(i);
                examuuid[i]=examAssign.getExamAssign_uuid();
            }
            result=CreateDao.getTExamNormalDao(examuuid,sql2,con);
            for(int i=0;i<result.size();i++){
                for(int j=0;j<examAssign_next.size();j++){
                    if(result.get(i).equals(examAssign_next.get(j).getExamAssign_uuid())){
                        examAssign_normal.add(examAssign_next.get(j));
                    }
                }
            }
        } catch (SQLException | ParseException throwables) {
            throwables.printStackTrace();
        }
        return examAssign_normal;
    }

    public static ArrayList<ExamAssign> getExamAssign_list3Service(Connection con, ArrayList<Newlesson> newlessons_list, String gettime) {
        String [] newlessonuuid = new String[newlessons_list.size()];
        for(int i=0;i<newlessons_list.size();i++){
            Newlesson newlesson=newlessons_list.get(i);
            newlessonuuid[i]=newlesson.getNewlesson_uuid();
        }
        String sql1="select * from examAssign where lessonuuid=? order by examAssign_createDate desc";
        String sql2="select distinct examAssignuuid from grade where examAssignuuid=? and total=-1";
        ArrayList<String> result;
        ArrayList<ExamAssign> examAssigns;
        ArrayList<ExamAssign> examAssign_next=new ArrayList<>();
        ArrayList<ExamAssign> examAssign_done=new ArrayList<>();
        try{
            examAssigns=CreateDao.getTExamAssignlistDao(newlessonuuid,sql1,con);
            for (int i=0;i<examAssigns.size();i++){
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date d1 = df.parse(examAssigns.get(i).getStartTime());
                Date d2 = df.parse(examAssigns.get(i).getEndTime());
                Date d3 = df.parse(gettime);
                if (d3.getTime() >= d2.getTime()){
                    examAssign_next.add(examAssigns.get(i));
                }
            }
            String [] examuuid=new String[examAssign_next.size()];
            for(int i=0;i<examAssign_next.size();i++){
                ExamAssign examAssign=examAssign_next.get(i);
                examuuid[i]=examAssign.getExamAssign_uuid();
            }
            result=CreateDao.getTExamNormalDao(examuuid,sql2,con);
            for(int i=0;i<result.size();i++){
                for(int j=0;j<examAssign_next.size();j++){
                    if(result.get(i).equals(examAssign_next.get(j).getExamAssign_uuid())){
                        examAssign_next.remove(examAssign_next.get(j));
                        break;
                    }
                }
            }
        }catch (SQLException | ParseException throwables) {
            throwables.printStackTrace();
        }
        return examAssign_next;
    }
}
