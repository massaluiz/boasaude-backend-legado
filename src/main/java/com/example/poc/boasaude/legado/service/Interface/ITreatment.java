package com.example.poc.boasaude.legado.service.Interface;

import com.example.poc.boasaude.legado.model.Treatment;

import java.util.List;

public interface ITreatment {
    public List<Object> getAllTreatment();

    Treatment addTreatment(Treatment treatment);

    Object getTreatment(String id);

    void removeTreatment(String id);

    List<Treatment> getTreatmentByUser(String user);

    List<Treatment> getTreatmentByStatus(String status);

    void authInsurance(String id);
}
