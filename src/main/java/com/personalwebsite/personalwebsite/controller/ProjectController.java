package com.personalwebsite.personalwebsite.controller;

import com.personalwebsite.personalwebsite.pojo.Project;
import com.personalwebsite.personalwebsite.pojo.CustomResponse;
import com.personalwebsite.personalwebsite.service.implementations.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static java.time.LocalDateTime.now;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping("/list")
    public ResponseEntity<CustomResponse> listProjects(){
        List<Project> projectList = projectService.list();
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
    public ResponseEntity<CustomResponse> saveProject(@RequestBody Project project){
        return ResponseEntity.ok(CustomResponse.builder()
                .timeStamp(now())
                .data(Map.of("saved", projectService.save(project)))
                .message("Project saved.")
                .developerMessage("Ignore id field in data section")
                .status(HttpStatus.CREATED)
                .statusCode(HttpStatus.CREATED.value())
                .build()
        );
    }

    @PostMapping("/update")
    public ResponseEntity<CustomResponse> updateProject(@RequestBody Project project){
        return ResponseEntity.ok(CustomResponse.builder()
                .timeStamp(now())
                .data(Map.of("updated", projectService.update(project)))
                .message("Project updated.")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build()
        );
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
