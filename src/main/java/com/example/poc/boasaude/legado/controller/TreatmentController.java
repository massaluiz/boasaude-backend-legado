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

    @DeleteMapping("/treatment/{id}")
    public void removeTreatment(@PathVariable String id) {
        iTreatment.removeTreatment(id);
    }
}
