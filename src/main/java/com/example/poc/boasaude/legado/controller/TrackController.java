package com.example.poc.boasaude.legado.controller;


import com.example.poc.boasaude.legado.service.Interface.ITrack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TrackController {

    @Autowired
    private ITrack iTrack;

    @GetMapping("/track")
    public void load() {
        iTrack.loadTrack();
    }

    @GetMapping("/tracks")
    public List<Object> getAll() {
        return iTrack.getAll();
    }
}
