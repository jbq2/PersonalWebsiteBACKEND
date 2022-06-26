package com.personalwebsite.personalwebsite.service.implementations;

import com.personalwebsite.personalwebsite.dao.CourseDao;
import com.personalwebsite.personalwebsite.pojo.Course;
import com.personalwebsite.personalwebsite.service.ServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CourseService implements ServiceInterface<Course> {

    private final CourseDao courseDao;

    @Override
    public Course save(Course course) {
        return courseDao.save(course);
    }

    @Override
    public List<Course> list() {
        return courseDao.findAll();
    }

    @Override
    public Course get(Long id) {
        return courseDao.findById(id);
    }

    @Override
    public Course update(Course course) {
        return courseDao.update(course);
    }

    @Override
    public Boolean delete(Long id) {
        return courseDao.delete(id);
    }
}
