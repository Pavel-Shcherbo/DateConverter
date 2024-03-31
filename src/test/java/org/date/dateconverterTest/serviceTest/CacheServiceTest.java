package org.date.dateconverterTest.serviceTest;

import org.date.dateconverter.service.CacheService;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CacheServiceTest {
    @Test
    void putAndGet() {
        CacheService cacheService = new CacheService();
        String key = "testKey";
        String value = "testValue";

        cacheService.put(key, value);

        assertEquals(value, cacheService.get(key));
    }

    @Test
    void containsKey() {
        CacheService cacheService = new CacheService();
        String key = "testKey";
        String value = "testValue";

        cacheService.put(key, value);

        assertTrue(cacheService.containsKey(key));
        assertFalse(cacheService.containsKey("nonExistingKey"));
    }

    @Test
    void remove() {
        CacheService cacheService = new CacheService();
        String key = "testKey";
        String value = "testValue";

        cacheService.put(key, value);
        cacheService.remove(key);

        assertNull(cacheService.get(key));
    }

    @Test
    void getAllEntries() {
        CacheService cacheService = new CacheService();
        String key1 = "testKey1";
        String value1 = "testValue1";
        String key2 = "testKey2";
        String value2 = "testValue2";

        cacheService.put(key1, value1);
        cacheService.put(key2, value2);

        Map<String, Object> allEntries = cacheService.getAllEntries();

        assertEquals(2, allEntries.size());
        assertTrue(allEntries.containsKey(key1));
        assertTrue(allEntries.containsKey(key2));
        assertEquals(value1, allEntries.get(key1));
        assertEquals(value2, allEntries.get(key2));
    }
}
