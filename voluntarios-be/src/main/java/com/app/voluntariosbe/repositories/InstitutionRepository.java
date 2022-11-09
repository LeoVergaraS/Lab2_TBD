package com.app.voluntariosbe.repositories;

import com.app.voluntariosbe.models.Institution;
import java.util.List;

public interface InstitutionRepository {
    public int countInstitution();
    public List<Institution> getAllInstitution();
    public Institution getInstitutionById(int id);
    public Institution createInstitution(Institution i);
    public Institution updateInstitution(Institution i);
    public void deleteInstitutionById(int id);
}
