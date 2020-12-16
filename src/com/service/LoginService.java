package com.service;

import com.bean.entity.Administrator;
import com.bean.entity.Student;
import com.bean.entity.Teacher;
import com.dao.LoginDao;
import java.sql.*;


public class LoginService {
    public static Student stuloginService(String id, String password, Connection con) throws SQLException {
        String sql="select * from student where s_id=? and s_password=?";
        return LoginDao.stuloginDao(id,password,con,sql);
    }
    public static Teacher tealoginService(String id, String password, Connection con) throws SQLException {
        String sql="select * from teacher where t_id=? and t_password=?";
        return LoginDao.tealoginDao(id,password,con,sql);
    }
    public static Administrator adminloginService(String id, String password, Connection con) throws SQLException {
        String sql="select * from administrator where a_id=? and a_password=?";
        return LoginDao.adminloginDao(id,password,con,sql);
    }
}
