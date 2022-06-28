package com.example.poc.boasaude.legado.service;

import com.example.poc.boasaude.legado.infra.Interface.ICache;
import com.example.poc.boasaude.legado.model.Treatment;
import com.example.poc.boasaude.legado.model.User;
import com.example.poc.boasaude.legado.service.Interface.ITreatment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
        User user = new User(UUID.randomUUID(), "Luiz");
        treatment = new Treatment(UUID.randomUUID(),
                "Atendimento dia X",
                "Atendimento de Rotina do paciente X",
                LocalDateTime.now(),
                LocalDateTime.now(),
                user);

        iCache.add(treatment.getId().toString(), treatment, 500000000);
        return treatment;
    }

    @Override
    public Object getTreatment(String id) {
        return iCache.get(id);
    }

    @Override
    public long getSize() {
        return iCache.size();
    }
}
