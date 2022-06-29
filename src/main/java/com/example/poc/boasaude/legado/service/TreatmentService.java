package com.example.poc.boasaude.legado.service;

import com.example.poc.boasaude.legado.infra.Interface.ICache;
import com.example.poc.boasaude.legado.model.Treatment;
import com.example.poc.boasaude.legado.service.Interface.ITreatment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TreatmentService implements ITreatment {

    @Autowired
    ICache iCache;

    @Override
    public List<Object> getAllTreatment() {
        return iCache.getAll();
    }


    @Override
    public Treatment addTreatment(Treatment treatment) {
        treatment.setId(UUID.randomUUID());
        iCache.add(treatment.getId().toString(), treatment, 500000000);
        return treatment;
    }

    @Override
    public Object getTreatment(String id) {
        return iCache.get(id);
    }

    @Override
    public void removeTreatment(String id) {
        iCache.remove(id);
    }
}
