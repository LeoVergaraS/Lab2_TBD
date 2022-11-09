package com.app.voluntariosbe.services;

import com.app.voluntariosbe.models.Eme_Skill;
import com.app.voluntariosbe.models.Task;
import com.app.voluntariosbe.repositories.Eme_SkillRepository;

import com.app.voluntariosbe.repositories.TaskRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
public class Eme_SkillService {

    private final Eme_SkillRepository eme_skillRepository;
    Eme_SkillService(Eme_SkillRepository eme_skillRepository){this.eme_skillRepository = eme_skillRepository;}

    @GetMapping("/eme_skills")
    public List<Eme_Skill> getAllEme_Skills(){ return eme_skillRepository.getAllEme_Skill();}

    @GetMapping("/eme_skills/count")
    public String countEme_Skills(){
        int total = eme_skillRepository.countEme_Skill();
        return String.format("tienes %s Eme_habilidad!", total );
    }

    @PostMapping("/eme_skills")
    @ResponseBody
    public Eme_Skill createEme_Skill(@RequestBody Eme_Skill eme_skill){
        Eme_Skill result = eme_skillRepository.createEme_Skill(eme_skill);
        return result;
    }

    @PutMapping("/eme_skills")
    @ResponseBody
    public Eme_Skill updateEme_Skill(@RequestBody Eme_Skill eme_skill){
        Eme_Skill result = eme_skillRepository.updateEme_Skill(eme_skill);
        return result;
    }

    @DeleteMapping("/eme_skills/{id}")
    public void deleteEme_Skill(@PathVariable int id){
        eme_skillRepository.deleteEme_SkillById(id);
    }

    @GetMapping("/eme_skills/{id}")
    public Eme_Skill getEme_SkillById(@PathVariable int id){
        return eme_skillRepository.getEme_SkillById(id);
    }

}
