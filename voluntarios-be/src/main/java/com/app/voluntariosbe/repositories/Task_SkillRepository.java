package com.app.voluntariosbe.repositories;

import com.app.voluntariosbe.models.Task;
import com.app.voluntariosbe.models.Task_Skill;
import java.util.List;

public interface Task_SkillRepository {
    public int countTask_Skill();
    public List<Task_Skill> getAllTask_Skill();
    public Task_Skill getTask_SkillById(int id);
    public Task_Skill createTask_Skill(Task_Skill t);
    public Task_Skill updateTask_Skill(Task_Skill t);
    public void deleteTask_SkillById(int id);
}
