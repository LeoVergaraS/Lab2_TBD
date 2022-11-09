package com.app.voluntariosbe.services;

import com.app.voluntariosbe.models.Status_Task;
import com.app.voluntariosbe.models.Task;
import com.app.voluntariosbe.repositories.Status_TaskRepository;

import com.app.voluntariosbe.repositories.TaskRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
public class Status_TaskService {

    private final Status_TaskRepository status_taskRepository;
    Status_TaskService(Status_TaskRepository status_taskRepository){this.status_taskRepository = status_taskRepository;}

    @GetMapping("/status_tasks")
    public List<Status_Task> getAllStatus_Tasks(){ return status_taskRepository.getAllStatus_Task();}

    @GetMapping("/status_tasks/count")
    public String countStatus_Tasks(){
        int total = status_taskRepository.countStatus_Task();
        return String.format("tienes %s Estado_tareas!", total );
    }

    @PostMapping("/status_tasks")
    @ResponseBody
    public Status_Task createStatus_Task(@RequestBody Status_Task status_task){
        Status_Task result = status_taskRepository.createStatus_Task(status_task);
        return result;
    }

    @PutMapping("/status_tasks")
    @ResponseBody
    public Status_Task updateStatus_Task(@RequestBody Status_Task status_task){
        Status_Task result = status_taskRepository.updateStatus_Task(status_task);
        return result;
    }

    @DeleteMapping("/status_tasks/{id}")
    public void deleteStatus_Task(@PathVariable int id){
        status_taskRepository.deleteStatus_TaskById(id);
    }

    @GetMapping("/status_tasks/{id}")
    public Status_Task getStatus_TaskById(@PathVariable int id){
        return status_taskRepository.getStatus_TaskById(id);
    }

}
