package com.personalwebsite.personalwebsite.dao.implementations;

import com.personalwebsite.personalwebsite.dao.DaoInterface;
import com.personalwebsite.personalwebsite.pojo.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

@Component
public class CourseDao implements DaoInterface<Course> {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    //TODO autowiring causes build issues
    RowMapper<Course> rowMapper = ((rs, rowNum) -> {
        Course course = new Course();
        course.setId(rs.getLong("id"));
        course.setCode(rs.getString("code"));
        course.setTitle(rs.getString("title"));
        course.setStartdate(rs.getDate("startdate").toString());
        try{
            course.setEnddate(rs.getDate("enddate").toString());
        }
        catch(NullPointerException e){
            course.setEnddate(null);
        }

        return course;
    });

    @Override
    public Collection<Course> findAll() {
        String sql =
                "SELECT * FROM courses " +
                "ORDER BY startdate DESC";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public Course findById(Long id) {
        String sql =
                "SELECT * FROM courses " +
                "WHERE id = ?";
        try{
            return jdbcTemplate.queryForObject(sql, rowMapper, id);
        }
        catch(EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public Course save(Course course) {
        String sql = "INSERT INTO courses (code, title, startdate, enddate)" +
                "VALUES (?, ?, ?, ?)";
        Date startDate = Date.valueOf(course.getStartdate());
        Date endDate = null;

        if(course.getEnddate() != null){
            endDate = Date.valueOf(course.getEnddate());
        }

        jdbcTemplate.update(sql,
                course.getCode(),
                course.getTitle(),
                startDate,
                endDate
        );

        sql =
                "SELECT * FROM courses " +
                "ORDER BY id DESC " +
                "LIMIT 1";

        return jdbcTemplate.queryForObject(sql, rowMapper);
    }

    @Override
    public Course update(Course course) {
        String sql = "UPDATE courses " +
                "SET code = ?, title = ?, startdate = ?, enddate = ? " +
                "WHERE id = ?";

        Date startDate = Date.valueOf(course.getStartdate());
        Date endDate = null;

        if(course.getEnddate() != null){
            endDate = Date.valueOf(course.getEnddate());
        }

        jdbcTemplate.update(sql,
                course.getCode(),
                course.getTitle(),
                startDate,
                endDate,
                course.getId()
        );

        return course;
    }

    @Override
    public Boolean delete(Long id) {
        String sql = "DELETE FROM courses " +
                "WHERE id = ?";
        int affectedRows = jdbcTemplate.update(sql, id);

        return affectedRows == 1;
    }
}
