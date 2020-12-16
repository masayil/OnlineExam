package com.service;

import com.dao.ChangePasswordDao;

import java.sql.Connection;

public class ChangePasswordService {
    public static Boolean StudentPasswordService(String password, String id, Connection con){
        String sql="update student set s_password=? where s_id=?";
        return ChangePasswordDao.PasswordChangeDao(password,id,con,sql);
    }

    public static Boolean TeacherPasswordService(String password, String id, Connection con){
        String sql="update teacher set t_password=? where t_id=?";
        return ChangePasswordDao.PasswordChangeDao(password,id,con,sql);
    }
}
