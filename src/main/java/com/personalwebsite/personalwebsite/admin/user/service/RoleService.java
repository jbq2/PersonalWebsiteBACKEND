package com.personalwebsite.personalwebsite.admin.user.service;

import com.personalwebsite.personalwebsite.admin.user.dao.RoleDao;
import com.personalwebsite.personalwebsite.admin.user.pojo.Role;
import com.personalwebsite.personalwebsite.service.ServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Collection;

@Service
@Transactional
@RequiredArgsConstructor
public class RoleService implements ServiceInterface<Role> {

    private final RoleDao roleDao;

    @Override
    public Role save(Role role) throws SQLIntegrityConstraintViolationException {
        return roleDao.save(role);
    }

    @Override
    public Collection<Role> list() {
        return roleDao.findAll();
    }

    @Override
    public Role get(Long id) {
        return roleDao.findById(id);
    }

    @Override
    public Role update(Role role) throws SQLIntegrityConstraintViolationException {
        return roleDao.update(role);
    }

    @Override
    public Boolean delete(Long id) {
        return roleDao.delete(id);
    }
}
