package com.service.teacher;

import com.bean.entity.*;
import com.chart.BarChart_ByDatasetUtilities;
import com.dao.teacher.CreateDao;
import com.dao.teacher.FindDao;
import com.myutil.Generatetime;
import com.myutil.Generateuuid;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class CreateService {
    public static boolean generatePaperBaseService(Connection con,String teacherid,int danxuan,int duoxuan,
                                               int panduan,int jianda,double score1,
                                               double score2,double score3,double score4,String thiscourse,
                                               String papername){
        String sql1="select * from questionBank where questionBank_course=?";
        HashMap<String, ArrayList<QuestionBank>> map1;
        map1=FindDao.getBank_generatePaperDao(con,sql1,thiscourse);
        String this_paperBaseuuid= Generateuuid.getuuid();
        String createTime= Generatetime.gettime();
        if(!(danxuan == 0 || score1 == 0.0||score1==0)){
            ArrayList<QuestionBank> banklist1=map1.get("danxuan");
            createPaperBase(banklist1,teacherid,danxuan,score1,this_paperBaseuuid,createTime,papername,
                    thiscourse,con);
        }
        if(!(duoxuan == 0 || score2 == 0.0||score2==0)){
            ArrayList<QuestionBank> banklist2=map1.get("duoxuan");
            createPaperBase(banklist2,teacherid,duoxuan,score2,this_paperBaseuuid,createTime,papername,
                    thiscourse,con);
        }
        if(!(panduan == 0 || score3 == 0.0||score3==0)){
            ArrayList<QuestionBank> banklist3=map1.get("panduan");
            createPaperBase(banklist3,teacherid,panduan,score3,this_paperBaseuuid,createTime,papername,
                    thiscourse,con);
        }
        if(!(jianda == 0 || score4 == 0.0||score4==0)){
            ArrayList<QuestionBank> banklist4=map1.get("jianda");
            createPaperBase(banklist4,teacherid,jianda,score4,this_paperBaseuuid,createTime,papername,
                    thiscourse,con);
        }
        return true;
    }
    public static void createPaperBase(ArrayList<QuestionBank> banklist,String teacherid,int timu,
                                            double score1,String this_paperBaseuuid,String createTime,
                                            String papername,String thiscourse,Connection con) {
        ArrayList<QuestionBank> want = new ArrayList<>();
        ArrayList<String> pointlist = new ArrayList<>();
        Collections.shuffle(banklist);
        /*
        * 将知识点取出
        * */
        for (int i = 0; i < banklist.size(); i++) {
            String point = banklist.get(i).getQuestionBank_point();
            if (!pointlist.contains(point)) {
                pointlist.add(point);
            }
        }
        /*
        * 抽题目
        * */
        int pointflag=0;/*知识点标志*/
        for(int a=0;a<timu;a++){
            if(pointflag==pointlist.size()){
                pointflag=0;/*循环知识点，与size相同重置*/
            }
            String point_now=pointlist.get(pointflag);/*要抽的知识点*/
            int i;
            for(i=banklist.size()-1;i>=0;i--){/*遍历相关题库，知识点相同取出至want并删除原数组的数据break循环,pointflag加1*/
                if(point_now.equals(banklist.get(i).getQuestionBank_point())){
                    want.add(banklist.get(i));
                    banklist.remove(i);
                    pointflag++;
                    break;
                }
            }
            if(i==-1){/*i=-1，此知识点已经无题目*/
                pointflag++;
                a--;
            }
        }
        /**/
        String sql="insert into paperbase values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        FindDao.createPaperBaseDao(want,teacherid,score1,this_paperBaseuuid,createTime,papername,thiscourse,
                sql,con);
    }
    public static boolean CreateExamService(Connection con,String lessonuuid,String paperuuid,String starttime,
                                     String endtime,int lasttime,String examname){
        String examAssign_uuid=Generateuuid.getuuid();
        String examAssign_createDate=Generatetime.gettime();
        double totalscore=100;
        String sql="insert into examAssign values (?,?,?,?,?,?,?,?,?)";
        return CreateDao.CreateExamDao(con,lessonuuid,paperuuid,starttime,endtime,lasttime,examname,
                examAssign_uuid,examAssign_createDate,totalscore,sql);
    }

    public static boolean deleteExamTService(Connection con,String examuuid){
        String sql="delete from examAssign where examAssign_uuid=?";
        return CreateDao.deleteExamTDao(con,examuuid,sql);
    }

    public static ArrayList<Student> getStudentIDService(Connection con, String examuuid) {
        String sql="select * from student where s_id in (select studentID from grade where examAssignuuid=? and total=-1)";
        return CreateDao.getStudentIDDao(con,examuuid,sql);
    }

    public static ArrayList<Paper> markStuPapersService(Connection con, String examuuid, String studentid) {
        String sql="select * from paper where studentID=? and examAssignuuid=? and questiontype=4";
        return CreateDao.markPapersDao(con,examuuid,studentid,sql);
    }

    public static void updatePaperService(Connection con, String[] myscore, String[] order) {
        String sql="update paper set paper_option4=? where paper_serialNumber=?";
        for(int i=0;i<order.length;i++){
            CreateDao.updatePaperDao(con,myscore[i],order[i],sql);
        }
    }

    public static void updateGradeService(Connection con, String[] myscore, String examuuid, String studentid) {
        double subject=0;
        for(int i=0;i<myscore.length;i++){
            double fenshu=Double.parseDouble(myscore[i]);
            subject+=fenshu;
        }
        String sql1="select objectivequestion from grade where studentID=? and examAssignuuid=?";
        String sql2="update grade set subjectivequestion=? where studentID=? and examAssignuuid=?";
        String sql3="update grade set total=? where studentID=? and examAssignuuid=?";
        CreateDao.updateGradeDao(con,examuuid,studentid,subject,sql1,sql2,sql3);
    }

    public static double[][] getPictureService(Connection con, String examuuid) {
        String sql="select * from grade where examAssignuuid=?";
        ArrayList<Grade> grades=CreateDao.getPictureDao(con,examuuid,sql);
        double stage1=0;
        double stage2=0;
        double stage3=0;
        double stage4=0;
        double stage5=0;
        for(int i=0;i<grades.size();i++){
            if(grades.get(i).getTotal()<60){
                stage1+=1;
            }else if(grades.get(i).getTotal()<70){
                stage2+=1;
            }else if(grades.get(i).getTotal()<80){
                stage3+=1;
            }else if(grades.get(i).getTotal()<90){
                stage4+=1;
            }else if(grades.get(i).getTotal()>=90){
                stage5+=1;
            }
        }
        return new double[][] { { stage1, stage2, stage3, stage4,stage5 } };
    }

    public static ArrayList<Grade> getScorelistService(Connection con, String examuuid) {
        String sql="select * from grade where examAssignuuid=?";
        return CreateDao.getPictureDao(con,examuuid,sql);
    }

    public static ArrayList<Student> getSpecialStuListService(Connection con, ArrayList<Grade> gradeslist) {
        String sql="select * from student where s_id=?";
        String [] studentID_list=new String[gradeslist.size()];
        for(int i=0;i<gradeslist.size();i++){
            studentID_list[i]=gradeslist.get(i).getStudentID();
        }
        return CreateDao.getSpecialStuListDao(con,studentID_list,sql);
    }

    public static ArrayList<Student> getNoGradeListService(Connection con, String lessonuuid) {
        String sql1 = "select * from student where s_id in (select retake_studentID from retake where lessonuuid=?)";
        String sql2 = "select * from student where s_class in (select newlesson_class from newlesson where newlesson_uuid=?)";
        return CreateDao.getNoGradeListDao(con, lessonuuid, sql1, sql2);
    }

    public static ArrayList<Course> getcourseslist_madePaperService(Connection con, String mydepart) {
        String sql="select * from course where course_creator=?";
        return CreateDao.getcourseslist_madePaperDao(con,mydepart,sql);
    }

    public static ArrayList<QuestionBank> getQuestionBanks_manmadeService(Connection con, String course) {
        String sql="select * from questionBank where questionBank_course=? order by questionBank_creatorID,questionBank_type,questionBank_point";
        return CreateDao.getQuestionBanks_manmadeDao(con,course,sql);
    }

    public static ArrayList<Teacher> getteachers_manmadeService(Connection con) {
        String sql="select * from teacher";
        return CreateDao.getteachers_manmadeDao(con,sql);
    }

    public static boolean createManMadePaper(Connection con, String[] danxuan, String[] danxuanscore, String[] duoxuan,
                                             String[] duoxuanscore, String[] panduan,
                                             String[] panduanscore, String[] jianda, String[] jiandascore,
                                             String t_id,String papername) {
        String paperuuid=Generateuuid.getuuid();
        String createdate=Generatetime.gettime();
        for(int i=0;i<danxuan.length;i++){
            String sql="select * from questionBank where questionBank_serialNumber=?";
            String sql2="insert into paperbase values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            CreateDao.createManMadeDao(con,sql,sql2,paperuuid,papername,createdate,danxuan[i],danxuanscore[i],t_id);
        }
        for(int i=0;i<duoxuan.length;i++){
            String sql="select * from questionBank where questionBank_serialNumber=?";
            String sql2="insert into paperbase values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            CreateDao.createManMadeDao(con,sql,sql2,paperuuid,papername,createdate,duoxuan[i],duoxuanscore[i],t_id);
        }
        for(int i=0;i<panduan.length;i++){
            String sql="select * from questionBank where questionBank_serialNumber=?";
            String sql2="insert into paperbase values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            CreateDao.createManMadeDao(con,sql,sql2,paperuuid,papername,createdate,panduan[i],panduanscore[i],t_id);
        }
        for(int i=0;i<jianda.length;i++){
            String sql="select * from questionBank where questionBank_serialNumber=?";
            String sql2="insert into paperbase values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            CreateDao.createManMadeDao(con,sql,sql2,paperuuid,papername,createdate,jianda[i],jiandascore[i],t_id);
        }
        return true;
    }
}
