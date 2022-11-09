package com.app.voluntariosbe.repositories;

import com.app.voluntariosbe.models.Task;
import java.util.List;

public interface TaskRepository {
    public int countTask();
    public List<Task> getAllTask();
    public Task getTaskById(int id);
    public Task createTask(Task t);
    public Task updateTask(Task t);
    public void deleteTaskById(int id);
}
