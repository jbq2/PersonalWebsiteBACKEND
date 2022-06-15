package com.personalwebsite.personalwebsite.service;

import com.personalwebsite.personalwebsite.pojo.Project;

import java.util.Collection;

public interface ServiceInterface<T> {
    T save(T t);

    Collection<T> list();

    T get(Long id);

    T update(T t);

    Boolean delete(Long id);

}
