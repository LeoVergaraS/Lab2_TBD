package com.app.voluntariosbe.services;

import com.app.voluntariosbe.models.Emergency;
import com.app.voluntariosbe.models.Task;
import com.app.voluntariosbe.repositories.EmergencyRepository;

import com.app.voluntariosbe.repositories.TaskRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
public class EmergencyServcice {

    private final EmergencyRepository emergencyRepository;
    EmergencyServcice(EmergencyRepository emergencyRepository){this.emergencyRepository = emergencyRepository;}

    @GetMapping("/emergencies")
    public List<Emergency> getAllEmergency(){ return emergencyRepository.getAllEmergency();}

    @GetMapping("/emergencies/count")
    public String countEmergencys(){
        int total = emergencyRepository.countEmergency();
        return String.format("tienes %s Emergencias!", total );
    }

    @PostMapping("/emergencies")
    @ResponseBody
    public Emergency createEmergency(@RequestBody Emergency emergency){
        Emergency result = emergencyRepository.createEmergency(emergency);
        return result;
    }

    @PutMapping("/emergencies")
    @ResponseBody
    public Emergency updateEmergency(@RequestBody Emergency emergency){
        Emergency result = emergencyRepository.updateEmergency(emergency);
        return result;
    }

    @DeleteMapping("/emergencies/{id}")
    public void deleteEmergency(@PathVariable int id){
        emergencyRepository.deleteEmergencyById(id);
    }

    @GetMapping("/emergencies/{id}")
    public Emergency getEmergencyById(@PathVariable int id){
        return emergencyRepository.getEmergencyById(id);
    }

}
