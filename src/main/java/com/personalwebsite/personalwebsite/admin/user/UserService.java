//package com.personalwebsite.personalwebsite.admin.user;
//
//import com.personalwebsite.personalwebsite.service.ServiceInterface;
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.sql.SQLIntegrityConstraintViolationException;
//import java.util.Collection;
//
//@Service
//@Transactional
//@AllArgsConstructor
//public class UserService implements ServiceInterface<User> {
//
//    private UserDao userDao;
//
//    public User get(String s){
//        return (s.indexOf('@') != -1) ? userDao.findByEmail(s) : userDao.findByUsername(s);
//    }
//
//    @Override
//    public User save(User user) throws SQLIntegrityConstraintViolationException {
//        return userDao.save(user);
//    }
//
//    @Override
//    public Collection<User> list() {
//        return userDao.findAll();
//    }
//
//    @Override
//    public User get(Long id) {
//        return userDao.findById(id);
//    }
//
//    @Override
//    public User update(User user) throws SQLIntegrityConstraintViolationException {
//        return userDao.update(user);
//    }
//
//    @Override
//    public Boolean delete(Long id) {
//        return userDao.delete(id);
//    }
//}
