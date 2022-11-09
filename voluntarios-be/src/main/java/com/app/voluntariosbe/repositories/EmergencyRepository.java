package com.app.voluntariosbe.repositories;

import com.app.voluntariosbe.models.Emergency;

import java.util.List;

public interface EmergencyRepository {
    public int countEmergency();
    public List<Emergency> getAllEmergency();
    public Emergency getEmergencyById(int id);
    public Emergency createEmergency(Emergency e);
    public Emergency updateEmergency(Emergency e);
    public void deleteEmergencyById(int id);
}
