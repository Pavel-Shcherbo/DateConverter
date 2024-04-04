package org.date.dateconverter.controller;

import org.date.dateconverter.service.CacheService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.date.dateconverter.service.RequestCounterService;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

class CacheControllerTest {

    @Mock
    private CacheService cacheService;

    @Mock
    private RequestCounterService requestCounterService;
    @Mock
    private CacheController cacheController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        cacheController = new CacheController(cacheService, requestCounterService);
    }

    @Test
    void getCacheContent() {
        // Arrange
        Map<String, Object> cacheContent = new HashMap<>();
        cacheContent.put("key1", "value1");
        cacheContent.put("key2", "value2");
        when(cacheService.getAllEntries()).thenReturn(cacheContent);

        // Act
        ResponseEntity<Map<String, Object>> responseEntity = cacheController.getCacheContent();

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(cacheContent, responseEntity.getBody());

        // Verify
        verify(requestCounterService).incrementAndGet();
    }
}
