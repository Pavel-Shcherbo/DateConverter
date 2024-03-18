package org.date.dateconverter.service;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CacheService {
    private final Map<String, Object> cacheMap;

    public CacheService() {
        this.cacheMap = new ConcurrentHashMap<>();
    }

    public void put(String key, Object value) {
        cacheMap.put(key, value);
    }

    public Object get(String key) {
        return cacheMap.get(key);
    }

    public boolean containsKey(String key) {
        return cacheMap.containsKey(key);
    }

    public void remove(String key) {
        cacheMap.remove(key);
    }

    public Map<String, Object> getAllEntries() {
        return new ConcurrentHashMap<>(cacheMap);
    }
}
