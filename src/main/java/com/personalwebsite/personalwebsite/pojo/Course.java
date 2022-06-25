package com.personalwebsite.personalwebsite.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    private Long id;
    private String code;
    private String title;
    private String startdate;
    private String enddate;
}
