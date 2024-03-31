package org.date.dateconverterTest.controller;

import org.date.dateconverter.controller.TimeConversionController;
import org.date.dateconverter.dto.TimeConversionDTO;
import org.date.dateconverter.repository.TimeZonesRepository;
import org.date.dateconverter.service.CacheService;
import org.date.dateconverter.service.TimeConversionService;
import org.date.dateconverter.service.TimeEntryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class TimeConversionControllerTest {

    @Mock
    private TimeConversionService timeConversionService;

    @Mock
    private TimeEntryService timeEntryService;

    @Mock
    private TimeZonesRepository timeZonesRepository;

    @Mock
    private CacheService cacheService;

    @InjectMocks
    private TimeConversionController timeConversionController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testConvertTime_CacheHit() {
        // Arrange
        long milliseconds = 1000L;
        String cacheKey = "conversion_" + milliseconds;
        TimeConversionDTO cachedResult = new TimeConversionDTO();
        when(cacheService.containsKey(cacheKey)).thenReturn(true);
        when(cacheService.get(cacheKey)).thenReturn(cachedResult);

        // Act
        ResponseEntity<TimeConversionDTO> response = timeConversionController.convertTime(milliseconds);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cachedResult, response.getBody());
    }

    // Add more tests to cover different scenarios for convertTime method

    @Test
    void testGetTimeZone() {
        // Arrange
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+1"));
        String expectedTimeZone = "GMT+01:00";

        // Act
        String timeZone = timeConversionController.getTimeZone();

        // Assert
        assertEquals(expectedTimeZone, timeZone);
    }

    // Add more tests as needed
}
