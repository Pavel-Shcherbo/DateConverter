package org.date.dateconverter.models;

import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

class TimeZonesTest {

    @Test
    void getId() {
        // Arrange
        Long id = 1L;
        TimeZones timeZones = new TimeZones();
        timeZones.setId(id);

        // Act
        Long retrievedId = timeZones.getId();

        // Assert
        assertEquals(id, retrievedId);
    }

    @Test
    void setId() {
        // Arrange
        Long id = 1L;
        TimeZones timeZones = new TimeZones();

        // Act
        timeZones.setId(id);

        // Assert
        assertEquals(id, timeZones.getId());
    }

    @Test
    void getTimeZone() {
        // Arrange
        String timeZone = "Europe/London";
        TimeZones timeZones = new TimeZones();
        timeZones.setTimeZone(timeZone);

        // Act
        String retrievedTimeZone = timeZones.getTimeZone();

        // Assert
        assertEquals(timeZone, retrievedTimeZone);
    }

    @Test
    void setTimeZone() {
        // Arrange
        String timeZone = "Europe/London";
        TimeZones timeZones = new TimeZones();

        // Act
        timeZones.setTimeZone(timeZone);

        // Assert
        assertEquals(timeZone, timeZones.getTimeZone());
    }

    @Test
    void getConversions() {
        // Arrange
        Set<Conversion> conversions = new HashSet<>();
        conversions.add(new Conversion());
        conversions.add(new Conversion());
        TimeZones timeZones = new TimeZones();
        timeZones.setConversions(conversions);

        // Act
        Set<Conversion> retrievedConversions = timeZones.getConversions();

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
        TimeZones timeZones = new TimeZones();

        // Act
        timeZones.setConversions(conversions);

        // Assert
        assertEquals(conversions.size(), timeZones.getConversions().size());
        assertTrue(timeZones.getConversions().containsAll(conversions));
    }
}
