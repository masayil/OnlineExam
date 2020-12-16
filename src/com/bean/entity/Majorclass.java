package com.bean.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
* create table majorclass(
majorclass_serialNumber int identity(1,1) primary key,
department nvarchar(50) NOT NULL,
major nvarchar(70) NOT NULL,
class_1 nvarchar(30) NOT NULL unique,
);
* */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Majorclass {
    private int majorclass_serialNumber;
    private String department;
    private String major;
    private String class_1;
}
