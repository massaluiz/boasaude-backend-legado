package com.example.poc.boasaude.legado.controller;


import com.example.poc.boasaude.legado.model.Treatment;
import com.example.poc.boasaude.legado.service.Interface.ITreatment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TreatmentController {

    @Autowired
    private ITreatment iTreatment;

    @GetMapping("/treatments")
    public List<Object> getAll() {
        return iTreatment.getAllTreatment();
    }

    @PostMapping("/treatment")
    public Treatment addTreatment(@RequestBody Treatment treatment) {
        return iTreatment.addTreatment(treatment);
    }

    @GetMapping("/treatment/{id}")
    public Object getTreatment(@PathVariable String id) {
        return iTreatment.getTreatment(id);
    }

    @GetMapping("/treatment/user/{user}")
    public List<Treatment> getTreatmentByUser(@PathVariable String user) {
        return iTreatment.getTreatmentByUser(user);
    }

    @DeleteMapping("/treatment/{id}")
    public void removeTreatment(@PathVariable String id) {
        iTreatment.removeTreatment(id);
    }

    @PutMapping("/treatment/insurance/{id}")
    public void authInsurance(@PathVariable String id) {
        iTreatment.authInsurance(id);
    }

    @GetMapping("/treatment/status/{status}")
    public List<Treatment> getTreatmentByStatus(@PathVariable String status) {
        return iTreatment.getTreatmentByStatus(status);
    }
}
