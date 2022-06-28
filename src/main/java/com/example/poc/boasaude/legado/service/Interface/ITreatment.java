package com.example.poc.boasaude.legado.service.Interface;

import com.example.poc.boasaude.legado.model.Treatment;

import java.util.List;

public interface ITreatment {
    public List<Object> getAllTreatment();
    Treatment addTreatment(Treatment treatment);

    Object getTreatment(String id);

    long getSize();
}
