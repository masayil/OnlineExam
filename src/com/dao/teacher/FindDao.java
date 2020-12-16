package com.dao.teacher;
import com.bean.entity.*;
import com.myutil.Pool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
}
