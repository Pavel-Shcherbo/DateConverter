package org.date.dateconverter.models;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ConversionTest {

    @Test
    void getId() {
        // Arrange
        Long id = 1L;
        Conversion conversion = new Conversion();
        conversion.setId(id);

        // Act
        Long retrievedId = conversion.getId();

        // Assert
        assertEquals(id, retrievedId);
    }

    @Test
    void setId() {
        // Arrange
        Long id = 1L;
        Conversion conversion = new Conversion();

        // Act
        conversion.setId(id);

        // Assert
        assertEquals(id, conversion.getId());
    }

    @Test
    void getTimeInCurrentTimeZone() {
        // Arrange
        String timeInCurrentTimeZone = "2022-04-05 15:30:00";
        Conversion conversion = new Conversion();
        conversion.setTimeInCurrentTimeZone(timeInCurrentTimeZone);

        // Act
        String retrievedTimeInCurrentTimeZone = conversion.getTimeInCurrentTimeZone();

        // Assert
        assertEquals(timeInCurrentTimeZone, retrievedTimeInCurrentTimeZone);
    }

    @Test
    void setTimeInCurrentTimeZone() {
        // Arrange
        String timeInCurrentTimeZone = "2022-04-05 15:30:00";
        Conversion conversion = new Conversion();

        // Act
        conversion.setTimeInCurrentTimeZone(timeInCurrentTimeZone);

        // Assert
        assertEquals(timeInCurrentTimeZone, conversion.getTimeInCurrentTimeZone());
    }

    @Test
    void getTimeInGMT() {
        // Arrange
        String timeInGMT = "2022-04-05 10:30:00";
        Conversion conversion = new Conversion();
        conversion.setTimeInGMT(timeInGMT);

        // Act
        String retrievedTimeInGMT = conversion.getTimeInGMT();

        // Assert
        assertEquals(timeInGMT, retrievedTimeInGMT);
    }

    @Test
    void setTimeInGMT() {
        // Arrange
        String timeInGMT = "2022-04-05 10:30:00";
        Conversion conversion = new Conversion();

        // Act
        conversion.setTimeInGMT(timeInGMT);

        // Assert
        assertEquals(timeInGMT, conversion.getTimeInGMT());
    }

    @Test
    void getTimeEntries() {
        // Arrange
        Set<TimeEntry> timeEntries = new HashSet<>();
        timeEntries.add(new TimeEntry());
        timeEntries.add(new TimeEntry());
        Conversion conversion = new Conversion();
        conversion.setTimeEntries(timeEntries);

        // Act
        Set<TimeEntry> retrievedTimeEntries = conversion.getTimeEntries();

        // Assert
        assertEquals(timeEntries.size(), retrievedTimeEntries.size());
        assertTrue(retrievedTimeEntries.containsAll(timeEntries));
    }

    @Test
    void setTimeEntries() {
        // Arrange
        Set<TimeEntry> timeEntries = new HashSet<>();
        timeEntries.add(new TimeEntry());
        timeEntries.add(new TimeEntry());
        Conversion conversion = new Conversion();

        // Act
        conversion.setTimeEntries(timeEntries);

        // Assert
        assertEquals(timeEntries.size(), conversion.getTimeEntries().size());
        assertTrue(conversion.getTimeEntries().containsAll(timeEntries));
    }

    @Test
    void addTimeEntry() {
        // Arrange
        Conversion conversion = new Conversion();
        TimeEntry timeEntry = new TimeEntry();

        // Act
        conversion.addTimeEntry(timeEntry);

        // Assert
        assertTrue(conversion.getTimeEntries().contains(timeEntry));
        assertTrue(timeEntry.getConversions().contains(conversion));
    }

    @Test
    void removeTimeEntry() {
        // Arrange
        Conversion conversion = new Conversion();
        TimeEntry timeEntry = new TimeEntry();
        conversion.addTimeEntry(timeEntry);

        // Act
        conversion.removeTimeEntry(timeEntry);

        // Assert
        assertFalse(conversion.getTimeEntries().contains(timeEntry));
        assertFalse(timeEntry.getConversions().contains(conversion));
    }
}
