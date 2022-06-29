package com.personalwebsite.personalwebsite.admin.user.dao;

import com.personalwebsite.personalwebsite.admin.user.pojo.User;
import com.personalwebsite.personalwebsite.dao.DaoInterface;
import org.springframework.jdbc.core.RowMapper;

import java.util.Collection;

public class UserDao implements DaoInterface<User> {

    RowMapper<User> rowMapper = ((rs, rowNum) -> {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        //TODO need to write RoleDao to get collection of roles

        return user;
    });

    @Override
    public Collection<User> findAll() {
        return null;
    }

    @Override
    public User findById(Long id){
        return null;
    }

    public User findByUsername(String username){
        return null;
    }

    @Override
    public User save(User user){
        return null;
    }

    @Override
    public User update(User user){
        return null;
    }

    @Override
    public Boolean delete(Long id){
        return false;
    }
}
