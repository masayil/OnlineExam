package com.service.teacher;

import com.bean.entity.QuestionBank;
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
        HashMap<String, ArrayList<QuestionBank>> map1= new HashMap<>();
        map1=FindDao.getBank_generatePaperDao(con,sql1,thiscourse);
        String this_paperBaseuuid= Generateuuid.getuuid();
        String createTime= Generatetime.gettime();
        if(!(danxuan == 0 || score1 == 0.0||score1==0)){
            ArrayList<QuestionBank> banklist1=map1.get("danxuan");
            createType1PaperBase(banklist1,teacherid,danxuan,score1,this_paperBaseuuid,createTime,papername,
                    thiscourse,con);
        }
        return true;
    }
    public static void createType1PaperBase(ArrayList<QuestionBank> banklist1,String teacherid,int danxaun,
                                            double score1,String this_paperBaseuuid,String createTime,
                                            String papername,String thiscourse,Connection con) {
        ArrayList<QuestionBank> want = new ArrayList<>();
        ArrayList<String> pointlist = new ArrayList<>();
        Collections.shuffle(banklist1);
        /*
        * 将知识点取出
        * */
        for (int i = 0; i < banklist1.size(); i++) {
            String point = banklist1.get(i).getQuestionBank_point();
            if (!pointlist.contains(point)) {
                pointlist.add(point);
            }
        }
        /*
        * 抽题目
        * */
        int pointflag=0;/*知识点标志*/
        for(int a=0;a<danxaun;a++){
            if(pointflag==pointlist.size()){
                pointflag=0;/*循环知识点，与size相同重置*/
            }
            String point_now=pointlist.get(pointflag);/*要抽的知识点*/
            int i;
            for(i=banklist1.size()-1;i>=0;i--){/*遍历相关题库，知识点相同取出至want并删除原数组的数据break循环,pointflag加1*/
                if(point_now.equals(banklist1.get(i).getQuestionBank_point())){
                    want.set(a, banklist1.get(i));
                    banklist1.remove(i);
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
        FindDao.createType1PaperBaseDao(want,teacherid,score1,this_paperBaseuuid,createTime,papername,thiscourse,
                sql,con);
    }
}
