package com.personalwebsite.personalwebsite.admin.user.service;


import com.personalwebsite.personalwebsite.admin.user.dao.UserDao;
import com.personalwebsite.personalwebsite.admin.user.pojo.Role;
import com.personalwebsite.personalwebsite.admin.user.pojo.User;
import com.personalwebsite.personalwebsite.service.ServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Collection;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements ServiceInterface<User>, UserDetailsService {

    private final UserDao userDao;

    @Override
    public User save(User user) throws SQLIntegrityConstraintViolationException {
        return userDao.save(user);
    }

    @Override
    public Collection<User> list() {
        return userDao.findAll();
    }

    public User get(String username) { return userDao.findByUsername(username); }

    @Override
    public User get(Long id) {
        return userDao.findById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =  userDao.findByUsername(username);

        if(user == null){
            System.out.println("User was not found in the database");
            throw new UsernameNotFoundException("User not found.");
        }
        else{
            System.out.println("User exists in the database");
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for(Role r : user.getRoles()){
            authorities.add(new SimpleGrantedAuthority(r.getName()));
        }

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                authorities
        );
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
