package org.date.dateconverter.controller;

import org.date.dateconverter.service.CacheService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CacheControllerTest {

    @Mock
    private CacheService cacheService;

    @InjectMocks
    private CacheController cacheController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetCacheContent() {
        // Arrange
        Map<String, Object> expectedContent = new HashMap<>();
        expectedContent.put("key1", "value1");
        expectedContent.put("key2", "value2");

        when(cacheService.getAllEntries()).thenReturn(expectedContent);

        // Act
        ResponseEntity<Map<String, Object>> response = cacheController.getCacheContent();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedContent, response.getBody());
    }
}
