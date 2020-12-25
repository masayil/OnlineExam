package com.service.admin;

import com.dao.admin.UpdateDao;

import java.sql.Connection;

public class UpdateService {
    public static boolean resetTeaPwdService(Connection con, String teacherid) {
        String sql="update teacher set t_password=? where t_id=?";
        return UpdateDao.resetPwdDao(con,teacherid,sql);
    }

    public static boolean deleteTeaService(Connection con, String teacherid) {
        String sql="delete from teacher where t_id=?";
        return UpdateDao.deleteTeaAndStuDao(con,teacherid,sql);
    }

    public static boolean updateTeaService(Connection con, String teacherid, String teachername, String sex, String depart) {
        String sql="update teacher SET t_name=?,t_sex=?,t_department=? where t_id=?";
        return UpdateDao.updateTeaDao(con,teacherid,teachername,sex,depart,sql);
    }

    public static boolean importSingleTService(Connection con, String teacherid, String teachername, String teacherpwd, String sex, String college, String depart) {
        String sql="insert into teacher values (?,?,?,?,?,?)";
        return UpdateDao.importSingleTDao(con,teacherid,teachername,teacherpwd,sex,college,depart,sql);
    }

    public static boolean RemoveClassAdminService(Connection con, String chosenclass) {
        String sql1="delete from majorclass where class_1=?";
        String sql2="delete from student where s_class=?";
        return UpdateDao.RemoveClassAdminDao(con,chosenclass,sql1,sql2);
    }

    public static boolean addclassService(Connection con, String depart, String major, String class_1) {
        String sql="insert into majorclass values (?,?,?)";
        return UpdateDao.addclassDao(con,depart,major,class_1,sql);
    }

    public static boolean resetStuPwdService(Connection con, String studentid) {
        String sql="update student set s_password=? where s_id=?";
        return UpdateDao.resetPwdDao(con,studentid,sql);
    }

    public static boolean deleteStuService(Connection con, String studentid) {
        String sql="delete from student where s_id=?";
        return UpdateDao.deleteTeaAndStuDao(con,studentid,sql);
    }
}
