package com.bean.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
* create table paper(
paper_serialNumber bigint identity(1,1) primary key,
studentID nvarchar(12) NOT NULL,
examAssignuuid nvarchar(32) NOT NULL, 考试安排uuid
questiontype int NOT NULL,                 题目类型
        paper_title nvarchar(200),
        paper_titleimage nvarchar(100),
        paper_option1 nvarchar(100),
        paper_option2 nvarchar(100),
        paper_option3 nvarchar(100),
        paper_option4 nvarchar(100),
        paper_answer nvarchar(500),
        youranswer nvarchar(500),
        );
*
* */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Paper {
    private long paper_serialNumber;
    private String studentID;
    private String examAssignuuid;
    private int questiontype;
    private String paper_title;
    private String paper_titleimage;
    private String paper_option1;
    private String paper_option2;
    private String paper_option3;
    private String paper_option4;
    private String paper_answer;
    private String youranswer;
    private double score;

}
