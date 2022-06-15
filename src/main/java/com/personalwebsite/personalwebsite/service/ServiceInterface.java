package com.personalwebsite.personalwebsite.service;

import com.personalwebsite.personalwebsite.pojo.Project;

import java.util.Collection;

public interface ServiceInterface<T> {
    Project save(T t);

    Collection<Project> list();

    Project get(Long id);

    Project update(T t);

    Project delete(Long id);

}
