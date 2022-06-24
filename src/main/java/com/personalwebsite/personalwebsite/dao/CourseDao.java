package com.personalwebsite.personalwebsite.dao;

import com.personalwebsite.personalwebsite.pojo.Course;
import com.personalwebsite.personalwebsite.pojo.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.Collection;

public class CourseDao implements DaoInterface<Course> {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    RowMapper<Course> rowMapper = ((rs, rowNum) -> {
        Course course = new Course();
        course.setCode(rs.getString("code"));
        course.setTitle(rs.getString("title"));

        return course;
    });

    @Override
    public Collection<Course> findAll() {
        String sql = "SELECT * FROM localdb.COURSES";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public Course findById(Long id) {
        return null;
    }

    @Override
    public Course save(Course course) {
        return null;
    }

    @Override
    public Course update(Course course) {
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }
}
