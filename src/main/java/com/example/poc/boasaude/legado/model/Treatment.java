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
public class Treatment {

    private UUID id;
    private String title;
    private String description;
    private LocalDateTime createAt;
    private LocalDateTime treatmentDate;
    private String user;
    private String speciality;
    private String type;
    private String healthInsurance;
    private String doctor;
    private String status;
}
