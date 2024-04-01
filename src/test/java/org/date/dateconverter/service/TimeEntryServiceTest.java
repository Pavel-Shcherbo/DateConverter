package org.date.dateconverter.service;

import org.date.dateconverter.models.TimeEntry;
import org.date.dateconverter.repository.TimeEntryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TimeEntryServiceTest {

    @Mock
    private TimeEntryRepository timeEntryRepository;

    private TimeEntryService timeEntryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        timeEntryService = new TimeEntryService(timeEntryRepository);
    }

    @Test
    void getAllTimeEntries() {
        // Arrange
        List<TimeEntry> timeEntries = Arrays.asList(new TimeEntry(), new TimeEntry());
        when(timeEntryRepository.findAll()).thenReturn(timeEntries);

        // Act
        List<TimeEntry> result = timeEntryService.getAllTimeEntries();

        // Assert
        assertEquals(2, result.size());
        verify(timeEntryRepository, times(1)).findAll();
    }

    @Test
    void getTimeEntryById() {
        // Arrange
        long id = 1L;
        TimeEntry timeEntry = new TimeEntry();
        when(timeEntryRepository.findById(id)).thenReturn(Optional.of(timeEntry));

        // Act
        Optional<TimeEntry> result = timeEntryService.getTimeEntryById(id);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(timeEntry, result.get());
        verify(timeEntryRepository, times(1)).findById(id);
    }

    @Test
    void createTimeEntry() {
        // Arrange
        long milliseconds = 1000L;
        when(timeEntryRepository.save(any(TimeEntry.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        TimeEntry result = timeEntryService.createTimeEntry(milliseconds);

        // Assert
        assertNotNull(result);
        assertEquals(milliseconds, result.getMilliseconds());
        verify(timeEntryRepository, times(1)).save(any(TimeEntry.class));
    }

    @Test
    void updateTimeEntry() {
        // Arrange
        TimeEntry timeEntry = new TimeEntry();

        // Act
        timeEntryService.updateTimeEntry(timeEntry);

        // Assert
        verify(timeEntryRepository, times(1)).save(timeEntry);
    }

    @Test
    void updateTimeEntryById() {
        // Arrange
        long id = 1L;
        long milliseconds = 2000L;
        TimeEntry timeEntry = new TimeEntry();
        when(timeEntryRepository.findById(id)).thenReturn(Optional.of(timeEntry));

        // Act
        timeEntryService.updateTimeEntryById(id, milliseconds);

        // Assert
        assertEquals(milliseconds, timeEntry.getMilliseconds());
        verify(timeEntryRepository, times(1)).findById(id);
        verify(timeEntryRepository, times(1)).save(timeEntry);
    }

    @Test
    void deleteTimeEntryById() {
        // Arrange
        long id = 1L;

        // Act
        timeEntryService.deleteTimeEntryById(id);

        // Assert
        verify(timeEntryRepository, times(1)).deleteById(id);
    }

    @Test
    void deleteAllTimeEntries() {
        // Act
        timeEntryService.deleteAllTimeEntries();

        // Assert
        verify(timeEntryRepository, times(1)).deleteAll();
    }
}
