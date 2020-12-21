package com.dao.teacher;
import com.bean.entity.*;
import com.myutil.Pool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class FindDao {
    public static ArrayList<Majorclass> majorclassDao(Connection con,String sql){
        PreparedStatement ps = null;
        ResultSet rs = null;
        Majorclass majorclass=null;
        ArrayList<Majorclass> classlist= new ArrayList<Majorclass>();
        try{
            ps=con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                int majorclass_serialNumber=rs.getInt("majorclass_serialNumber");
                String department=rs.getString("department");
                String major=rs.getString("major");
                String class_1=rs.getString("class_1");
                majorclass=new Majorclass(majorclass_serialNumber,department,major,class_1);
                classlist.add(majorclass);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Pool.closeDBResource(rs,ps);
        }
        return classlist;
    }

    public static ArrayList<Course> courseDao(Connection con, String department,String sql){
        PreparedStatement ps = null;
        ResultSet rs = null;
        Course course=null;
        ArrayList<Course> courselist= new ArrayList<Course>();
        try{
            ps=con.prepareStatement(sql);
            ps.setString(1,department);
            rs = ps.executeQuery();
            while (rs.next()){
                int course_serialNumber=rs.getInt("course_serialNumber");
                String course_name=rs.getString("course_name");
                String course_creator=rs.getString("course_creator");
                course=new Course(course_serialNumber,course_name,course_creator);
                courselist.add(course);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Pool.closeDBResource(rs,ps);
        }
        return courselist;
    }

    public static ArrayList<Student> getStudentListDao(Connection con, String keyvalues,String sql){
        PreparedStatement ps = null;
        ResultSet rs = null;
        Student student=null;
        ArrayList<Student> studentlist=new ArrayList<Student>();
        try{
            ps=con.prepareStatement(sql);
            ps.setString(1,keyvalues);
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
                studentlist.add(student);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            Pool.closeDBResource(rs,ps);
        }
        return studentlist;
    }

    public static  ArrayList<Part_paperbase> getPart_paperbaseDao(Connection con, String teacherid,String sql){
        PreparedStatement ps = null;
        ResultSet rs = null;
        Part_paperbase part_paperbase=null;
        ArrayList<Part_paperbase> part_paperbaseArrayList=new ArrayList<Part_paperbase>();
        try{
            ps=con.prepareStatement(sql);
            ps.setString(1,teacherid);
            rs=ps.executeQuery();
            while (rs.next()){
                String paperbase_name=rs.getString("paperbase_name");
                String paperbase_uuid=rs.getString("paperbase_uuid");
                String paperbase_createDate=rs.getString("paperbase_createDate");
                String course=rs.getString("course");
                part_paperbase=new Part_paperbase(paperbase_name,paperbase_uuid,paperbase_createDate,course);
                part_paperbaseArrayList.add(part_paperbase);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Pool.closeDBResource(rs,ps);
        }
        return part_paperbaseArrayList;
    }

    public static ArrayList<PaperBase> getPaper_baseDao(Connection con,String paperuuid,String sql){
        PreparedStatement ps = null;
        ResultSet rs = null;
        PaperBase paperBase=null;
        ArrayList<PaperBase> paperBaseArrayList=new ArrayList<PaperBase>();
        try{
            ps=con.prepareStatement(sql);
            ps.setString(1,paperuuid);
            rs=ps.executeQuery();
            while (rs.next()){
                long paperbase_serialNumber=rs.getLong("paperbase_serialNumber");
                String paperbase_name=rs.getString("paperbase_name");
                String paperbase_uuid=rs.getString("paperbase_uuid");
                String paperbase_creatorID=rs.getString("paperbase_creatorID");
                String paperbase_createDate=rs.getString("paperbase_createDate");
                String course=rs.getString("course");
                int questiontype=rs.getInt("questiontype");
                String paperbase_title=rs.getString("paperbase_title");
                String paperbase_titleimage=rs.getString("paperbase_titleimage");
                String paperbase_option1=rs.getString("paperbase_option1");
                String paperbase_option2=rs.getString("paperbase_option2");
                String paperbase_option3=rs.getString("paperbase_option3");
                String paperbase_option4=rs.getString("paperbase_option4");
                String paperbase_answer=rs.getString("paperbase_answer");
                double score=rs.getDouble("score");
                paperBase=new PaperBase(paperbase_serialNumber,paperbase_name,paperbase_uuid,paperbase_creatorID,
                        paperbase_createDate,course,questiontype,paperbase_title,paperbase_titleimage,
                        paperbase_option1,paperbase_option2,paperbase_option3,paperbase_option4,paperbase_answer,score);
                paperBaseArrayList.add(paperBase);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Pool.closeDBResource(rs,ps);
        }
        return paperBaseArrayList;
    }

    public static boolean removePaperBaseDao(Connection con, String paperuuid,String sql){
        PreparedStatement ps = null;
        try{
            ps=con.prepareStatement(sql);
            ps.setString(1,paperuuid);
            int i=ps.executeUpdate();
            if(i>=1){
                return true;
            }
        }catch (SQLException e){
        e.printStackTrace();
        }finally {
        Pool.close(ps);
        }
        return false;
    }

    public static ArrayList<Teacher> getTeacherManageBankDao(Connection con,String department,String sql){
        PreparedStatement ps = null;
        ResultSet rs = null;
        Teacher teacher=null;
        ArrayList<Teacher> teacherArrayList=new ArrayList<Teacher>();
        try{
            ps=con.prepareStatement(sql);
            ps.setString(1,department);
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

    public static ArrayList<Course> getCourseListDao(Connection con,String t_department,String sql){
        PreparedStatement ps = null;
        ResultSet rs = null;
        Course course=null;
        ArrayList<Course> courseArrayList=new ArrayList<Course>();
        try{
            ps=con.prepareStatement(sql);
            ps.setString(1,t_department);
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

    public static boolean deleteBankDao(Connection con,String serialNumber,String sql){
        PreparedStatement ps = null;
        long s=Long.parseLong(serialNumber);
        try{
            ps=con.prepareStatement(sql);
            ps.setLong(1,s);
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

    public static ArrayList<Integer> CreatePaperDao(Connection con,String course,String sql){
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Integer> count=new ArrayList<Integer>();
        try{
            ps=con.prepareStatement(sql);
            ps.setString(1,course);
            rs=ps.executeQuery();
            while (rs.next()){
                int mark=rs.getInt("questionBank_type");
                count.add(mark);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Pool.closeDBResource(rs,ps);
        }
        return count;
    }

    public static HashMap<String,ArrayList<QuestionBank>> getBank_generatePaperDao(Connection con,String sql,String thiscourse){
        PreparedStatement ps = null;
        ResultSet rs = null;
        QuestionBank questionBank=null;
        ArrayList<QuestionBank> banklist1=new ArrayList<QuestionBank>();
        ArrayList<QuestionBank> banklist2=new ArrayList<QuestionBank>();
        ArrayList<QuestionBank> banklist3=new ArrayList<QuestionBank>();
        ArrayList<QuestionBank> banklist4=new ArrayList<QuestionBank>();
        HashMap<String, ArrayList<QuestionBank>> map1= new HashMap<>();
        try {
            ps=con.prepareStatement(sql);
            ps.setString(1,thiscourse);
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
                switch (questionBank_type){
                    case 1:
                        banklist1.add(questionBank);
                        break;
                    case 2:
                        banklist2.add(questionBank);
                        break;
                    case 3:
                        banklist3.add(questionBank);
                        break;
                    case 4:
                        banklist4.add(questionBank);
                        break;
                }
            }
            map1.put("danxuan",banklist1);
            map1.put("duoxuan",banklist2);
            map1.put("panduan",banklist3);
            map1.put("jianda",banklist4);
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Pool.closeDBResource(rs,ps);
        }
        return map1;
    }

    public static void createPaperBaseDao(ArrayList<QuestionBank> want,String teacherid,
                                            double score,String this_paperBaseuuid,String createTime,
                                            String papername,String thiscourse,String sql,Connection con) {
        PreparedStatement ps=null;
        try{
            ps=con.prepareStatement(sql);
            for(int i=0;i<want.size();i++){
                ps.setString(1,papername);
                ps.setString(2,this_paperBaseuuid);
                ps.setString(3,teacherid);
                ps.setString(4,createTime);
                ps.setString(5,thiscourse);
                ps.setInt(6,want.get(i).getQuestionBank_type());
                ps.setString(7,want.get(i).getQuestionBank_title());
                ps.setString(8,want.get(i).getQuestionBank_titleimage());
                ps.setString(9,want.get(i).getQuestionBank_option1());
                ps.setString(10,want.get(i).getQuestionBank_option2());
                ps.setString(11,want.get(i).getQuestionBank_option3());
                ps.setString(12,want.get(i).getQuestionBank_option4());
                ps.setString(13,want.get(i).getQuestionBank_answer());
                ps.setDouble(14,score);
                ps.addBatch();
            }
            ps.executeBatch();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Pool.close(ps);
        }
    }

    public static ArrayList<PaperBase> prepareExamDao(Connection con,String thiscourse,String sql){
        PreparedStatement ps = null;
        ResultSet rs = null;
        PaperBase paperBase=new PaperBase();
        ArrayList<PaperBase> paperBaseArrayList=new ArrayList<PaperBase>();
        try{
            ps=con.prepareStatement(sql);
            ps.setString(1,thiscourse);
            rs=ps.executeQuery();
            while (rs.next()){
                String paperbase_name=rs.getString("paperbase_name");
                String paperbase_uuid=rs.getString("paperbase_uuid");
                if(paperbase_name!=null&&paperbase_uuid!=null){
                paperBase.setPaperbase_name(paperbase_name);
                paperBase.setPaperbase_uuid(paperbase_uuid);
                paperBaseArrayList.add(paperBase);}
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Pool.closeDBResource(rs,ps);
        }
        return paperBaseArrayList;
    }
}
