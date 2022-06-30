package com.personalwebsite.personalwebsite.admin.user.service;


import com.personalwebsite.personalwebsite.admin.user.dao.UserDao;
import com.personalwebsite.personalwebsite.admin.user.pojo.User;
import com.personalwebsite.personalwebsite.service.ServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Collection;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements ServiceInterface<User> {

    private final UserDao userDao;

    @Override
    public User save(User user) throws SQLIntegrityConstraintViolationException {
        return userDao.save(user);
    }

    @Override
    public Collection<User> list() {
        return userDao.findAll();
    }

    @Override
    public User get(Long id) {
        return userDao.findById(id);
    }

    public User get(String username){
        return userDao.findByUsername(username);
    }

    @Override
    public User update(User user) throws SQLIntegrityConstraintViolationException {
        return userDao.update(user);
    }

    @Override
    public Boolean delete(Long id) {
        return userDao.delete(id);
    }
}
