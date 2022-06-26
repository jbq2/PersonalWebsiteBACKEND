package com.personalwebsite.personalwebsite.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    private Long id;
    private String name;
    private String description;
    private Long course_id;
    private String startdate;
    private String enddate;
    private String url;
    private String servedurl;
}
