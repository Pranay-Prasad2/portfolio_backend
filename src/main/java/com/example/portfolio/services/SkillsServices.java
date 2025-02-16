package com.example.portfolio.services;

import com.example.portfolio.entity.Skills;
import com.example.portfolio.repository.SkillsRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SkillsServices {

    @Autowired
    private SkillsRepository skillsRepository;

    public List<Skills> getAllSkills(){
        return skillsRepository.findAll();
    }

    public void addSkill(Skills skill){
        skillsRepository.save(skill);
    }

    public Optional<Skills> getById(ObjectId myid){
        return skillsRepository.findById(myid);
    }

    public void deleteByID(ObjectId id){
        try{
            skillsRepository.deleteById(id);
        }catch (Exception e){
            throw new RuntimeException("Error while deleting SKill with id: "+id+" "+e);
        }
    }



}
