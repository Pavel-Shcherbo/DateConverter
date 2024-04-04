package org.date.dateconverter.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RequestCounterServiceTest {

    @Test
    void incrementAndGet() {
        // Arrange
        RequestCounterService requestCounterService = new RequestCounterService();

        // Act
        int initialValue = requestCounterService.incrementAndGet();
        int incrementedValue = requestCounterService.incrementAndGet();

        // Assert
        assertEquals(1, initialValue);
        assertEquals(2, incrementedValue);
    }
}
