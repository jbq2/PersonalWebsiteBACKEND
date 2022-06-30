package com.personalwebsite.personalwebsite.admin.user.dao;

import com.personalwebsite.personalwebsite.admin.user.pojo.Role;
import com.personalwebsite.personalwebsite.admin.user.pojo.User;
import com.personalwebsite.personalwebsite.admin.user.pojo.UserHasRole;
import com.personalwebsite.personalwebsite.dao.DaoInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class UserDao implements DaoInterface<User> {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    RowMapper<User> rowMapper = ((rs, rowNum) -> {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        //TODO need to write RoleDao to get collection of roles

        UserHasRoleDao userHasRoleDao = new UserHasRoleDao();
        Collection<UserHasRole> userroles = userHasRoleDao.findByUserId(user.getId());

        Collection<Role> roles = new ArrayList<>();
        RoleDao roleDao = new RoleDao();

        for(UserHasRole row : userroles){
            roles.add(roleDao.findById(row.getRole_id()));
        }

        user.setRoles(roles);

        return user;
    });

    @Override
    public Collection<User> findAll() {
        String sql =
                "SELECT * FROM localdb.users";

        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public User findById(Long id){
        String sql =
                "SELECT * FROM localdb.users " +
                "WHERE id = ?";

        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    public User findByUsername(String username){
        String sql =
                "SELECT * FROM localdb.users " +
                "WHERE username = ?";

        return jdbcTemplate.queryForObject(sql, rowMapper, username);
    }

    @Override
    public User save(User user){
        String sql =
                "INSERT INTO localdb.users (username, password) " +
                "VALUES (?, ?)";

        try{
            jdbcTemplate.update(sql,
                    user.getUsername(),
                    user.getPassword()
            );

            sql =
                    "SELECT * FROM localdb.users " +
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
    public User update(User user){
        String sql =
                "UPDATE localdb.users " +
                "SET username = ?, password = ?";

        try{
            jdbcTemplate.update(sql,
                    user.getUsername(),
                    user.getPassword()
            );

            return user;
        }
        catch(DataAccessException e){
            System.out.println(e.toString());
            return null;
        }
    }

    @Override
    public Boolean delete(Long id){
        String sql =
                "DELETE FROM localdb.users " +
                "WHERE id = ?";

        int affectedRows = jdbcTemplate.update(sql, id);

        return affectedRows == 1;
    }
}
