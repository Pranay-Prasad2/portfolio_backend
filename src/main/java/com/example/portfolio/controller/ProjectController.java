package com.example.portfolio.controller;

import com.example.portfolio.entity.Project;
import com.example.portfolio.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping
    public List<Project> getAllProjects(){
        return projectService.getALL();
    }

    @PostMapping
    public void addProject(@RequestBody Project project){
        projectService.saveProject(project);
    }


}
