package com.example.poc.boasaude.legado.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class User {

    private UUID id;
    private String name;
}
