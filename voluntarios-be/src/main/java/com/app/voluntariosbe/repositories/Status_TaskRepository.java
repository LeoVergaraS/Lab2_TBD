package com.app.voluntariosbe.repositories;

import com.app.voluntariosbe.models.Status_Task;
import com.app.voluntariosbe.models.Task;

import java.util.List;

public interface Status_TaskRepository {
    public int countStatus_Task();
    public List<Status_Task> getAllStatus_Task();
    public Status_Task getStatus_TaskById(int id);
    public Status_Task createStatus_Task(Status_Task s);
    public Status_Task updateStatus_Task(Status_Task s);
    public void deleteStatus_TaskById(int id);
}
