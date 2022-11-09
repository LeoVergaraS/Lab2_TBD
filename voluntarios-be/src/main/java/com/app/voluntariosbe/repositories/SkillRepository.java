package com.app.voluntariosbe.repositories;

import com.app.voluntariosbe.models.Skill;

import java.util.List;

public interface SkillRepository {
    public int countSkill();
    public List<Skill> getAllSkill();
    public Skill getSkillById(int id);
    public Skill createSkill(Skill s);
    public Skill updateSkill(Skill s);
    public void deleteSkillById(int id);
    public List<Skill> getSkillByEmergency(int id);
}
