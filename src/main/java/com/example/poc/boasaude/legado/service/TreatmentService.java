package com.example.poc.boasaude.legado.service;

import com.example.poc.boasaude.legado.infra.Interface.ICache;
import com.example.poc.boasaude.legado.model.Track;
import com.example.poc.boasaude.legado.model.Treatment;
import com.example.poc.boasaude.legado.service.Interface.ITrack;
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

    @Autowired
    ITrack iTrack;

    @Override
    public List<Object> getAllTreatment() {
        List<Object> treatments = new ArrayList<>();
        for (Object obj : iCache.getAll()) {
            if (obj.getClass().equals(Treatment.class)) {
                treatments.add(obj);
            }
        }
        return treatments;
    }


    @Override
    public Treatment addTreatment(Treatment treatment) {
        treatment.setId(UUID.randomUUID());
        treatment.setType("TREATMENT");
        iCache.add(treatment.getId().toString(), treatment, 500000000);
        addingTrack("ADD_TREATMENT", treatment.getUser());
        return treatment;
    }

    private void addingTrack(String action, String user) {
        Track track = new Track();
        track.setId(UUID.randomUUID());
        track.setAction(action);
        track.setUser(user);
        track.setCreateAt(LocalDateTime.now());
        track.setType("TRACK");
        iCache.add(track.getId().toString(), track, 500000000);
    }

    @Override
    public Object getTreatment(String id) {
        return iCache.get(id);
    }

    @Override
    public void removeTreatment(String id) {
        iCache.remove(id);
        addingTrack("DELETE_TREATMENT", "LUIZ");
    }

    @Override
    public List<Treatment> getTreatmentByUser(String user) {
        List<Object> objects = iCache.getAllType();
        List<Treatment> treatmentsReturn = new ArrayList<>();
        for (Object object : objects) {
            if (object.getClass().equals(Treatment.class)) {
                if (((Treatment) object).getUser().equalsIgnoreCase(user)) {
                    treatmentsReturn.add(((Treatment) object));
                }
            }
        }
        return treatmentsReturn;
    }
}
