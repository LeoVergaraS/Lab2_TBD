package com.app.voluntariosbe.services;

import com.app.voluntariosbe.models.Task;
import com.app.voluntariosbe.models.Task_Skill;
import com.app.voluntariosbe.repositories.TaskRepository;
import com.app.voluntariosbe.repositories.Task_SkillRepository;

import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
public class Task_SkillService {

    private final Task_SkillRepository task_skillRepository;
    Task_SkillService(Task_SkillRepository task_skillRepository){this.task_skillRepository = task_skillRepository;}

    @GetMapping("/task_skills")
    public List<Task_Skill> getAllTask_Skills(){ return task_skillRepository.getAllTask_Skill();}

    @GetMapping("/task_skills/count")
    public String countTask_Skills(){
        int total = task_skillRepository.countTask_Skill();
        return String.format("tienes %s Tarea_habilidad!", total );
    }

    @PostMapping("/task_skills")
    @ResponseBody
    public Task_Skill createTask_Skill(@RequestBody Task_Skill task_skill){
        Task_Skill result = task_skillRepository.createTask_Skill(task_skill);
        return result;
    }

    @PutMapping("/task_skills")
    @ResponseBody
    public Task_Skill updateTask_Skill(@RequestBody Task_Skill task_skill){
        Task_Skill result = task_skillRepository.updateTask_Skill(task_skill);
        return result;
    }

    @DeleteMapping("/task_skills/{id}")
    public void deleteTask_Skill(@PathVariable int id){
        task_skillRepository.deleteTask_SkillById(id);
    }

    @GetMapping("/task_skills/{id}")
    public Task_Skill getTask_SkillById(@PathVariable int id){
        return task_skillRepository.getTask_SkillById(id);
    }

}
