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
        project.setId(rs.getLong("p_id"));
        project.setName(rs.getString("p_name"));
        project.setDescription(rs.getString("p_description"));
        project.setCourse(rs.getString("p_course"));
        project.setStartdate(rs.getDate("p_startdate").toString());
        try{
            project.setEnddate(rs.getDate("p_enddate").toString());
        }
        catch(NullPointerException e){
            project.setEnddate(null);
        }
        project.setUrl(rs.getString("p_url"));
        return project;
    });

    @Override
    public List<Project> findAll() {
        String sql =
                "SELECT * FROM PROJECTS " +
                "ORDER BY P_ID";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public Project findById(Long id) {
        String sql =
                "SELECT * FROM PROJECTS " +
                "WHERE p_id = ?";
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
                "INSERT INTO PROJECTS (p_id, p_name, p_description, p_course, p_url, p_startdate, p_enddate) " +
                "VALUES (projects_seq.nextval, ?, ?, ?, ?, ?, ?)";
        Date startDate = Date.valueOf(project.getStartdate());
        Date endDate = null;

        if(project.getEnddate() != null){
            endDate = Date.valueOf(project.getEnddate());
        }

        jdbcTemplate.update(sql,
                project.getName(), // p_name
                project.getDescription(), // p_description
                project.getCourse(), // p_course
                project.getUrl(), // p_url
                startDate, // p_startdate (YYYY-MM-DD)
                endDate // p_enddate (YYYY-MM-DD)
        );

        sql =
            "SELECT * FROM PROJECTS " +
            "ORDER BY P_ID DESC " +
            "FETCH NEXT 1 ROWS ONLY";

        return jdbcTemplate.queryForObject(sql, rowMapper);
    }

    @Override
    public Project update(Project project) {
        String sql =
                "UPDATE PROJECTS " +
                "SET p_name = ?, p_description = ?, p_course = ?, p_url = ?, p_startdate = ?, p_enddate = ? " +
                "WHERE p_id = ?";
        Date startDate = Date.valueOf(project.getStartdate());
        Date endDate = null;

        if(project.getEnddate() != null){
            endDate = Date.valueOf(project.getEnddate());
        }

        jdbcTemplate.update(sql,
                project.getName(), // p_name
                project.getDescription(), // p_description
                project.getCourse(), // p_course
                project.getUrl(), // p_url
                startDate, // p_startdate
                endDate, // p_enddate
                project.getId() // p_id
        );

        return project;
    }

    @Override
    public Project delete(Long id) {
        Project project = findById(id);
        String sql =
                "DELETE FROM PROJECTS " +
                "WHERE p_id = ?";
        jdbcTemplate.update(sql, id);
        return project;
    }

    public Project deleteByName(String name) {
        Project project = findByName(name);
        String sql =
                "DELETE FROM PROJECTS " +
                "WHERE p_name = ?";
        jdbcTemplate.update(sql, name);
        return project;
    }

    public Project findByName(String name) {
        String sql =
                "SELECT * FROM PROJECTS " +
                "WHERE p_name = ?";
        try{
            return jdbcTemplate.queryForObject(sql, rowMapper, name);
        }
        catch(EmptyResultDataAccessException e){
            return null;
        }
    }
}
