package com.bean.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
* create table course(
course_serialNumber int identity(1,1) primary key,
course_name nvarchar(50) NOT NULL unique,课程名字
course_creator nvarchar(70) NOT NULL,  开设院系
        );
* */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    private int course_serialNumber;
    private String course_name;
    private String course_creator;
}
