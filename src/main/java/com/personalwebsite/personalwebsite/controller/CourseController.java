package com.personalwebsite.personalwebsite.controller;

import com.personalwebsite.personalwebsite.pojo.Course;
import com.personalwebsite.personalwebsite.pojo.CustomResponse;
import com.personalwebsite.personalwebsite.service.implementations.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import static java.time.LocalDateTime.now;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping("/list")
    public ResponseEntity<CustomResponse> listCourses(){
        List<Course> courseList = courseService.list();

        return ResponseEntity.ok(CustomResponse.builder()
                .timeStamp(now())
                .data(Map.of("courses", courseList))
                .message("Courses fetched.")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build()
        );
    }

    public ResponseEntity<CustomResponse> getCourse(@PathVariable("id") Long id){
        Course course = courseService.get(id);

        return ResponseEntity.ok(CustomResponse.builder()
                .timeStamp(now())
                .data(Map.of("course", course))
                .message("Course fetched.")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build()
        );
    }
}
