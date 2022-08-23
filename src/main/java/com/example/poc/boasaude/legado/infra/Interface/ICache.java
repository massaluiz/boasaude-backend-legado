package com.example.poc.boasaude.legado.infra.Interface;

import com.example.poc.boasaude.legado.model.Treatment;

import java.util.List;

public interface ICache {
    void add(String key, Object value, long periodInMillis);

    void remove(String key);

    Object get(String key);

    List<Object> getAll();

    void clear();

    long size();

    List<Object> getAllType();
}
