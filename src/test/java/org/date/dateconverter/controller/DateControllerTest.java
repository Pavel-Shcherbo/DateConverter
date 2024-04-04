package org.date.dateconverter.controller;

import org.date.dateconverter.models.Conversion;
import org.date.dateconverter.models.TimeEntry;
import org.date.dateconverter.models.TimeZones;
import org.date.dateconverter.service.ConversionService;
import org.date.dateconverter.service.RequestCounterService;
import org.date.dateconverter.service.TimeEntryService;
import org.date.dateconverter.service.TimeZonesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class DateControllerTest {

    @Mock
    private ConversionService conversionService;
    @Mock
    private TimeEntryService timeEntryService;
    @Mock
    private TimeZonesService timeZonesService;
    @Mock
    private RequestCounterService requestCounterService;



    private DateController dateController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        dateController = new DateController(conversionService, timeEntryService, timeZonesService, requestCounterService);
    }

    // Tests for Conversion endpoints

    @Test
    void createConversion() {
        // Arrange
        Conversion conversion = new Conversion();
        when(conversionService.createConversion(conversion)).thenReturn(conversion);

        // Act
        ResponseEntity<Conversion> responseEntity = dateController.createConversion(conversion);

        // Assert
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(conversion, responseEntity.getBody());
    }

    @Test
    void getAllConversions() {
        // Arrange
        List<Conversion> conversions = Arrays.asList(new Conversion(), new Conversion());
        when(conversionService.getAllConversions()).thenReturn(conversions);

        // Act
        ResponseEntity<List<Conversion>> responseEntity = dateController.getAllConversions();

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(conversions, responseEntity.getBody());
    }

    @Test
    void getConversionById() {
        // Arrange
        long id = 1L;
        Conversion conversion = new Conversion();
        when(conversionService.getConversionById(id)).thenReturn(Optional.of(conversion));

        // Act
        ResponseEntity<Conversion> responseEntity = dateController.getConversionById(id);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(conversion, responseEntity.getBody());
    }

    @Test
    void updateConversion() {
        // Arrange
        long id = 1L;
        Conversion conversion = new Conversion();

        // Act
        ResponseEntity<Void> responseEntity = dateController.updateConversion(id, conversion);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(conversionService, times(1)).updateConversion(conversion);
    }

    @Test
    void deleteConversionById() {
        // Arrange
        long id = 1L;

        // Act
        ResponseEntity<Void> responseEntity = dateController.deleteConversionById(id);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(conversionService, times(1)).deleteConversionById(id);
    }

    @Test
    void getAllTimeEntries() {
        // Arrange
        List<TimeEntry> timeEntries = Arrays.asList(new TimeEntry(), new TimeEntry());
        when(timeEntryService.getAllTimeEntries()).thenReturn(timeEntries);

        // Act
        ResponseEntity<List<TimeEntry>> responseEntity = dateController.getAllTimeEntries();

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(timeEntries, responseEntity.getBody());
    }

    @Test
    void getTimeEntryById() {
        // Arrange
        long id = 1L;
        TimeEntry timeEntry = new TimeEntry();
        when(timeEntryService.getTimeEntryById(id)).thenReturn(Optional.of(timeEntry));

        // Act
        ResponseEntity<TimeEntry> responseEntity = dateController.getTimeEntryById(id);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(timeEntry, responseEntity.getBody());
    }

    @Test
    void updateTimeEntry() {
        // Arrange
        long id = 1L;
        TimeEntry timeEntry = new TimeEntry();

        // Act
        ResponseEntity<Void> responseEntity = dateController.updateTimeEntry(id, timeEntry);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(timeEntryService, times(1)).updateTimeEntry(timeEntry);
    }

    @Test
    void deleteTimeEntryById() {
        // Arrange
        long id = 1L;

        // Act
        ResponseEntity<Void> responseEntity = dateController.deleteTimeEntryById(id);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(timeEntryService, times(1)).deleteTimeEntryById(id);
    }

    // Tests for TimeZones endpoints

    @Test
    void createTimeZone() {
        // Arrange
        TimeZones timeZone = new TimeZones();
        when(timeZonesService.createTimeZone(timeZone)).thenReturn(timeZone);

        // Act
        ResponseEntity<TimeZones> responseEntity = dateController.createTimeZone(timeZone);

        // Assert
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(timeZone, responseEntity.getBody());
    }

    @Test
    void getAllTimeZones() {
        // Arrange
        List<TimeZones> timeZones = Arrays.asList(new TimeZones(), new TimeZones());
        when(timeZonesService.getAllTimeZones()).thenReturn(timeZones);

        // Act
        ResponseEntity<List<TimeZones>> responseEntity = dateController.getAllTimeZones();

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(timeZones, responseEntity.getBody());
    }

    @Test
    void getTimeZoneById() {
        // Arrange
        long id = 1L;
        TimeZones timeZone = new TimeZones();
        when(timeZonesService.getTimeZoneById(id)).thenReturn(Optional.of(timeZone));

        // Act
        ResponseEntity<TimeZones> responseEntity = dateController.getTimeZoneById(id);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(timeZone, responseEntity.getBody());
    }

    @Test
    void updateTimeZone() {
        // Arrange
        long id = 1L;
        TimeZones timeZone = new TimeZones();

        // Act
        ResponseEntity<Void> responseEntity = dateController.updateTimeZone(id, timeZone);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(timeZonesService, times(1)).updateTimeZone(timeZone);
    }

    @Test
    void deleteTimeZoneById() {
        // Arrange
        long id = 1L;

        // Act
        ResponseEntity<Void> responseEntity = dateController.deleteTimeZoneById(id);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(timeZonesService, times(1)).deleteTimeZoneById(id);
    }

    @Test
    void getUsefulData() {
        // Arrange
        Long timeZoneId = 1L;
        Long timeEntryId = 1L;
        List<Conversion> conversions = Arrays.asList(new Conversion(), new Conversion());
        when(conversionService.getUsefulData(timeZoneId, timeEntryId)).thenReturn(conversions);

        // Act
        ResponseEntity<List<Conversion>> responseEntity = dateController.getUsefulData(timeZoneId, timeEntryId);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(conversions, responseEntity.getBody());
    }

}
