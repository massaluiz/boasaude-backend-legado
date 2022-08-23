package com.example.poc.boasaude.legado.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Track {

    private UUID id;
    private String action;
    private String user;
    private LocalDateTime createAt;
    private String type;
}
