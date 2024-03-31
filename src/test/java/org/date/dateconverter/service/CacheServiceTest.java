package org.date.dateconverter.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CacheServiceTest {

    private CacheService cacheService;

    @BeforeEach
    void setUp() {
        cacheService = new CacheService();
    }

    @Test
    void put() {
        cacheService.put("key1", "value1");
        assertTrue(cacheService.containsKey("key1"));
    }

    @Test
    void get() {
        cacheService.put("key1", "value1");
        assertEquals("value1", cacheService.get("key1"));
    }

    @Test
    void containsKey() {
        assertFalse(cacheService.containsKey("nonExistingKey"));
        cacheService.put("key1", "value1");
        assertTrue(cacheService.containsKey("key1"));
    }

    @Test
    void remove() {
        cacheService.put("key1", "value1");
        assertTrue(cacheService.containsKey("key1"));
        cacheService.remove("key1");
        assertFalse(cacheService.containsKey("key1"));
    }

    @Test
    void getAllEntries() {
        cacheService.put("key1", "value1");
        cacheService.put("key2", "value2");
        Map<String, Object> allEntries = cacheService.getAllEntries();
        assertEquals(2, allEntries.size());
        assertTrue(allEntries.containsKey("key1"));
        assertTrue(allEntries.containsKey("key2"));
    }
}
