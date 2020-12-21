package com.service.teacher;

import com.bean.entity.Paper;
import com.bean.entity.QuestionBank;
import com.bean.entity.Student;
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
            createPaperBase(banklist4,teacherid,panduan,score4,this_paperBaseuuid,createTime,papername,
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
}
