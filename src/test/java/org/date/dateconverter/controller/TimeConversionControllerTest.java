package org.date.dateconverter.controller;

import org.date.dateconverter.dto.TimeConversionDTO;
import org.date.dateconverter.models.TimeEntry;
import org.date.dateconverter.models.TimeZones;
import org.date.dateconverter.repository.TimeZonesRepository;
import org.date.dateconverter.service.CacheService;
import org.date.dateconverter.service.TimeConversionService;
import org.date.dateconverter.service.TimeEntryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TimeConversionControllerTest {

    @Mock
    private TimeConversionService timeConversionService;
    @Mock
    private TimeEntryService timeEntryService;
    @Mock
    private TimeZonesRepository timeZonesRepository;
    @Mock
    private CacheService cacheService;

    private TimeConversionController timeConversionController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        timeConversionController = new TimeConversionController(
                timeConversionService,
                timeEntryService,
                timeZonesRepository,
                cacheService
        );
    }


    @Test
    void convertTime_Cached() {
        // Arrange
        long milliseconds = 1617325533000L;
        String cacheKey = "conversion_" + milliseconds;

        TimeConversionDTO cachedResult = new TimeConversionDTO();
        when(cacheService.containsKey(cacheKey)).thenReturn(true);
        when(cacheService.get(cacheKey)).thenReturn(cachedResult);

        // Act
        ResponseEntity<TimeConversionDTO> responseEntity = timeConversionController.convertTime(milliseconds);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(cachedResult, responseEntity.getBody());
        verify(timeEntryService, never()).createTimeEntry(milliseconds);
        verify(timeZonesRepository, never()).findByTimeZone(anyString());
        verify(timeConversionService, never()).convertTime(anyLong(), any(TimeEntry.class), any(TimeZones.class));
    }

    @Test
    void getTimeZone() {
        // Arrange
        String defaultTimeZoneId = TimeZone.getDefault().getID();

        // Act
        String timeZoneId = timeConversionController.getTimeZone();

        // Assert
        assertEquals(defaultTimeZoneId, timeZoneId);
    }
}
