package com.personalwebsite.personalwebsite.admin.user.dao;

import com.personalwebsite.personalwebsite.admin.user.pojo.Role;
import com.personalwebsite.personalwebsite.dao.DaoInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Collection;

@Component
public class RoleDao implements DaoInterface<Role>{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    RowMapper<Role> rowMapper = ((rs, rowNum) -> {
        Role role = new Role();
        role.setId(rs.getLong("id"));
        role.setName(rs.getString("name"));

        return role;
    });

    @Override
    public Collection<Role> findAll() {
        String sql =
                "SELECT * FROM localdb.roles";

        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public Role findById(Long id) {
        String sql =
                "SELECT * FROM localdb.roles " +
                "WHERE id = ?";

        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public Role save(Role role) throws SQLIntegrityConstraintViolationException {
        String sql =
                "INSERT INTO localdb.roles (name) " +
                "VALUES (?)";

        try{
            jdbcTemplate.update(sql, role.getName());

            sql =
                    "SELECT * FROM localdb.roles " +
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
    public Role update(Role role) {
        String sql =
                "UPDATE localdb.roles " +
                "SET name = ? " +
                "WHERE id = ?";

        try{
            jdbcTemplate.update(sql,
                    role.getName(),
                    role.getId()
            );

            return role;
        }
        catch(DataAccessException e){
            System.out.println(e.toString());
            return null;
        }
    }

    @Override
    public Boolean delete(Long id) {
        String sql =
                "DELETE FROM localdb.roles " +
                "WHERE id = ?";

        int affectedRows = jdbcTemplate.update(sql, id);

        return affectedRows == 1;
    }
}
