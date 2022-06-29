package com.personalwebsite.personalwebsite.admin.user.dao;

import com.personalwebsite.personalwebsite.admin.user.pojo.User;
import com.personalwebsite.personalwebsite.admin.user.pojo.UserHasRole;
import org.springframework.jdbc.core.RowMapper;

public class UserHasRoleDao {

    RowMapper<UserHasRole> rowMapper = ((rs, rowNum) -> {
        UserHasRole userHasRole = new UserHasRole();
        userHasRole.setId(rs.getLong("id"));
        userHasRole.setUser_id(rs.getLong("user_id"));
        userHasRole.setRole_id(rs.getLong("role_id"));

        return userHasRole;
    });

    public UserHasRole findById(Long id){
        return null;
    }

    public UserHasRole findByUserId(Long user_id){
        return null;
    }

    public UserHasRole findByRoleId(Long role_id){
        return null;
    }

    public UserHasRole save(UserHasRole userHasRole){
        return null;
    }

    public UserHasRole update(UserHasRole userHasRole){
        return null;
    }

    public Boolean delete(UserHasRole userHasRole){
        return false;
    }
}
