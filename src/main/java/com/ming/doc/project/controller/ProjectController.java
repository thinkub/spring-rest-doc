package com.ming.doc.project.controller;

import com.ming.doc.project.model.Project;
import com.ming.doc.project.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author by Ming(thinkub0219@gmail.com) on 2020. 9. 22..
 */

@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @GetMapping("/{projectId}")
    public ResponseEntity<Project> findProject(@PathVariable Long projectId) {
        Project project = projectService.findProjectByProjectId(projectId);
        return ResponseEntity.ok().body(project);
    }

    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody Project.Create create) {
        Project project = projectService.createProject(create);
        return ResponseEntity.ok().body(project);
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity deleteProject(@PathVariable Long projectId) {
        projectService.deleteProject(projectId);
        return ResponseEntity.ok().build();
    }

}
