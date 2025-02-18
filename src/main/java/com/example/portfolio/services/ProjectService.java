package com.example.portfolio.services;

import com.example.portfolio.entity.Project;
import com.example.portfolio.repository.ProjectRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public List<Project> getAllProject(){
        return projectRepository.findAll();
    }

    public Optional<Project> getProjectById(ObjectId id){
        return projectRepository.findById(id);
    }

    public void deleteById(ObjectId id){
        projectRepository.deleteById(id);
    }

}
