package com.dao.admin;

import com.bean.entity.*;
import com.myutil.Pool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QueryDao {
    public static ArrayList<Teacher> getTeacherListDao(Connection con, String sql) {
        PreparedStatement ps=null;
        ResultSet rs=null;
        Teacher teacher=null;
        ArrayList<Teacher> teacherArrayList=new ArrayList<>();
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

    public static ArrayList<Newlesson> getNewLessonListDao(Connection con, String t_id, String sql) {
        PreparedStatement ps=null;
        ResultSet rs=null;
        Newlesson newlesson=null;
        ArrayList<Newlesson> newlessonArrayList=new ArrayList<>();
        try{
            ps=con.prepareStatement(sql);
            ps.setString(1,t_id);
            rs=ps.executeQuery();
            while (rs.next()){
                int newlesson_serialNumber=rs.getInt("newlesson_serialNumber");
                String newlesson_uuid=rs.getString("newlesson_uuid");
                String newlesson_name=rs.getString("newlesson_name");
                String newlesson_creatorID=rs.getString("newlesson_creatorID");
                String newlesson_class=rs.getString("newlesson_class");
                String newlesson_createDate=rs.getString("newlesson_createDate");
                newlesson=new Newlesson(newlesson_serialNumber,newlesson_uuid,newlesson_name,newlesson_creatorID,newlesson_class,newlesson_createDate);
                newlessonArrayList.add(newlesson);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Pool.closeDBResource(rs,ps);
        }
        return newlessonArrayList;
    }

    public static Teacher getThisTeacherDao(Connection con, String teacherid, String sql) {
        PreparedStatement ps=null;
        ResultSet rs=null;
        Teacher teacher=null;
        try {
            ps=con.prepareStatement(sql);
            ps.setString(1,teacherid);
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
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Pool.closeDBResource(rs,ps);
        }
        return teacher;
    }

    public static ArrayList<Teacher> manageTeacher1Dao(Connection con, String sql) {
        PreparedStatement ps=null;
        ResultSet rs=null;
        Teacher teacher=null;
        ArrayList<Teacher> teacherArrayList=new ArrayList<>();
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

    public static ArrayList<Teacher> manageTeacher2Dao(Connection con, String sql, String depart) {
        PreparedStatement ps=null;
        ResultSet rs=null;
        Teacher teacher=null;
        ArrayList<Teacher> teacherArrayList=new ArrayList<>();
        try{
            ps=con.prepareStatement(sql);
            ps.setString(1,depart);
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

    public static ArrayList<Teacher> manageTeacher3Dao(Connection con, String sql, String teachername) {
        PreparedStatement ps=null;
        ResultSet rs=null;
        Teacher teacher=null;
        ArrayList<Teacher> teacherArrayList=new ArrayList<>();
        try{
            ps=con.prepareStatement(sql);
            ps.setString(1,"%"+teachername+"%");
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

    public static boolean hasTeaDao(Connection con, String teacherid,String sql) {
        PreparedStatement ps=null;
        ResultSet rs=null;
        Teacher teacher=null;
        try{
            ps=con.prepareStatement(sql);
            ps.setString(1,teacherid);
            rs=ps.executeQuery();
            if(rs.next()){
                return false;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Pool.closeDBResource(rs,ps);
        }
        return true;
    }

    public static ArrayList<Majorclass> getMajorClasslistDao(Connection con, String sql) {
        PreparedStatement ps=null;
        ResultSet rs=null;
        Majorclass majorclass=null;
        ArrayList<Majorclass> majorclassArrayList=new ArrayList<>();
        try{
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()){
                int majorclass_serialNumber=rs.getInt("majorclass_serialNumber");
                String department=rs.getString("department");
                String major=rs.getString("major");
                String class_1=rs.getString("class_1");
                majorclass=new Majorclass(majorclass_serialNumber,department,major,class_1);
                majorclassArrayList.add(majorclass);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Pool.closeDBResource(rs,ps);
        }
        return majorclassArrayList;
    }

    public static ArrayList<Student> getAllStudentAdminDao(Connection con, String sql) {
        PreparedStatement ps=null;
        ResultSet rs=null;
        Student student=null;
        ArrayList<Student> studentArrayList=new ArrayList<>();
        try{
            ps=con.prepareStatement(sql);
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
                studentArrayList.add(student);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Pool.closeDBResource(rs,ps);
        }
        return studentArrayList;
    }

    public static ArrayList<Student> getSpecialStudentAdminDao(Connection con, String key,String sql) {
        PreparedStatement ps=null;
        ResultSet rs=null;
        Student student=null;
        ArrayList<Student> studentArrayList=new ArrayList<>();
        try{
            ps=con.prepareStatement(sql);
            ps.setString(1,key);
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
                studentArrayList.add(student);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Pool.closeDBResource(rs,ps);
        }
        return studentArrayList;
    }

    public static boolean addstuSingleDao(Connection con, String studentid, String studentname, String studentpwd,
                                          String sex, String college, String depart,
                                          String classselect, String major, String sql) {
        PreparedStatement ps=null;
        try{
            ps=con.prepareStatement(sql);
            ps.setString(1,studentid);
            ps.setString(2,studentname);
            ps.setString(3,studentpwd);
            ps.setString(4,sex);
            ps.setString(5,college);
            ps.setString(6,depart);
            ps.setString(7,classselect);
            ps.setString(8,major);
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

    public static ArrayList<Student> getSearchStudentAdminDao(Connection con, String studentname, String sql) {
        PreparedStatement ps=null;
        ResultSet rs=null;
        Student student=null;
        ArrayList<Student> studentArrayList=new ArrayList<>();
        try{
            ps=con.prepareStatement(sql);
            ps.setString(1,"%"+studentname+"%");
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
                studentArrayList.add(student);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Pool.closeDBResource(rs,ps);
        }
        return studentArrayList;
    }

    public static ArrayList<Course> getCourseListDao(Connection con, String sql) {
        PreparedStatement ps=null;
        ResultSet rs=null;
        Course course=null;
        ArrayList<Course> courseArrayList=new ArrayList<>();
        try{
            ps=con.prepareStatement(sql);
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
