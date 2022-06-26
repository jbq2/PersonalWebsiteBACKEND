package com.personalwebsite.personalwebsite.dao;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Collection;

public interface DaoInterface <E>{
    Collection<E> findAll();

    E findById(Long id);

    E save(E e) throws SQLIntegrityConstraintViolationException;

    E update(E e);

    Boolean delete(Long id);
}
