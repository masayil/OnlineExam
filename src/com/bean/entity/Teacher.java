package com.bean.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * create table teacher(
t_serialNumber int identity(1,1) primary key,
t_id nvarchar(12) NOT NULL unique,
t_name nvarchar(20) NOT NULL,
t_password nvarchar(25) NOT NULL,
t_sex nvarchar(5),
t_college nvarchar(50),
t_department nvarchar(70),
);
 * */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
	private int t_serialNumber;
	private String t_id;
	private String t_name;
	private String t_password;
	private String t_sex;
	private String t_college;
	private String t_department;
}
