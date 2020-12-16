package com.bean.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Part_paperbase {
    private String paperbase_name;
    private String paperbase_uuid;
    private String paperbase_createDate;
    private String course;
}
