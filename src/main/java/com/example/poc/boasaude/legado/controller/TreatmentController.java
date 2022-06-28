package com.example.poc.boasaude.legado.controller;


import com.example.poc.boasaude.legado.model.Treatment;
import com.example.poc.boasaude.legado.service.Interface.ITreatment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TreatmentController {

    @Autowired
    private ITreatment iTreatment;

    @GetMapping("/treatment")
    public List<Object> getAll() {
        return iTreatment.getAllTreatment();
    }

    @GetMapping("/treatment/1")
    public Treatment addTreatment() {
        return iTreatment.addTreatment(new Treatment());
    }

    @GetMapping("/treatment/get/{id}")
    public Object getTreatment(@PathVariable String id) {
        return iTreatment.getTreatment(id);
    }

    @GetMapping("/treatment/size")
    public Object size() {
        return iTreatment.getSize();
    }
}
