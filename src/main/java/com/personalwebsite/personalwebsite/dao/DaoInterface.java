package com.personalwebsite.personalwebsite.dao;

import java.util.Collection;

public interface DaoInterface <E>{
    Collection<E> findAll();

    E findById(Long id);

    E save(E e);

    E update(E e);

    E delete(Long id);
}
