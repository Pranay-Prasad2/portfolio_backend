package com.example.portfolio.services;

import com.example.portfolio.entity.Project;
import com.example.portfolio.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public List<Project> getALL(){
        return projectRepository.findAll();
    }

    public void saveProject(Project project){
        projectRepository.save(project);
    }

}
