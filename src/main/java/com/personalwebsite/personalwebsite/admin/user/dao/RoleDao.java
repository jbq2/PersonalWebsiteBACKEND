package com.personalwebsite.personalwebsite.admin.user.dao;

import com.personalwebsite.personalwebsite.admin.user.pojo.Role;
import com.personalwebsite.personalwebsite.dao.DaoInterface;
import org.springframework.jdbc.core.RowMapper;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Collection;

public class RoleDao implements DaoInterface<Role>{

    RowMapper<Role> rowMapper = ((rs, rowNum) -> {
        Role role = new Role();
        role.setId(rs.getLong("id"));
        role.setName(rs.getString("name"));

        return role;
    });

    @Override
    public Collection<Role> findAll() {
        return null;
    }

    @Override
    public Role findById(Long id) {
        return null;
    }

    @Override
    public Role save(Role role) throws SQLIntegrityConstraintViolationException {
        return null;
    }

    @Override
    public Role update(Role role) {
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }
}
