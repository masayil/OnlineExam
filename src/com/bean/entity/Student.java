package com.bean.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 create table student(
s_serialNumber bigint identity(1,1) primary key,
s_id nvarchar(12) NOT NULL unique,
s_name nvarchar(20) NOT NULL,
s_password nvarchar(25) NOT NULL,
st_sex nvarchar(5),
s_college nvarchar(50),
s_department nvarchar(70),
s_class nvarchar(30),
s_major nvarchar(30),
);
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Student {
	private long s_serialNumber;
	private String s_id;
	private String s_name;
	private String s_password;
	private String s_sex;
	private String s_college;
	private String s_department;
	private String s_class;
	private String s_major;

}
