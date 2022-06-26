package com.personalwebsite.personalwebsite.service;

import com.personalwebsite.personalwebsite.pojo.Project;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Collection;

public interface ServiceInterface<T> {
    T save(T t) throws SQLIntegrityConstraintViolationException;

    Collection<T> list();

    T get(Long id);

    T update(T t) throws SQLIntegrityConstraintViolationException;

    Boolean delete(Long id);

}
