package org.date.dateconverter.models;

import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

class TimeEntryTest {

    @Test
    void getId() {
        // Arrange
        Long id = 1L;
        TimeEntry timeEntry = new TimeEntry();
        timeEntry.setId(id);

        // Act
        Long retrievedId = timeEntry.getId();

        // Assert
        assertEquals(id, retrievedId);
    }

    @Test
    void setId() {
        // Arrange
        Long id = 1L;
        TimeEntry timeEntry = new TimeEntry();

        // Act
        timeEntry.setId(id);

        // Assert
        assertEquals(id, timeEntry.getId());
    }

    @Test
    void getMilliseconds() {
        // Arrange
        long milliseconds = 1617325533000L;
        TimeEntry timeEntry = new TimeEntry();
        timeEntry.setMilliseconds(milliseconds);

        // Act
        long retrievedMilliseconds = timeEntry.getMilliseconds();

        // Assert
        assertEquals(milliseconds, retrievedMilliseconds);
    }

    @Test
    void setMilliseconds() {
        // Arrange
        long milliseconds = 1617325533000L;
        TimeEntry timeEntry = new TimeEntry();

        // Act
        timeEntry.setMilliseconds(milliseconds);

        // Assert
        assertEquals(milliseconds, timeEntry.getMilliseconds());
    }

    @Test
    void getConversions() {
        // Arrange
        Set<Conversion> conversions = new HashSet<>();
        conversions.add(new Conversion());
        conversions.add(new Conversion());
        TimeEntry timeEntry = new TimeEntry();
        timeEntry.setConversions(conversions);

        // Act
        Set<Conversion> retrievedConversions = timeEntry.getConversions();

        // Assert
        assertEquals(conversions.size(), retrievedConversions.size());
        assertTrue(retrievedConversions.containsAll(conversions));
    }

    @Test
    void setConversions() {
        // Arrange
        Set<Conversion> conversions = new HashSet<>();
        conversions.add(new Conversion());
        conversions.add(new Conversion());
        TimeEntry timeEntry = new TimeEntry();

        // Act
        timeEntry.setConversions(conversions);

        // Assert
        assertEquals(conversions.size(), timeEntry.getConversions().size());
        assertTrue(timeEntry.getConversions().containsAll(conversions));
    }
}
