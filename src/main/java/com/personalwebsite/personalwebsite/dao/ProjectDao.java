package com.personalwebsite.personalwebsite.dao;

import com.personalwebsite.personalwebsite.pojo.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.sql.Date;
import java.util.List;

@Component
public class ProjectDao implements DaoInterface<Project> {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    RowMapper<Project> rowMapper = ((rs, rowNum) -> {
        Project project = new Project();
        project.setId(rs.getLong("id"));
        project.setName(rs.getString("name"));
        project.setDescription(rs.getString("description"));
        project.setCourse(rs.getString("course"));
        project.setStartdate(rs.getDate("startdate").toString());
        try{
            project.setEnddate(rs.getDate("enddate").toString());
        }
        catch(NullPointerException e){
            project.setEnddate(null);
        }
        project.setUrl(rs.getString("url"));
        return project;
    });

    @Override
    public List<Project> findAll() {
        String sql =
                "SELECT * FROM localdb.projects " +
                "ORDER BY id";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public Project findById(Long id) {
        String sql =
                "SELECT * FROM localdb.projects " +
                "WHERE id = ?";
        try{
            return jdbcTemplate.queryForObject(sql, rowMapper, id);
        }
        catch(EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public Project save(Project project) {
        String sql =
                "INSERT INTO localdb.projects (name, description, course, startdate, enddate, url) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        Date startDate = Date.valueOf(project.getStartdate());
        Date endDate = null;

        if(project.getEnddate() != null){
            endDate = Date.valueOf(project.getEnddate());
        }

        jdbcTemplate.update(sql,
                project.getName(),
                project.getDescription(),
                project.getCourse(),
                startDate,
                endDate,
                project.getUrl()
        );

        sql =
            "SELECT * FROM localdb.projects " +
            "ORDER BY id DESC " +
            "LIMIT 1";

        return jdbcTemplate.queryForObject(sql, rowMapper);
    }

    @Override
    public Project update(Project project) {
        String sql =
                "UPDATE localdb.projects " +
                "SET name = ?, description = ?, course = ?, url = ?, startdate = ?, enddate = ? " +
                "WHERE id = ?";
        Date startDate = Date.valueOf(project.getStartdate());
        Date endDate = null;

        if(project.getEnddate() != null){
            endDate = Date.valueOf(project.getEnddate());
        }

        jdbcTemplate.update(sql,
                project.getName(),
                project.getDescription(),
                project.getCourse(),
                project.getUrl(),
                startDate,
                endDate,
                project.getId()
        );

        return project;
    }

    @Override
    public Boolean delete(Long id) {
        String sql =
                "DELETE FROM localdb.projects " +
                "WHERE id = ?";
        int affectedRows = jdbcTemplate.update(sql, id);

        return affectedRows == 1;
    }

}