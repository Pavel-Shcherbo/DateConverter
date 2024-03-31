package org.date.dateconverterTest.controller;

import org.date.dateconverter.controller.DateController;
import org.date.dateconverter.models.Conversion;
import org.date.dateconverter.models.TimeEntry;
import org.date.dateconverter.models.TimeZones;
import org.date.dateconverter.service.ConversionService;
import org.date.dateconverter.service.TimeEntryService;
import org.date.dateconverter.service.TimeZonesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class DateControllerTest {

    @Mock
    private ConversionService conversionService;

    @Mock
    private TimeEntryService timeEntryService;

    @Mock
    private TimeZonesService timeZonesService;

    @InjectMocks
    private DateController dateController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateConversion() {
        // Arrange
        Conversion conversion = new Conversion();
        when(conversionService.createConversion(conversion)).thenReturn(conversion);

        // Act
        ResponseEntity<Conversion> response = dateController.createConversion(conversion);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(conversion, response.getBody());
    }

    @Test
    void testGetAllConversions() {
        // Arrange
        List<Conversion> conversions = new ArrayList<>();
        when(conversionService.getAllConversions()).thenReturn(conversions);

        // Act
        ResponseEntity<List<Conversion>> response = dateController.getAllConversions();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(conversions, response.getBody());
    }

    // Add similar tests for other controller methods (getConversionById, updateConversion, deleteConversionById)

    @Test
    void testGetAllTimeEntries() {
        // Arrange
        List<TimeEntry> timeEntries = new ArrayList<>();
        when(timeEntryService.getAllTimeEntries()).thenReturn(timeEntries);

        // Act
        ResponseEntity<List<TimeEntry>> response = dateController.getAllTimeEntries();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(timeEntries, response.getBody());
    }

    // Add similar tests for other controller methods related to TimeEntry

    @Test
    void testCreateTimeZone() {
        // Arrange
        TimeZones timeZone = new TimeZones();
        when(timeZonesService.createTimeZone(timeZone)).thenReturn(timeZone);

        // Act
        ResponseEntity<TimeZones> response = dateController.createTimeZone(timeZone);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(timeZone, response.getBody());
    }

    // Add similar tests for other controller methods related to TimeZone

    @Test
    void testGetUsefulData() {
        // Arrange
        Long timeZoneId = 1L;
        Long timeEntryId = 1L;
        List<Conversion> usefulData = new ArrayList<>();
        when(conversionService.getUsefulData(timeZoneId, timeEntryId)).thenReturn(usefulData);

        // Act
        ResponseEntity<List<Conversion>> response = dateController.getUsefulData(timeZoneId, timeEntryId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(usefulData, response.getBody());
    }

    // Add other tests as needed
}
