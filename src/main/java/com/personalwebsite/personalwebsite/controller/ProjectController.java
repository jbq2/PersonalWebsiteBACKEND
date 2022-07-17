package com.personalwebsite.personalwebsite.controller;

import com.personalwebsite.personalwebsite.pojo.Project;
import com.personalwebsite.personalwebsite.pojo.CustomResponse;
import com.personalwebsite.personalwebsite.service.ServiceInterface;
import com.personalwebsite.personalwebsite.service.implementations.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static java.time.LocalDateTime.now;

@RestController
@CrossOrigin(origins = {"http://localhost:4200", "http://jbq2-portfolio.herokuapp.com"})
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping("/list")
    public ResponseEntity<CustomResponse> listProjects(){
        Collection<Project> projectList = projectService.list();

        return ResponseEntity.ok(CustomResponse.builder()
                .timeStamp(now())
                .data(Map.of("projects", projectList))
                .message("Projects fetched.")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build()
        );
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<CustomResponse> getProject(@PathVariable("id") Long id){
        Project project = projectService.get(id);

        if(project != null){
            return ResponseEntity.ok(CustomResponse.builder()
                    .timeStamp(now())
                    .data(Map.of("project", project))
                    .message("Project fetched.")
                    .status(HttpStatus.OK)
                    .statusCode(HttpStatus.OK.value())
                    .build()
            );
        }

        return ResponseEntity.ok(CustomResponse.builder()
                .timeStamp(now())
                .message("Project with id " + id.toString() + " does not exist.")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build()
        );
    }


    @PostMapping("/save")
    public ResponseEntity<CustomResponse> saveProject(@RequestBody Project project) throws SQLIntegrityConstraintViolationException {
        Project savedProject = projectService.save(project);

        if(savedProject != null){
            return ResponseEntity.ok(CustomResponse.builder()
                    .timeStamp(now())
                    .data(Map.of("saved", savedProject))
                    .message("Project saved.")
                    .status(HttpStatus.CREATED)
                    .statusCode(HttpStatus.CREATED.value())
                    .build()
            );
        }
        else{
            return ResponseEntity.ok(CustomResponse.builder()
                    .timeStamp(now())
                    .message("No project saved, likely due to integrity constraints.")
                    .developerMessage("Check for primary or foreign key integrity constraint violation, or SQL syntax errors.")
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .statusCode(HttpStatus.EXPECTATION_FAILED.value())
                    .build()
            );
        }
    }

    @PostMapping("/update")
    public ResponseEntity<CustomResponse> updateProject(@RequestBody Project project) throws SQLIntegrityConstraintViolationException {
        Project updatedProject = projectService.update(project);

        if(updatedProject != null){
            return ResponseEntity.ok(CustomResponse.builder()
                    .timeStamp(now())
                    .data(Map.of("updated", updatedProject))
                    .message("Project updated.")
                    .status(HttpStatus.OK)
                    .statusCode(HttpStatus.OK.value())
                    .build()
            );
        }
        else{
            return ResponseEntity.ok(CustomResponse.builder()
                    .timeStamp(now())
                    .message("Project not updated, likely due to integrity constraints.")
                    .developerMessage("Check for primary or foreign key integrity constraint violation, or SQL syntax errors.")
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .statusCode(HttpStatus.EXPECTATION_FAILED.value())
                    .build()
            );
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CustomResponse> deleteProject(@PathVariable("id") Long id){
        Boolean isDeleted = projectService.delete(id);

        if(isDeleted){
            return ResponseEntity.ok(CustomResponse.builder()
                    .timeStamp(now())
                    .data(Map.of("isDeleted", true))
                    .message("Project deleted.")
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
