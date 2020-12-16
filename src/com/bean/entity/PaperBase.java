package com.bean.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
*
* create table paperbase(
paperbase_serialNumber bigint identity(1,1) primary key,
paperbase_name nvarchar(30) NOT NULL,
paperbase_uuid nvarchar(32) NOT NULL,
        paperbase_creatorID nvarchar(12) NOT NULL,
        paperbase_createDate nvarchar(40),
        course nvarchar(50) NOT NULL,
        questiontype int NOT NULL,
        paperbase_title nvarchar(200),
        paperbase_titleimage nvarchar(100),
        paperbase_option1 nvarchar(100),
        paperbase_option2 nvarchar(100),
        paperbase_option3 nvarchar(100),
        paperbase_option4 nvarchar(100),
        paperbase_answer nvarchar(500),
        score float not null,
        );
* */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PaperBase {
    private Long paperbase_serialNumber;
    private String paperbase_name;
    private String paperbase_uuid;
    private String paperbase_creatorID;
    private String paperbase_createDate;
    private String course;
    private int questiontype;
    private String paperbase_title;
    private String paperbase_titleimage;
    private String paperbase_option1;
    private String paperbase_option2;
    private String paperbase_option3;
    private String paperbase_option4;
    private String paperbase_answer;
    private double score;
}
