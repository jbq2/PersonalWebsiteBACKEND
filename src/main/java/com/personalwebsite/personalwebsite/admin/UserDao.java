package com.personalwebsite.personalwebsite.admin;

import com.personalwebsite.personalwebsite.dao.DaoInterface;
import com.personalwebsite.personalwebsite.pojo.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Collection;
import java.util.List;

@Component
public class UserDao implements DaoInterface<User> {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    RowMapper<User> rowMapper = ((rs, rowNum) -> {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setUsername(rs.getString("username"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setRole(UserRole.valueOf(rs.getString("role")));
        user.setLocked(rs.getBoolean("locked"));
        user.setEnabled(rs.getBoolean("enabled"));
        return user;
    });

    @Override
    public List<User> findAll() {
        String sql =
                "SELECT * FROM localdb.users " +
                "ORDER BY id";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public User findById(Long id) {
        String sql =
                "SELECT * FROM localdb.users " +
                "WHERE id = ?";

        try{
            return jdbcTemplate.queryForObject(sql, rowMapper, id);
        }
        catch(EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public User save(User user) throws SQLIntegrityConstraintViolationException {
        String sql =
                "INSERT INTO localdb.users (username, email, password, role, locked, enabled) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql,
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getRole().name(),
                user.getLocked(),
                user.getEnabled()
        );

        sql =
                "SELECT * FROM localdb.users " +
                "ORDER BY id DESC " +
                "LIMIT 1";

        return jdbcTemplate.queryForObject(sql, rowMapper);
    }

    @Override
    public User update(User user) {
        String sql = "UPDATE localdb.users " +
                "SET username = ?, email = ?, password = ?, role = ?, locked = ?, enabled = ? " +
                "WHERE id = ?";

        jdbcTemplate.update(sql,
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getRole(),
                user.getLocked(),
                user.getEnabled(),
                user.getId()
        );

        return user;
    }

    @Override
    public Boolean delete(Long id) {
        String sql = "DELETE FROM localdb.users " +
                "WHERE id = ?";
        int affectedRows = jdbcTemplate.update(sql, id);

        return affectedRows == 1;
    }
}
