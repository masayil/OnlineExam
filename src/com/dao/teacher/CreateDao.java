package com.dao.teacher;

import com.bean.entity.*;
import com.myutil.Pool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class CreateDao {
    public static boolean CreateExamDao(Connection con, String lessonuuid, String paperuuid, String starttime,
                                     String endtime, int lasttime, String examname,String examAssign_uuid,
                                        String examAssign_createDate,double totalscore,String sql){
        PreparedStatement ps = null;
        try{
            ps=con.prepareStatement(sql);
            ps.setString(1,examAssign_uuid);
            ps.setString(2,lessonuuid);
            ps.setString(3,starttime);
            ps.setString(4,endtime);
            ps.setInt(5,lasttime);
            ps.setString(6,examAssign_createDate);
            ps.setString(7,paperuuid);
            ps.setString(8,examname);
            ps.setDouble(9,totalscore);
            int i=ps.executeUpdate();
            if(i==1){
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Pool.close(ps);
        }
        return false;
    }

    public static ArrayList<Newlesson> getnewlessonTDao(Connection con, String t_id,String sql){
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Newlesson> newlessonArrayList=new ArrayList<>();
        try{
            ps=con.prepareStatement(sql);
            ps.setString(1,t_id);
            rs=ps.executeQuery();
            while (rs.next()){
                Newlesson newlesson=new Newlesson();
                String newlesson_uuid=rs.getString("newlesson_uuid");
                String newlesson_name=rs.getString("newlesson_name");
                String newlesson_class=rs.getString("newlesson_class");
                newlesson.setNewlesson_uuid(newlesson_uuid);
                newlesson.setNewlesson_name(newlesson_name);
                newlesson.setNewlesson_class(newlesson_class);
                newlessonArrayList.add(newlesson);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Pool.closeDBResource(rs,ps);
        }
        return newlessonArrayList;
    }

    public static ArrayList<ExamAssign> getTExamAssignlistDao(String [] newlessonuuid, String sql, Connection con) throws SQLException{
        PreparedStatement ps = null;
        ResultSet rs = null;
        ExamAssign examassign=null;
        ArrayList<ExamAssign> ExamAssignlist=new ArrayList<ExamAssign>();
        try {
            for (String s : newlessonuuid) {
                ps = con.prepareStatement(sql);
                ps.setString(1, s);
                rs = ps.executeQuery();
                while (rs.next()) {
                    int examAssign_serialNumber = rs.getInt("examAssign_serialNumber");
                    String examAssign_uuid = rs.getString("examAssign_uuid");
                    String lessonuuid = rs.getString("lessonuuid");
                    String startTime = rs.getString("startTime");
                    String endTime = rs.getString("endTime");
                    int lasttime = rs.getInt("lasttime");
                    String examAssign_createDate = rs.getString("examAssign_createDate");
                    String paperbaseuuid = rs.getString("paperbaseuuid");
                    String examAssign_name = rs.getString("examAssign_name");
                    double totalscore = rs.getDouble("totalscore");
                    examassign = new ExamAssign(examAssign_serialNumber, examAssign_uuid, lessonuuid, startTime, endTime, lasttime, examAssign_createDate, paperbaseuuid, examAssign_name, totalscore);
                    ExamAssignlist.add(examassign);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Pool.closeDBResource(rs,ps);
        }
        return ExamAssignlist;
    }

    public static boolean deleteExamTDao(Connection con,String examuuid,String sql){
        PreparedStatement ps = null;
        try{
            ps=con.prepareStatement(sql);
            ps.setString(1,examuuid);
            int i=ps.executeUpdate();
            if(i==1){
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Pool.close(ps);
        }
        return false;
    }

    public static ArrayList<String> getTExamNormalDao(String[] examuuid, String sql, Connection con) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<String> examuuidlist=new ArrayList<>();
        try{
            for(int i=0;i<examuuid.length;i++){
                ps = con.prepareStatement(sql);
                ps.setString(1,examuuid[i]);
                rs = ps.executeQuery();
                while (rs.next()){
                    String uuid= "";
                    uuid=rs.getString("examAssignuuid");
                    examuuidlist.add(uuid);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Pool.closeDBResource(rs,ps);
        }
        return examuuidlist;
    }

    public static ArrayList<Student> getStudentIDDao(Connection con, String examuuid, String sql) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Student student=null;
        ArrayList<Student> studentIDlist=new ArrayList<>();
        try{
            ps=con.prepareStatement(sql);
            ps.setString(1,examuuid);
            rs=ps.executeQuery();
            while (rs.next()){
                long s_serialNumber=rs.getLong("s_serialNumber");
                String s_id=rs.getString("s_id");
                String s_name=rs.getString("s_name");
                String s_password=rs.getString("s_password");
                String s_sex=rs.getString("s_sex");
                String s_college=rs.getString("s_college");
                String s_department=rs.getString("s_department");
                String s_class=rs.getString("s_class");
                String s_major=rs.getString("s_major");
                student=new Student(s_serialNumber,s_id,s_name,s_password,s_sex,s_college,s_department,s_class,s_major);
                studentIDlist.add(student);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Pool.closeDBResource(rs,ps);
        }
        return studentIDlist;
    }

    public static ArrayList<Paper> markPapersDao(Connection con, String examuuid, String studentid, String sql) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Paper paper=null;
        ArrayList<Paper> papers=new ArrayList<>();
        try{
            ps=con.prepareStatement(sql);
            ps.setString(1,studentid);
            ps.setString(2,examuuid);
            rs=ps.executeQuery();
            while (rs.next()){
                long paper_serialNumber=rs.getLong("paper_serialNumber");
                String studentID=rs.getString("studentID");
                String examAssignuuid=rs.getString("examAssignuuid");
                int questiontype=rs.getInt("questiontype");
                String paper_title=rs.getString("paper_title");
                String paper_titleimage=rs.getString("paper_titleimage");
                String paper_option1=rs.getString("paper_option1");
                String paper_option2=rs.getString("paper_option2");
                String paper_option3=rs.getString("paper_option3");
                String paper_option4=rs.getString("paper_option4");
                String paper_answer=rs.getString("paper_answer");
                String youranswer=rs.getString("youranswer");
                double score=rs.getDouble("score");
                paper=new Paper(paper_serialNumber,studentID,examAssignuuid,questiontype,paper_title,paper_titleimage,
                        paper_option1,paper_option2,paper_option3,paper_option4,paper_answer,youranswer,score);
                papers.add(paper);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Pool.closeDBResource(rs,ps);
        }
        return papers;
    }

    public static void updatePaperDao(Connection con, String score, String order, String sql) {
        PreparedStatement ps = null;
        long serialNumber=Long.parseLong(order);
        try{
            ps=con.prepareStatement(sql);
            ps.setString(1,score);
            ps.setLong(2,serialNumber);
            int i=ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Pool.close(ps);
        }
    }

    public static void updateGradeDao(Connection con, String examuuid, String studentid, double subject, String sql1, String sql2, String sql3) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        double object=0;
        double total=0;
        try{
            ps=con.prepareStatement(sql1);
            ps.setString(1,studentid);
            ps.setString(2,examuuid);
            rs=ps.executeQuery();
            if(rs.next()){
            object=rs.getDouble("objectivequestion");}
            ps=con.prepareStatement(sql2);
            ps.setDouble(1,subject);
            ps.setString(2,studentid);
            ps.setString(3,examuuid);
            int i=ps.executeUpdate();
            total=subject+object;
            ps=con.prepareStatement(sql3);
            ps.setDouble(1,total);
            ps.setString(2,studentid);
            ps.setString(3,examuuid);
            int y=ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Pool.closeDBResource(rs,ps);
        }
    }

    public static ArrayList<Grade> getPictureDao(Connection con, String examuuid, String sql) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Grade grade=null;
        ArrayList<Grade> grades=new ArrayList<>();
        try{
            ps=con.prepareStatement(sql);
            ps.setString(1,examuuid);
            rs=ps.executeQuery();
            while (rs.next()){
                long grade_serialNumber=rs.getLong("grade_serialNumber");
                double objectivequestion=rs.getDouble("objectivequestion");
                double subjectivequestion=rs.getDouble("subjectivequestion");
                double total=rs.getDouble("total");
                String studentID=rs.getString("studentID");
                String examAssignuuid=rs.getString("examAssignuuid");
                grade=new Grade(grade_serialNumber,objectivequestion,subjectivequestion,total,studentID,examAssignuuid);
                grades.add(grade);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Pool.closeDBResource(rs,ps);
        }
        return grades;
    }

    public static ArrayList<Student> getSpecialStuListDao(Connection con, String[] studentID_list, String sql) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Student student=null;
        ArrayList<Student> students=new ArrayList<>();
        try{
            for (String s : studentID_list) {
                ps = con.prepareStatement(sql);
                ps.setString(1, s);
                rs = ps.executeQuery();
                while (rs.next()) {
                    long s_serialNumber = rs.getLong("s_serialNumber");
                    String s_id = rs.getString("s_id");
                    String s_name = rs.getString("s_name");
                    String s_password = rs.getString("s_password");
                    String s_sex = rs.getString("s_sex");
                    String s_college = rs.getString("s_college");
                    String s_department = rs.getString("s_department");
                    String s_class = rs.getString("s_class");
                    String s_major = rs.getString("s_major");
                    student = new Student(s_serialNumber, s_id, s_name, s_password, s_sex, s_college, s_department, s_class, s_major);
                    students.add(student);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Pool.closeDBResource(rs,ps);
        }
        return students;
    }

    public static ArrayList<Student> getNoGradeListDao(Connection con, String lessonuuid, String sql1,String sql2) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Student student=null;
        ArrayList<Student> students=new ArrayList<>();
        try{
            ps=con.prepareStatement(sql1);
            ps.setString(1,lessonuuid);
            rs=ps.executeQuery();
            while (rs.next()){
                long s_serialNumber=rs.getLong("s_serialNumber");
                String s_id=rs.getString("s_id");
                String s_name=rs.getString("s_name");
                String s_password=rs.getString("s_password");
                String s_sex=rs.getString("s_sex");
                String s_college=rs.getString("s_college");
                String s_department=rs.getString("s_department");
                String s_class=rs.getString("s_class");
                String s_major=rs.getString("s_major");
                student=new Student(s_serialNumber,s_id,s_name,s_password,s_sex,s_college,s_department,s_class,s_major);
                students.add(student);
            }
            ps=con.prepareStatement(sql2);
            ps.setString(1,lessonuuid);
            rs=ps.executeQuery();
            while (rs.next()){
                long s_serialNumber=rs.getLong("s_serialNumber");
                String s_id=rs.getString("s_id");
                String s_name=rs.getString("s_name");
                String s_password=rs.getString("s_password");
                String s_sex=rs.getString("s_sex");
                String s_college=rs.getString("s_college");
                String s_department=rs.getString("s_department");
                String s_class=rs.getString("s_class");
                String s_major=rs.getString("s_major");
                student=new Student(s_serialNumber,s_id,s_name,s_password,s_sex,s_college,s_department,s_class,s_major);
                students.add(student);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Pool.closeDBResource(rs,ps);
        }
        return students;
    }

    public static ArrayList<Course> getcourseslist_madePaperDao(Connection con, String mydepart, String sql) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Course course=null;
        ArrayList<Course> courseArrayList=new ArrayList<>();
        try{
            ps=con.prepareStatement(sql);
            ps.setString(1,mydepart);
            rs=ps.executeQuery();
            while (rs.next()){
                int course_serialNumber=rs.getInt("course_serialNumber");
                String course_name=rs.getString("course_name");
                String course_creator=rs.getString("course_creator");
                course=new Course(course_serialNumber,course_name,course_creator);
                courseArrayList.add(course);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Pool.closeDBResource(rs,ps);
        }
        return courseArrayList;
    }

    public static ArrayList<QuestionBank> getQuestionBanks_manmadeDao(Connection con, String course, String sql) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        QuestionBank questionBank=null;
        ArrayList<QuestionBank> questionBankArrayList=new ArrayList<>();
        try{
            ps=con.prepareStatement(sql);
            ps.setString(1,course);
            rs=ps.executeQuery();
            while (rs.next()){
                long questionBank_serialNumber=rs.getLong("questionBank_serialNumber");
                String questionBank_creatorID=rs.getString("questionBank_creatorID");
                String questionBank_course=rs.getString("questionBank_course");
                String questionBank_createDate=rs.getString("questionBank_createDate");
                String questionBank_point=rs.getString("questionBank_point");
                int questionBank_type=rs.getInt("questionBank_type");
                String questionBank_title=rs.getString("questionBank_title");
                String questionBank_titleimage=rs.getString("questionBank_titleimage");
                String questionBank_option1=rs.getString("questionBank_option1");
                String questionBank_option2=rs.getString("questionBank_option2");
                String questionBank_option3=rs.getString("questionBank_option3");
                String questionBank_option4=rs.getString("questionBank_option4");
                String questionBank_answer=rs.getString("questionBank_answer");
                questionBank=new QuestionBank(questionBank_serialNumber,questionBank_creatorID,questionBank_course,
                        questionBank_createDate,questionBank_point,questionBank_type,questionBank_title,questionBank_titleimage,
                        questionBank_option1,questionBank_option2,questionBank_option3,questionBank_option4,
                        questionBank_answer);
                questionBankArrayList.add(questionBank);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            Pool.closeDBResource(rs,ps);
        }
        return questionBankArrayList;
    }

    public static ArrayList<Teacher> getteachers_manmadeDao(Connection con, String sql) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Teacher teacher=null;
        ArrayList<Teacher> teacherArrayList=new ArrayList<Teacher>();
        try{
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()){
                int t_serialNumber=rs.getInt("t_serialNumber");
                String t_id=rs.getString("t_id");
                String t_name=rs.getString("t_name");
                String t_password=rs.getString("t_password");
                String t_sex=rs.getString("t_sex");
                String t_college=rs.getString("t_college");
                String t_department=rs.getString("t_department");
                teacher=new Teacher(t_serialNumber,t_id,t_name,t_password,t_sex,t_college,t_department);
                teacherArrayList.add(teacher);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Pool.closeDBResource(rs,ps);
        }
        return teacherArrayList;
    }

    public static void createManMadeDao(Connection con,String sql,String sql2, String paperuuid,
                                        String papername, String createdate, String xuhao, String fenshu,String t_id) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            ps=con.prepareStatement(sql);
            ps.setLong(1,Long.parseLong(xuhao));
            rs=ps.executeQuery();
            if(rs.next()){
                long questionBank_serialNumber=rs.getLong("questionBank_serialNumber");
                String questionBank_creatorID=rs.getString("questionBank_creatorID");
                String questionBank_course=rs.getString("questionBank_course");
                String questionBank_createDate=rs.getString("questionBank_createDate");
                String questionBank_point=rs.getString("questionBank_point");
                int questionBank_type=rs.getInt("questionBank_type");
                String questionBank_title=rs.getString("questionBank_title");
                String questionBank_titleimage=rs.getString("questionBank_titleimage");
                String questionBank_option1=rs.getString("questionBank_option1");
                String questionBank_option2=rs.getString("questionBank_option2");
                String questionBank_option3=rs.getString("questionBank_option3");
                String questionBank_option4=rs.getString("questionBank_option4");
                String questionBank_answer=rs.getString("questionBank_answer");
                ps=con.prepareStatement(sql2);
                ps.setString(1,papername);
                ps.setString(2,paperuuid);
                ps.setString(3,t_id);
                ps.setString(4,createdate);
                ps.setString(5,questionBank_course);
                ps.setInt(6,questionBank_type);
                ps.setString(7,questionBank_title);
                ps.setString(8,questionBank_titleimage);
                ps.setString(9,questionBank_option1);
                ps.setString(10,questionBank_option2);
                ps.setString(11,questionBank_option3);
                ps.setString(12,questionBank_option4);
                ps.setString(13,questionBank_answer);
                ps.setDouble(14,Double.parseDouble(fenshu));
                int i=ps.executeUpdate();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Pool.closeDBResource(rs,ps);
        }
    }
}
