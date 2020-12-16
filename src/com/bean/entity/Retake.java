package com.bean.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
* create table retake(
retake_serialNumber int identity(1,1) primary key,
lessonuuid nvarchar(32) NOT NULL,             课程表uuid
retake_studentID nvarchar(12) NOT NULL,          学生ID
        );
* */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Retake {
    private int retake_serialNumber;
    private String lessonuuid;
    private String retake_studentID;
}
