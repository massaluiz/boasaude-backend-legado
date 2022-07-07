package com.example.poc.boasaude.legado.infra;

import com.example.poc.boasaude.legado.infra.Interface.ICache;
import com.example.poc.boasaude.legado.model.Treatment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class InMemoryCache implements ICache {

    private static final int CLEAN_UP_PERIOD_IN_SEC = 5;

    private final ConcurrentHashMap<String, SoftReference<CacheObject>> cache = new ConcurrentHashMap<>();

    public InMemoryCache() {
        Thread cleanerThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Thread.sleep(CLEAN_UP_PERIOD_IN_SEC * 1000);
                    cache.entrySet().removeIf(entry -> Optional.ofNullable(entry.getValue()).map(SoftReference::get).map(CacheObject::isExpired).orElse(false));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        cleanerThread.setDaemon(true);
        cleanerThread.start();
    }

    @Override
    public void add(String key, Object value, long periodInMillis) {
        if (key == null) {
            return;
        }
        if (value == null) {
            cache.remove(key);
        } else {
            long expiryTime = System.currentTimeMillis() + periodInMillis;
            cache.put(key, new SoftReference<>(new CacheObject(value, expiryTime)));
        }
    }

    @Override
    public void remove(String key) {
        cache.remove(key);
    }

    @Override
    public Object get(String key) {
        return Optional.ofNullable(cache.get(key)).map(SoftReference::get).filter(cacheObject -> !cacheObject.isExpired()).map(CacheObject::getValue).orElse(null);
    }

    @Override
    public List<Object> getAll() {
        List<Object> treatments = new ArrayList<>();
        Iterator<ConcurrentHashMap.Entry<String, SoftReference<CacheObject>>> itr = cache.entrySet().iterator();
        while (itr.hasNext()) {
            ConcurrentHashMap.Entry<String, SoftReference<CacheObject>> entry = itr.next();
            treatments.add(this.get(entry.getKey()));
        }
        return treatments;
    }

    @Override
    public void clear() {
        cache.clear();
    }

    @Override
    public long size() {
        return cache.entrySet().stream().filter(entry -> Optional.ofNullable(entry.getValue()).map(SoftReference::get).map(cacheObject -> !cacheObject.isExpired()).orElse(false)).count();
    }

    @Override
    public List<Treatment> getAllType() {
        List<Treatment> treatments = new ArrayList<>();
        Iterator<ConcurrentHashMap.Entry<String, SoftReference<CacheObject>>> itr = cache.entrySet().iterator();
        while (itr.hasNext()) {
            ConcurrentHashMap.Entry<String, SoftReference<CacheObject>> entry = itr.next();
            treatments.add(Treatment.class.cast(this.get(entry.getKey())));
        }
        return treatments;
    }

    @AllArgsConstructor
    private static class CacheObject {

        @Getter
        private Object value;
        private long expiryTime;

        boolean isExpired() {
            return System.currentTimeMillis() > expiryTime;
        }
    }
}
