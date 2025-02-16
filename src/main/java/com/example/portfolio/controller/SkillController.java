package com.example.portfolio.controller;

import com.example.portfolio.entity.Skills;
import com.example.portfolio.services.SkillsServices;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.HTML;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/skills")
public class SkillController {

    private List<Skills> allSkills = new ArrayList<>();

    @Autowired
    private SkillsServices skillsServices;

    @GetMapping
    public ResponseEntity<?> getAllSkills(){
        try {
            allSkills = skillsServices.getAllSkills();
            if (!allSkills.isEmpty()) {
                return new ResponseEntity<>(allSkills, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<?> addSkill(@RequestBody Skills skill){
        try {
            if(skill.getTitle() == null || skill.getTitle().trim().isEmpty() ||
                    skill.getLevel() == null || skill.getLevel().trim().isEmpty() ||
                    skill.getCategory() == null || skill.getCategory().trim().isEmpty()){
                return new ResponseEntity<>("EMPTY FIElDS NOT ACCEPTED",HttpStatus.BAD_REQUEST);
            }
            skillsServices.addSkill(skill);
            return new ResponseEntity<>(skill,HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/id/{myid}")
    public ResponseEntity<Skills> getSkillByID(@PathVariable ObjectId myid){

        try{
            Optional<Skills> skill = skillsServices.getById(myid);
            return skill.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/id/{myid}")
    public ResponseEntity<?> updateSkill(
            @PathVariable ObjectId myid,
            @RequestBody Skills newSkill
    ){
        Optional<Skills> old = skillsServices.getById(myid);
        if (old.isPresent()) {
            Skills oldSkill = old.get();
            oldSkill.setTitle(newSkill.getTitle() != null && !newSkill.getTitle().trim().isEmpty() ? newSkill.getTitle() : oldSkill.getTitle());
            oldSkill.setCategory(newSkill.getCategory() != null && !newSkill.getCategory().trim().isEmpty() ? newSkill.getCategory() : oldSkill.getCategory());
            oldSkill.setLevel(newSkill.getLevel() != null && !newSkill.getLevel().trim().isEmpty() ? newSkill.getLevel() : oldSkill.getLevel());
            skillsServices.addSkill(oldSkill);
            return new ResponseEntity<>(newSkill,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/id/{myid}")
    public ResponseEntity<?> deleteSkillByID(@PathVariable ObjectId myid){
        try{
            skillsServices.deleteByID(myid);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e,HttpStatus.NOT_FOUND);
        }
    }


}
