package com.personalwebsite.personalwebsite.admin.user.dao;

import com.personalwebsite.personalwebsite.admin.user.pojo.User;
import com.personalwebsite.personalwebsite.admin.user.pojo.UserHasRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class UserHasRoleDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    RowMapper<UserHasRole> rowMapper = ((rs, rowNum) -> {
        UserHasRole userHasRole = new UserHasRole();
        userHasRole.setId(rs.getLong("id"));
        userHasRole.setUser_id(rs.getLong("user_id"));
        userHasRole.setRole_id(rs.getLong("role_id"));

        return userHasRole;
    });

    public UserHasRole findById(Long id){
        String sql =
                "SELECT * FROM localdb.userhasrole " +
                "WHERE id = ?";

        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    public Collection<UserHasRole> findByUserId(Long user_id){
        String sql =
                "SELECT * FROM localdb.userhasrole " +
                "WHERE user_id = ?";

        return jdbcTemplate.query(sql, rowMapper);
    }

    public Collection<UserHasRole> findByRoleId(Long role_id){
        String sql =
                "SELECT * FROM localdb.userhasrole " +
                "WHERE role_id = ?";

        return jdbcTemplate.query(sql, rowMapper);
    }

    public UserHasRole save(UserHasRole userHasRole){
        String sql = "INSERT INTO localdb.userhasrole (user_id, role_id) " +
                "VALUES (?, ?)";

        try{
            jdbcTemplate.update(sql,
                    userHasRole.getUser_id(),
                    userHasRole.getRole_id()
            );

            sql =
                    "SELECT * FROM localdb.userhasrole " +
                    "ORDER BY id DESC " +
                    "LIMIT 1";

            return jdbcTemplate.queryForObject(sql, rowMapper);
        }
        catch(DataAccessException e){
            System.out.println(e.toString());
            return null;
        }
    }

    public UserHasRole update(UserHasRole userHasRole){
        String sql =
                "UPDATE localdb.userhasrole " +
                "SET user_id = ?, role_id = ? " +
                "WHERE id = ?";

        try{
            jdbcTemplate.update(sql,
                    userHasRole.getUser_id(),
                    userHasRole.getRole_id(),
                    userHasRole.getId()
            );

            return userHasRole;
        }
        catch(DataAccessException e){
            System.out.println(e.toString());
            return null;
        }
    }

    public Boolean delete(Long id){
        String sql =
                "DELETE FROM localdb.userhasrole " +
                "WHERE id = ?";

        int affectedRows = jdbcTemplate.update(sql, id);

        return affectedRows == 1;
    }
}
