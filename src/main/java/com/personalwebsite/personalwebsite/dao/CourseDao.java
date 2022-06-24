package com.personalwebsite.personalwebsite.dao;

import com.personalwebsite.personalwebsite.pojo.Course;
import com.personalwebsite.personalwebsite.pojo.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class CourseDao implements DaoInterface<Course> {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    RowMapper<Course> rowMapper = ((rs, rowNum) -> {
        Course course = new Course();
        course.setId(rs.getLong("id"));
        course.setCode(rs.getString("code"));
        course.setTitle(rs.getString("title"));

        return course;
    });

    @Override
    public List<Course> findAll() {
        String sql =
                "SELECT * FROM localdb.COURSES";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public Course findById(Long id) {
        String sql =
                "SELECT * FROM localdb.COURSES " +
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
        String sql = "INSERT INTO localdb.COURSES (code, title)" +
                "VALUES (?, ?)";

        jdbcTemplate.update(sql,
                course.getCode(),
                course.getTitle()
        );

        sql =
                "SELECT * FROM localdb.COURSES " +
                "ORDER BY id DESC " +
                "LIMIT 1";

        return jdbcTemplate.queryForObject(sql, rowMapper);
    }

    @Override
    public Course update(Course course) {
        String sql = "UPDATE localdb.COURSES " +
                "SET code = ?, title = ? " +
                "WHERE id = ?";

        jdbcTemplate.update(sql,
                course.getCode(),
                course.getTitle(),
                course.getId()
        );

        return course;
    }

    @Override
    public Boolean delete(Long id) {
        String sql = "DELETE FROM localdb.COURSES " +
                "WHERE id = ?";
        int affectedRows = jdbcTemplate.update(sql, id);

        return affectedRows == 1;
    }
}
