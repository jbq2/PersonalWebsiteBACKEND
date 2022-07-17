package com.personalwebsite.personalwebsite.controller;

import com.personalwebsite.personalwebsite.pojo.Course;
import com.personalwebsite.personalwebsite.pojo.CustomResponse;
import com.personalwebsite.personalwebsite.service.implementations.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Collection;
import java.util.Map;

import static java.time.LocalDateTime.now;

@RestController
@CrossOrigin(origins = {"http://localhost:4200", "http://jbq2-portfolio.herokuapp.com"})
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping("/list")
    public ResponseEntity<CustomResponse> listCourses(){
        Collection<Course> courseList = courseService.list();

        return ResponseEntity.ok(CustomResponse.builder()
                .timeStamp(now())
                .data(Map.of("courses", courseList))
                .message("Courses fetched.")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build()
        );
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<CustomResponse> getCourse(@PathVariable("id") Long id){
        Course course = courseService.get(id);

        if(course != null){
            return ResponseEntity.ok(CustomResponse.builder()
                    .timeStamp(now())
                    .data(Map.of("course", course))
                    .message("Course fetched.")
                    .status(HttpStatus.OK)
                    .statusCode(HttpStatus.OK.value())
                    .build()
            );
        }

        return ResponseEntity.ok(CustomResponse.builder()
                .timeStamp(now())
                .message("Course with id " + id.toString() + " does not exist.")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build()
        );
    }

    @PostMapping("/save")
    public ResponseEntity<CustomResponse> saveCourse(@RequestBody Course course) throws SQLIntegrityConstraintViolationException {
        return ResponseEntity.ok(CustomResponse.builder()
                .timeStamp(now())
                .data(Map.of("course", courseService.save(course)))
                .message("Course save.")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build()
        );
    }

    @PostMapping("/update")
    public ResponseEntity<CustomResponse> updateCourse(@RequestBody Course course) throws SQLIntegrityConstraintViolationException {
        return ResponseEntity.ok(CustomResponse.builder()
                .timeStamp(now())
                .data(Map.of("course", courseService.update(course)))
                .message("Course updated.")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build()
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CustomResponse> deleteCourse(@PathVariable("id") Long id){
        Boolean isDeleted = courseService.delete(id);

        if(isDeleted){
            return ResponseEntity.ok(CustomResponse.builder()
                    .timeStamp(now())
                    .data(Map.of("isDeleted", true))
                    .message("Course deleted.")
                    .status(HttpStatus.OK)
                    .statusCode(HttpStatus.OK.value())
                    .build()
            );
        }

        return ResponseEntity.ok(CustomResponse.builder()
                .timeStamp(now())
                .data(Map.of("isDeleted", false))
                .message("Project with id " + id + " does not exist.")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build()
        );
    }
}
