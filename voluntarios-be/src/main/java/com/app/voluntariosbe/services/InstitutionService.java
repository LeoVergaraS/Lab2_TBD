package com.app.voluntariosbe.services;

import com.app.voluntariosbe.models.Institution;
import com.app.voluntariosbe.models.Task;
import com.app.voluntariosbe.repositories.InstitutionRepository;

import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
public class InstitutionService {
    private final InstitutionRepository institutionRepository;
    InstitutionService(InstitutionRepository institutionRepository){this.institutionRepository = institutionRepository;}

    @GetMapping("/institutions")
    public List<Institution> getAllInstitutions(){ return institutionRepository.getAllInstitution();}

    @GetMapping("/institutions/count")
    public String countInstitutions(){
        int total = institutionRepository.countInstitution();
        return String.format("tienes %s Instituciones!", total );
    }

    @PostMapping("/institutions")
    @ResponseBody
    public Institution createInstitution(@RequestBody Institution institution){
        Institution result = institutionRepository.createInstitution(institution);
        return result;
    }

    @PutMapping("/institutions")
    @ResponseBody
    public Institution updateInstitution(@RequestBody Institution institution){
        Institution result = institutionRepository.updateInstitution(institution);
        return result;
    }

    @DeleteMapping("/institutions/{id}")
    public void deleteInstitution(@PathVariable int id){
        institutionRepository.deleteInstitutionById(id);
    }

    @GetMapping("/institutions/{id}")
    public Institution getInstitutionById(@PathVariable int id){
        return institutionRepository.getInstitutionById(id);
    }

}
