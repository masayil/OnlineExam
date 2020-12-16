package com.bean.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
*
* create table questionBank(
questionBank_serialNumber bigint identity(1,1) primary key,
questionBank_creatorID nvarchar(12) NOT NULL,
questionBank_course nvarchar(50) NOT NULL,
        questionBank_createDate nvarchar(40),
        questionBank_point nvarchar(50) NOT NULL,
        questionBank_type int NOT NULL,
        questionBank_title nvarchar(200),
        questionBank_titleimage nvarchar(100),
        questionBank_option1 nvarchar(100),
        questionBank_option2 nvarchar(100),
        questionBank_option3 nvarchar(100),
        questionBank_option4 nvarchar(100),
        questionBank_answer nvarchar(500),
        );
*
*
* */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionBank {
    private long questionBank_serialNumber;
    private String questionBank_creatorID;
    private String questionBank_course;
    private String questionBank_createDate;
    private String questionBank_point;
    private int questionBank_type;
    private String questionBank_title;
    private String questionBank_titleimage;
    private String questionBank_option1;
    private String questionBank_option2;
    private String questionBank_option3;
    private String questionBank_option4;
    private String questionBank_answer;
}
