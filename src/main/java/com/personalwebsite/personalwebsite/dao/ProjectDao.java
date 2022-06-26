package com.personalwebsite.personalwebsite.dao;

import com.personalwebsite.personalwebsite.pojo.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
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
        long course_id = rs.getLong("course_id");
        if(course_id != 0){
            project.setCourse_id(course_id);
        }
        else{
            project.setCourse_id(null);
        }
        project.setStartdate(rs.getDate("startdate").toString());
        try{
            project.setEnddate(rs.getDate("enddate").toString());
        }
        catch(NullPointerException e){
            project.setEnddate(null);
        }
        project.setUrl(rs.getString("url"));
        project.setServedurl(rs.getString("servedurl"));
        return project;
    });

    @Override
    public List<Project> findAll() {
        String sql =
                "SELECT * FROM localdb.projects " +
                "ORDER BY startdate DESC";
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
                "INSERT INTO localdb.projects (name, description, course_id, startdate, enddate, url, servedurl) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        Date startDate = Date.valueOf(project.getStartdate());
        Date endDate = null;

        if(project.getEnddate() != null){
            endDate = Date.valueOf(project.getEnddate());
        }

        try{
            jdbcTemplate.update(sql,
                    project.getName(),
                    project.getDescription(),
                    project.getCourse_id(),
                    startDate,
                    endDate,
                    project.getUrl(),
                    project.getServedurl()
            );

            sql =
                    "SELECT * FROM localdb.projects " +
                            "ORDER BY id DESC " +
                            "LIMIT 1";

            return jdbcTemplate.queryForObject(sql, rowMapper);
        }
        catch(DataAccessException e){
            System.out.println(e.toString());
            return null;
        }
    }

    @Override
    public Project update(Project project){
        String sql =
                "UPDATE localdb.projects " +
                "SET name = ?, description = ?, course_id = ?, url = ?, startdate = ?, enddate = ?, servedurl = ? " +
                "WHERE id = ?";
        Date startDate = Date.valueOf(project.getStartdate());
        Date endDate = null;

        if(project.getEnddate() != null){
            endDate = Date.valueOf(project.getEnddate());
        }

        try{
            jdbcTemplate.update(sql,
                    project.getName(),
                    project.getDescription(),
                    project.getCourse_id(),
                    project.getUrl(),
                    startDate,
                    endDate,
                    project.getServedurl(),
                    project.getId()
            );

            return project;
        }
        catch(DataAccessException e){
            System.out.println(e.toString());
            return null;
        }

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
