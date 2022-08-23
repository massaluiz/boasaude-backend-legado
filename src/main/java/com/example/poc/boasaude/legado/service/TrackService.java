package com.example.poc.boasaude.legado.service;

import com.example.poc.boasaude.legado.infra.Interface.ICache;
import com.example.poc.boasaude.legado.model.Track;
import com.example.poc.boasaude.legado.service.Interface.ITrack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class TrackService implements ITrack {

    @Autowired
    ICache iCache;

    @Override
    public void loadTrack() {
        for (int i = 1; i <= 100; i++) {
            Track track = new Track();
            track.setId(UUID.randomUUID());
            track.setAction(getRandomAction());
            track.setUser("LUIZ");
            track.setCreateAt(LocalDateTime.now());
            track.setType("TRACK");
            iCache.add(track.getId().toString(), track, 500000000);
        }
    }

    @Override
    public List<Object> getAll() {
        List<Object> tracks = new ArrayList<>();
        for (Object obj : iCache.getAll()) {
            if (obj.getClass().equals(Track.class)) {
                tracks.add(obj);
            }
        }
        return tracks;
    }

    private String getRandomAction() {
        List<String> actions = new ArrayList<>(
                Arrays.asList("DELETE_TREATMENT",
                        "ADD_TREATMENT",
                        "EDIT_TREATMENT",
                        "CHANGE_TREATMENT_DATE"));
        Random r = new Random();
        int randomNumber = r.nextInt(actions.size());
        return actions.get(randomNumber);

    }
}
