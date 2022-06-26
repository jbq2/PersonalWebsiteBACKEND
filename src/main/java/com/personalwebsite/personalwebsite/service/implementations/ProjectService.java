package com.personalwebsite.personalwebsite.service.implementations;

import com.personalwebsite.personalwebsite.dao.ProjectDao;
import com.personalwebsite.personalwebsite.pojo.Project;
import com.personalwebsite.personalwebsite.service.ServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProjectService implements ServiceInterface<Project> {

    private final ProjectDao projectDao;

    @Override
    public Project save(Project project) {
        return projectDao.save(project);
    }

    @Override
    public List<Project> list() {
        return projectDao.findAll();
    }

    @Override
    public Project get(Long id) {
        return projectDao.findById(id);
    }

    @Override
    public Project update(Project project) {
        return projectDao.update(project);
    }

    @Override
    public Boolean delete(Long id) {
        return projectDao.delete(id);
    }
}
