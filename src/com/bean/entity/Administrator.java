package com.bean.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * create table administrator(
a_serialNumber int identity(1,1) primary key,
a_id nvarchar(12) NOT NULL unique,
a_name nvarchar(20) NOT NULL,
a_password nvarchar(25) NOT NULL,
a_sex nvarchar(5) ,
);
 * */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Administrator {
	private int a_serialNumber;
	private String a_id;
	private String a_name;
	private String a_password;
	private String a_sex;

}
