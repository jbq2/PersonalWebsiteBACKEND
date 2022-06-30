package com.personalwebsite.personalwebsite.admin.user.service;


import com.personalwebsite.personalwebsite.admin.user.dao.UserHasRoleDao;
import com.personalwebsite.personalwebsite.admin.user.pojo.UserHasRole;
import com.personalwebsite.personalwebsite.service.ServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Collection;

@Service
@Transactional
@RequiredArgsConstructor
public class UserHasRoleService {

    private final UserHasRoleDao userHasRoleDao;

    public UserHasRole save(UserHasRole userHasRole) throws SQLIntegrityConstraintViolationException {
        return userHasRoleDao.save(userHasRole);
    }

    public UserHasRole getById(Long id) {
        return userHasRoleDao.findById(id);
    }

    public Collection<UserHasRole> getByUserId(Long user_id){
        return userHasRoleDao.findByUserId(user_id);
    }

    public Collection<UserHasRole> getByRoleId(Long role_id){
        return userHasRoleDao.findByRoleId(role_id);
    }

    public UserHasRole update(UserHasRole userHasRole) throws SQLIntegrityConstraintViolationException {
        return userHasRoleDao.update(userHasRole);
    }

    public Boolean delete(Long id) {
        return userHasRoleDao.delete(id);
    }
}
