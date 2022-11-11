package com.app.voluntariosbe.repositories;

import com.app.voluntariosbe.models.Volunteer;
import com.app.voluntariosbe.models.Volunteer_Query;

import java.util.List;

public interface VolunteerRepository {
    public int countVolunteer();
    public List<Volunteer> getAllVolunteer();
    public Volunteer getVolunteerById(int id);
    public Volunteer createVolunteer(Volunteer v);
    public Volunteer updateVolunteer(Volunteer v);
    public void deleteVolunteerById(int id);
    public List<Volunteer> getVolunteerByEmergency(int id);
    public List<Volunteer_Query> getVolunteerByTask(int id);
}
