package com.bean.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
* create table newlesson(
newlesson_serialNumber int identity(1,1) primary key,
newlesson_uuid nvarchar(32) NOT NULL unique,      课程表uuid
newlesson_name nvarchar(50) NOT NULL,                 课程名字
        newlesson_creatorID nvarchar(12) NOT NULL,             老师ID
        newlesson_class nvarchar(30) NOT NULL,                  上课班级
        newlesson_createDate nvarchar(40), 课程开设时间
        );
*
* */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Newlesson {
    private int newlesson_serialNumber;
    private String newlesson_uuid;
    private String newlesson_name;
    private String newlesson_creatorID;
    private String newlesson_class;
    private String newlesson_createDate;

}
