package com.bean.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
* create table examAssign(
examAssign_serialNumber int identity(1,1) primary key,
examAssign_uuid nvarchar(32) NOT NULL unique,            考试安排uuid
lessonuuid nvarchar(32) NOT NULL,                  课程表uuid
        startTime nvarchar(40),
        endTime nvarchar(40),
        lasttime int,
        examAssign_createDate nvarchar(40),       创建时间
        paperbaseuuid nvarchar(32) NOT NULL,             试卷uuid
        examAssign_name nvarchar(40),考试名称
        );
* */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ExamAssign {
    private int examAssign_serialNumber;
    private String examAssign_uuid;
    private String lessonuuid;
    private String startTime;
    private String endTime;
    private int lasttime;
    private String examAssign_createDate;
    private String paperbaseuuid;
    private String examAssign_name;
    private double totalscore;

}
