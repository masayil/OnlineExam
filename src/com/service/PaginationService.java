package com.service;

import com.bean.entity.QuestionBank;
import com.bean.pagination.Bank;
import com.dao.PaginationDao;

import java.sql.Connection;
import java.util.ArrayList;

public class PaginationService {
    public static Bank t_ToBank_allService(Connection con, String questiontype,
                                           int curPageInt, int totalPageInt){
        Bank bank=null;
        int filterNum=(curPageInt-1)*Bank.OnePageNum;
        switch (questiontype){
            case "none":
                String sql1none="select top "+Bank.OnePageNum+" * from questionBank order by questionBank_course";
                String sql2none="select top "+Bank.OnePageNum+" * from questionBank where questionBank_title not in (select top "+filterNum+" questionBank_title from questionBank order by questionBank_course) order by questionBank_course";
                String sql3none="select * from questionBank order by questionBank_course";
                bank= PaginationDao.t_ToBank_allDao(con,curPageInt,totalPageInt,sql1none,sql2none,sql3none);
                break;
            case "type1":
                String sql1type1="select top "+Bank.OnePageNum+" * from questionBank where questionBank_type=1 order by questionBank_course";
                String sql2type1="select top "+Bank.OnePageNum+" * from questionBank where questionBank_title not in (select top "+filterNum+" questionBank_title from questionBank where questionBank_type=1 order by questionBank_course) and questionBank_type=1 order by questionBank_course";
                String sql3type1="select * from questionBank where questionBank_type=1 order by questionBank_course";
                bank= PaginationDao.t_ToBank_allDao(con,curPageInt,totalPageInt,sql1type1,sql2type1,sql3type1);
                break;
            case "type2":
                String sql1type2="select top "+Bank.OnePageNum+" * from questionBank where questionBank_type=2 order by questionBank_course";
                String sql2type2="select top "+Bank.OnePageNum+" * from questionBank where questionBank_title not in (select top "+filterNum+" questionBank_title from questionBank where questionBank_type=2) and questionBank_type=2 order by questionBank_course";
                String sql3type2="select * from questionBank where questionBank_type=2 order by questionBank_course";
                bank= PaginationDao.t_ToBank_allDao(con,curPageInt,totalPageInt,sql1type2,sql2type2,sql3type2);
                break;
            case "type3":
                String sql1type3="select top "+Bank.OnePageNum+" * from questionBank where questionBank_type=3 order by questionBank_course";
                String sql2type3="select top "+Bank.OnePageNum+" * from questionBank where questionBank_title not in (select top "+filterNum+" questionBank_title from questionBank where questionBank_type=3) and questionBank_type=3 order by questionBank_course";
                String sql3type3="select * from questionBank where questionBank_type=3 order by questionBank_course";
                bank= PaginationDao.t_ToBank_allDao(con,curPageInt,totalPageInt,sql1type3,sql2type3,sql3type3);
                break;
            case "type4":
                String sql1type4="select top "+Bank.OnePageNum+" * from questionBank where questionBank_type=4 order by questionBank_course";
                String sql2type4="select top "+Bank.OnePageNum+" * from questionBank where questionBank_title not in (select top "+filterNum+" questionBank_title from questionBank where questionBank_type=4) and questionBank_type=4 order by questionBank_course";
                String sql3type4="select * from questionBank where questionBank_type=4 order by questionBank_course";
                bank= PaginationDao.t_ToBank_allDao(con,curPageInt,totalPageInt,sql1type4,sql2type4,sql3type4);
                break;
        }
        return bank;
    }
    public static Bank t_ToBank_titlenameService(Connection con, String questiontype,
                                           int curPageInt, int totalPageInt,String text){
        Bank bank=null;
        int filterNum=(curPageInt-1)*Bank.OnePageNum;
        switch (questiontype){
            case "none":
                String sql1none="select top "+Bank.OnePageNum+" * from questionBank where questionBank_title LIKE ? order by questionBank_course";
                String sql2none="select top "+Bank.OnePageNum+" * from questionBank where questionBank_title not in (select top "+filterNum+" questionBank_title from questionBank where questionBank_title LIKE ? order by questionBank_course) and questionBank_title LIKE ? order by questionBank_course";
                String sql3none="select * from questionBank where questionBank_title LIKE ? order by questionBank_course";
                bank= PaginationDao.t_ToBank_titlenameDao(con,curPageInt,totalPageInt,sql1none,sql2none,sql3none,text);
                break;
            case "type1":
                String sql1type1="select top "+Bank.OnePageNum+" * from questionBank where questionBank_type=1 and questionBank_title LIKE ? order by questionBank_course";
                String sql2type1="select top "+Bank.OnePageNum+" * from questionBank where questionBank_title not in (select top "+filterNum+" questionBank_title from questionBank where questionBank_type=1 and questionBank_title LIKE ? order by questionBank_course) and questionBank_type=1 and questionBank_title LIKE ? order by questionBank_course";
                String sql3type1="select * from questionBank where questionBank_type=1 and questionBank_title LIKE ? order by questionBank_course";
                bank= PaginationDao.t_ToBank_titlenameDao(con,curPageInt,totalPageInt,sql1type1,sql2type1,sql3type1,text);
                break;
            case "type2":
                String sql1type2="select top "+Bank.OnePageNum+" * from questionBank where questionBank_type=2 and questionBank_title LIKE ? order by questionBank_course";
                String sql2type2="select top "+Bank.OnePageNum+" * from questionBank where questionBank_title not in (select top "+filterNum+" questionBank_title from questionBank where questionBank_type=2 and questionBank_title LIKE ? order by questionBank_course) and questionBank_type=2 and questionBank_title LIKE ? order by questionBank_course";
                String sql3type2="select * from questionBank where questionBank_type=2 and questionBank_title LIKE ? order by questionBank_course";
                bank= PaginationDao.t_ToBank_titlenameDao(con,curPageInt,totalPageInt,sql1type2,sql2type2,sql3type2,text);
                break;
            case "type3":
                String sql1type3="select top "+Bank.OnePageNum+" * from questionBank where questionBank_type=3 and questionBank_title LIKE ? order by questionBank_course";
                String sql2type3="select top "+Bank.OnePageNum+" * from questionBank where questionBank_title not in (select top "+filterNum+" questionBank_title from questionBank where questionBank_type=3 and questionBank_title LIKE ?) and questionBank_type=3 and questionBank_title LIKE ? order by questionBank_course";
                String sql3type3="select * from questionBank where questionBank_type=3 and questionBank_title LIKE ? order by questionBank_course";
                bank= PaginationDao.t_ToBank_titlenameDao(con,curPageInt,totalPageInt,sql1type3,sql2type3,sql3type3,text);
                break;
            case "type4":
                String sql1type4="select top "+Bank.OnePageNum+" * from questionBank where questionBank_type=4 and questionBank_title LIKE ? order by questionBank_course";
                String sql2type4="select top "+Bank.OnePageNum+" * from questionBank where questionBank_title not in (select top "+filterNum+" questionBank_title from questionBank where questionBank_type=4 and questionBank_title LIKE ? order by questionBank_course) and questionBank_type=4 and questionBank_title LIKE ? order by questionBank_course";
                String sql3type4="select * from questionBank where questionBank_type=4 and questionBank_title LIKE ? order by questionBank_course";
                bank= PaginationDao.t_ToBank_titlenameDao(con,curPageInt,totalPageInt,sql1type4,sql2type4,sql3type4,text);
                break;
        }
        return bank;
    }

    public static Bank t_ToBank_coursenameService(Connection con, String questiontype,
                                                 int curPageInt, int totalPageInt,String text){
        Bank bank=null;
        int filterNum=(curPageInt-1)*Bank.OnePageNum;
        switch (questiontype){
            case "none":
                String sql1none="select top "+Bank.OnePageNum+" * from questionBank where questionBank_course LIKE ? order by questionBank_course";
                String sql2none="select top "+Bank.OnePageNum+" * from questionBank where questionBank_title not in (select top "+filterNum+" questionBank_title from questionBank where questionBank_course LIKE ? order by questionBank_course) and questionBank_course LIKE ? order by questionBank_course";
                String sql3none="select * from questionBank where questionBank_course LIKE ? order by questionBank_course";
                bank= PaginationDao.t_ToBank_titlenameDao(con,curPageInt,totalPageInt,sql1none,sql2none,sql3none,text);
                break;
            case "type1":
                String sql1type1="select top "+Bank.OnePageNum+" * from questionBank where questionBank_type=1 and questionBank_course LIKE ? order by questionBank_course";
                String sql2type1="select top "+Bank.OnePageNum+" * from questionBank where questionBank_title not in (select top "+filterNum+" questionBank_title from questionBank where questionBank_type=1 and questionBank_course LIKE ? order by questionBank_course) and questionBank_type=1 and questionBank_course LIKE ? order by questionBank_course";
                String sql3type1="select * from questionBank where questionBank_type=1 and questionBank_course LIKE ? order by questionBank_course";
                bank= PaginationDao.t_ToBank_titlenameDao(con,curPageInt,totalPageInt,sql1type1,sql2type1,sql3type1,text);
                break;
            case "type2":
                String sql1type2="select top "+Bank.OnePageNum+" * from questionBank where questionBank_type=2 and questionBank_course LIKE ? order by questionBank_course";
                String sql2type2="select top "+Bank.OnePageNum+" * from questionBank where questionBank_title not in (select top "+filterNum+" questionBank_title from questionBank where questionBank_type=2 and questionBank_course LIKE ? order by questionBank_course) and questionBank_type=2 and questionBank_course LIKE ? order by questionBank_course";
                String sql3type2="select * from questionBank where questionBank_type=2 and questionBank_course LIKE ? order by questionBank_course";
                bank= PaginationDao.t_ToBank_titlenameDao(con,curPageInt,totalPageInt,sql1type2,sql2type2,sql3type2,text);
                break;
            case "type3":
                String sql1type3="select top "+Bank.OnePageNum+" * from questionBank where questionBank_type=3 and questionBank_course LIKE ? order by questionBank_course";
                String sql2type3="select top "+Bank.OnePageNum+" * from questionBank where questionBank_title not in (select top "+filterNum+" questionBank_title from questionBank where questionBank_type=3 and questionBank_course LIKE ? order by questionBank_course) and questionBank_type=3 and questionBank_course LIKE ? order by questionBank_course";
                String sql3type3="select * from questionBank where questionBank_type=3 and questionBank_course LIKE ? order by questionBank_course";
                bank= PaginationDao.t_ToBank_titlenameDao(con,curPageInt,totalPageInt,sql1type3,sql2type3,sql3type3,text);
                break;
            case "type4":
                String sql1type4="select top "+Bank.OnePageNum+" * from questionBank where questionBank_type=4 and questionBank_course LIKE ? order by questionBank_course";
                String sql2type4="select top "+Bank.OnePageNum+" * from questionBank where questionBank_title not in (select top "+filterNum+" questionBank_title from questionBank where questionBank_type=4 and questionBank_course LIKE ? order by questionBank_course) and questionBank_type=4 and questionBank_course LIKE ? order by questionBank_course";
                String sql3type4="select * from questionBank where questionBank_type=4 and questionBank_course LIKE ? order by questionBank_course";
                bank= PaginationDao.t_ToBank_titlenameDao(con,curPageInt,totalPageInt,sql1type4,sql2type4,sql3type4,text);
                break;
        }
        return bank;
    }

    public static Bank t_ToBank_teachernameService(Connection con, String questiontype,
                                                  int curPageInt, int totalPageInt,String text){
        Bank bank=null;
        int filterNum=(curPageInt-1)*Bank.OnePageNum;
        switch (questiontype){
            case "none":
                String sql1none="select top "+Bank.OnePageNum+" * from questionBank where questionBank_creatorID LIKE ? order by questionBank_course";
                String sql2none="select top "+Bank.OnePageNum+" * from questionBank where questionBank_title not in (select top "+filterNum+" questionBank_title from questionBank where questionBank_creatorID LIKE ? order by questionBank_course) and questionBank_creatorID LIKE ? order by questionBank_course";
                String sql3none="select * from questionBank where questionBank_creatorID LIKE ? order by questionBank_course";
                bank= PaginationDao.t_ToBank_titlenameDao(con,curPageInt,totalPageInt,sql1none,sql2none,sql3none,text);
                break;
            case "type1":
                String sql1type1="select top "+Bank.OnePageNum+" * from questionBank where questionBank_type=1 and questionBank_creatorID LIKE ? order by questionBank_course";
                String sql2type1="select top "+Bank.OnePageNum+" * from questionBank where questionBank_title not in (select top "+filterNum+" questionBank_title from questionBank where questionBank_type=1 and questionBank_creatorID LIKE ? order by questionBank_course) and questionBank_type=1 and questionBank_creatorID LIKE ? order by questionBank_course";
                String sql3type1="select * from questionBank where questionBank_type=1 and questionBank_creatorID LIKE ? order by questionBank_course";
                bank= PaginationDao.t_ToBank_titlenameDao(con,curPageInt,totalPageInt,sql1type1,sql2type1,sql3type1,text);
                break;
            case "type2":
                String sql1type2="select top "+Bank.OnePageNum+" * from questionBank where questionBank_type=2 and questionBank_creatorID LIKE ? order by questionBank_course";
                String sql2type2="select top "+Bank.OnePageNum+" * from questionBank where questionBank_title not in (select top "+filterNum+" questionBank_title from questionBank where questionBank_type=2 and questionBank_creatorID LIKE ? order by questionBank_course) and questionBank_type=2 and questionBank_creatorID LIKE ? order by questionBank_course";
                String sql3type2="select * from questionBank where questionBank_type=2 and questionBank_creatorID LIKE ? order by questionBank_course";
                bank= PaginationDao.t_ToBank_titlenameDao(con,curPageInt,totalPageInt,sql1type2,sql2type2,sql3type2,text);
                break;
            case "type3":
                String sql1type3="select top "+Bank.OnePageNum+" * from questionBank where questionBank_type=3 and questionBank_creatorID LIKE ? order by questionBank_course";
                String sql2type3="select top "+Bank.OnePageNum+" * from questionBank where questionBank_title not in (select top "+filterNum+" questionBank_title from questionBank where questionBank_type=3 and questionBank_creatorID LIKE ? order by questionBank_course) and questionBank_type=3 and questionBank_creatorID LIKE ? order by questionBank_course";
                String sql3type3="select * from questionBank where questionBank_type=3 and questionBank_creatorID LIKE ? order by questionBank_course";
                bank= PaginationDao.t_ToBank_titlenameDao(con,curPageInt,totalPageInt,sql1type3,sql2type3,sql3type3,text);
                break;
            case "type4":
                String sql1type4="select top "+Bank.OnePageNum+" * from questionBank where questionBank_type=4 and questionBank_creatorID LIKE ? order by questionBank_course";
                String sql2type4="select top "+Bank.OnePageNum+" * from questionBank where questionBank_title not in (select top "+filterNum+" questionBank_title from questionBank where questionBank_type=4 and questionBank_creatorID LIKE ? order by questionBank_course) and questionBank_type=4 and questionBank_creatorID LIKE ? order by questionBank_course";
                String sql3type4="select * from questionBank where questionBank_type=4 and questionBank_creatorID LIKE ? order by questionBank_course";
                bank= PaginationDao.t_ToBank_titlenameDao(con,curPageInt,totalPageInt,sql1type4,sql2type4,sql3type4,text);
                break;
        }
        return bank;
    }
}
