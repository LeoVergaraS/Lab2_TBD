package com.app.voluntariosbe.services;

import com.app.voluntariosbe.models.Task;
import com.app.voluntariosbe.repositories.TaskRepository;

import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
public class TaskService {

    private final TaskRepository taskRepository;
    TaskService(TaskRepository taskRepository){this.taskRepository = taskRepository;}

    @GetMapping("/tasks")
    public List<Task> getAllTasks(){ return taskRepository.getAllTask();}

    @GetMapping("/tasks/count")
    public String countTasks(){
        int total = taskRepository.countTask();
        return String.format("tienes %s Tareas!", total );
    }

    @PostMapping("/tasks")
    @ResponseBody
    public Task createTask(@RequestBody Task task){
        Task result = taskRepository.createTask(task);
        return result;
    }

    @PutMapping("/tasks")
    @ResponseBody
    public Task updateTask(@RequestBody Task task){
        Task result = taskRepository.updateTask(task);
        return result;
    }

    @DeleteMapping("/tasks/{id}")
    public void deleteTask(@PathVariable int id){
        taskRepository.deleteTaskById(id);
    }

    @GetMapping("/tasks/{id}")
    public Task getTaskById(@PathVariable int id){
        return taskRepository.getTaskById(id);
    }

}
