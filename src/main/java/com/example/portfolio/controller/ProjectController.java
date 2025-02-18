package com.example.portfolio.controller;

import com.example.portfolio.entity.Project;
import com.example.portfolio.services.ProjectService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping
    public ResponseEntity<?> getAllProjects(){
        try{
            List<Project> projects = projectService.getAllProject();
            return new ResponseEntity<>(projects,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e,HttpStatus.NO_CONTENT);
        }

    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getProjectByID(@PathVariable ObjectId id){
        try{
            Optional<Project> project = projectService.getProjectById(id);
            if(project.isPresent()){
                return new ResponseEntity<>(project.get(), HttpStatus.OK);
            }
            return new ResponseEntity<>("NOT FOUND",HttpStatus.NO_CONTENT);

        } catch (Exception e) {
            return new ResponseEntity<>(e,HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<?> addProject(@RequestBody Project project){
        try{
            projectService.saveProject(project);
            return new ResponseEntity<>(project,HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e,HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<?> updateProject(@PathVariable ObjectId id,@RequestBody Project newProject){
        try{
            Optional<Project> project = projectService.getProjectById(id);
            if(project.isPresent()){
                Project oldProject = project.get();
                oldProject.setTitle(newProject.getTitle() != null && !newProject.getTitle().trim().isEmpty()?newProject.getTitle():oldProject.getTitle());
                oldProject.setCategory(newProject.getCategory() != null && !newProject.getCategory().trim().isEmpty()?newProject.getCategory():oldProject.getTitle());
                oldProject.setDescription(newProject.getDescription() != null && !newProject.getDescription().trim().isEmpty()?newProject.getDescription():oldProject.getDescription());
                oldProject.setTechStack(newProject.getTechStack() != null && !newProject.getTechStack().isEmpty()?newProject.getTechStack():oldProject.getTechStack());
                projectService.saveProject(oldProject);
                return new ResponseEntity<>(oldProject,HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> deleteByID(@PathVariable ObjectId id){
        try{
            projectService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



}
