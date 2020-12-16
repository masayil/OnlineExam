package com.bean.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
* create table grade(
grade_serialNumber bigint identity(1,1) primary key,
objectivequestion float,
subjectivequestion float,
total float,
studentID nvarchar(12) NOT NULL,
examAssignuuid nvarchar(32) NOT NULL, 考试安排uuid
);
*
*
* */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Grade {
    private Long grade_serialNumber;
    private double objectivequestion;
    private double subjectivequestion;
    private double total;
    private String studentID;
    private String examAssignuuid;
}
