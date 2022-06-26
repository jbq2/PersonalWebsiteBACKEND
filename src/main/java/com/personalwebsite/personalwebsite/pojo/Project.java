package com.personalwebsite.personalwebsite.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    private long id;
    private String name;
    private String description;
    private String course;
    //TODO change type of project column 'course' to INT, and assign it to the id of courses table
    private String startdate;
    private String enddate;
    private String url;
    private String servedurl;
}
