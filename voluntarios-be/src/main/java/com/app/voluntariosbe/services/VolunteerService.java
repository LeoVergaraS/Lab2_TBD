package com.app.voluntariosbe.services;

import com.app.voluntariosbe.models.Task;
import com.app.voluntariosbe.models.Volunteer;
import com.app.voluntariosbe.repositories.TaskRepository;
import com.app.voluntariosbe.repositories.VolunteerRepository;

import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
public class VolunteerService {
    private final VolunteerRepository volunteerRepository;
    VolunteerService(VolunteerRepository volunteerRepository){this.volunteerRepository = volunteerRepository;}

    @GetMapping("/volunteers")
    public List<Volunteer> getAllVolunteers(){ return volunteerRepository.getAllVolunteer();}

    @GetMapping("/volunteers/count")
    public String countVolunteers(){
        int total = volunteerRepository.countVolunteer();
        return String.format("tienes %s Voluntarios!", total );
    }

    @PostMapping("/volunteers")
    @ResponseBody
    public Volunteer createVolunteer(@RequestBody Volunteer volunteer){
        Volunteer result = volunteerRepository.createVolunteer(volunteer);
        return result;
    }

    @PutMapping("/volunteers")
    @ResponseBody
    public Volunteer updateVolunteer(@RequestBody Volunteer volunteer){
        Volunteer result = volunteerRepository.updateVolunteer(volunteer);
        return result;
    }

    @DeleteMapping("/volunteers/{id}")
    public void deleteVolunteer(@PathVariable int id){
        volunteerRepository.deleteVolunteerById(id);
    }

    @GetMapping("/volunteers/{id}")
    public Volunteer getVolunteerById(@PathVariable int id){
        return volunteerRepository.getVolunteerById(id);
    }

}
