package com.app.voluntariosbe.repositories;

import com.app.voluntariosbe.models.Eme_Skill;
import java.util.List;

public interface Eme_SkillRepository {
    public int countEme_Skill();
    public List<Eme_Skill> getAllEme_Skill();
    public Eme_Skill getEme_SkillById(int id);
    public Eme_Skill createEme_Skill(Eme_Skill t);
    public Eme_Skill updateEme_Skill(Eme_Skill t);
    public void deleteEme_SkillById(int id);
}
