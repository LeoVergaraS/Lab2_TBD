package com.app.voluntariosbe.services;

import com.app.voluntariosbe.models.Task;
import com.app.voluntariosbe.models.Vol_Skill;
import com.app.voluntariosbe.repositories.TaskRepository;
import com.app.voluntariosbe.repositories.Vol_SkillRepository;

import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
public class Vol_SkillService {

    private final Vol_SkillRepository vol_skillRepository;
    Vol_SkillService(Vol_SkillRepository vol_skillRepository){this.vol_skillRepository = vol_skillRepository;}

    @GetMapping("/vol_skills")
    public List<Vol_Skill> getAllVol_Skills(){ return vol_skillRepository.getAllVol_Skill();}

    @GetMapping("/vol_skills/count")
    public String countVol_Skills(){
        int total = vol_skillRepository.countVol_Skill();
        return String.format("tienes %s Vol_habilidad!", total );
    }

    @PostMapping("/vol_skills")
    @ResponseBody
    public Vol_Skill createVol_Skill(@RequestBody Vol_Skill vol_skill){
        Vol_Skill result = vol_skillRepository.createVol_Skill(vol_skill);
        return result;
    }

    @PutMapping("/vol_skills")
    @ResponseBody
    public Vol_Skill updateVol_Skill(@RequestBody Vol_Skill vol_skill){
        Vol_Skill result = vol_skillRepository.updateVol_Skill(vol_skill);
        return result;
    }

    @DeleteMapping("/vol_skills/{id}")
    public void deleteVol_Skill(@PathVariable int id){
        vol_skillRepository.deleteVol_SkillById(id);
    }

    @GetMapping("/vol_skills/{id}")
    public Vol_Skill getVol_SkillById(@PathVariable int id){
        return vol_skillRepository.getVol_SkillById(id);
    }

}
