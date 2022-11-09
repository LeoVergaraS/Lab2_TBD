package com.app.voluntariosbe.repositories;

import com.app.voluntariosbe.models.Vol_Skill;
import java.util.List;

public interface Vol_SkillRepository {
    public int countVol_Skill();
    public List<Vol_Skill> getAllVol_Skill();
    public Vol_Skill getVol_SkillById(int id);
    public Vol_Skill createVol_Skill(Vol_Skill v);
    public Vol_Skill updateVol_Skill(Vol_Skill v);
    public void deleteVol_SkillById(int id);
}
