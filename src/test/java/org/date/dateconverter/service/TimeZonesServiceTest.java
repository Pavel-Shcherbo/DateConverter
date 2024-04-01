package org.date.dateconverter.service;

import org.date.dateconverter.models.TimeZones;
import org.date.dateconverter.repository.TimeZonesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class TimeZonesServiceTest {

    @Mock
    private TimeZonesRepository timeZonesRepository;

    private TimeZonesService timeZonesService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        timeZonesService = new TimeZonesService(timeZonesRepository);
    }

    @Test
    void createTimeZone() {
        // Arrange
        TimeZones timeZone = new TimeZones();
        when(timeZonesRepository.save(timeZone)).thenReturn(timeZone);

        // Act
        TimeZones result = timeZonesService.createTimeZone(timeZone);

        // Assert
        assertEquals(timeZone, result);
        verify(timeZonesRepository, times(1)).save(timeZone);
    }

    @Test
    void getAllTimeZones() {
        // Arrange
        List<TimeZones> timeZonesList = Arrays.asList(new TimeZones(), new TimeZones());
        when(timeZonesRepository.findAll()).thenReturn(timeZonesList);

        // Act
        List<TimeZones> result = timeZonesService.getAllTimeZones();

        // Assert
        assertEquals(2, result.size());
        verify(timeZonesRepository, times(1)).findAll();
    }

    @Test
    void getTimeZoneById() {
        // Arrange
        long id = 1L;
        TimeZones timeZone = new TimeZones();
        when(timeZonesRepository.findById(id)).thenReturn(Optional.of(timeZone));

        // Act
        Optional<TimeZones> result = timeZonesService.getTimeZoneById(id);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(timeZone, result.get());
        verify(timeZonesRepository, times(1)).findById(id);
    }

    @Test
    void updateTimeZone() {
        // Arrange
        TimeZones timeZone = new TimeZones();

        // Act
        timeZonesService.updateTimeZone(timeZone);

        // Assert
        verify(timeZonesRepository, times(1)).save(timeZone);
    }

    @Test
    void deleteTimeZoneById() {
        // Arrange
        long id = 1L;
        TimeZones timeZone = new TimeZones();
        when(timeZonesRepository.findById(id)).thenReturn(Optional.of(timeZone));

        // Act
        timeZonesService.deleteTimeZoneById(id);

        // Assert
        verify(timeZonesRepository, times(1)).findById(id);
        verify(timeZonesRepository, times(1)).deleteById(id);
    }
}
