package com.app.voluntariosbe.services;

import com.app.voluntariosbe.models.Skill;
import com.app.voluntariosbe.repositories.SkillRepository;

import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
public class SkillService {
    private final SkillRepository skillRepository;

    SkillService(SkillRepository skillRepository){this.skillRepository = skillRepository;}

    @GetMapping("/skills")
    public List<Skill> getAllSkills(){ return skillRepository.getAllSkill();}

    @GetMapping("/skills/count")
    public String countSkills(){
        int total = skillRepository.countSkill();
        return String.format("tienes %s Habilidades!", total );
    }

    @PostMapping("/skills")
    @ResponseBody
    public Skill createSkill(@RequestBody Skill skill){
        Skill result = skillRepository.createSkill(skill);
        return result;
    }

    @PutMapping("/skills")
    @ResponseBody
    public Skill updateSkill(@RequestBody Skill skill){
        Skill result = skillRepository.updateSkill(skill);
        return result;
    }

    @DeleteMapping("/skills/{id}")
    public void deleteSkill(@PathVariable int id){
        skillRepository.deleteSkillById(id);
    }

    @GetMapping("/skills/{id}")
    public Skill getSkillById(@PathVariable int id){
        return skillRepository.getSkillById(id);
    }

    @GetMapping("/skills/emergencies/{id}")
    public List<Skill> getSkillByEmergency(@PathVariable int id){
        return skillRepository.getSkillByEmergency(id);
    }

}
