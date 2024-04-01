package org.date.dateconverter.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimeConversionDTOTest {

    @Test
    void getLocalTime_ShouldReturnInitializedValue_WhenInitializedWithConstructor() {
        // Arrange
        String localTime = "2024-04-01 10:00:00";
        TimeConversionDTO dto = new TimeConversionDTO(localTime, "2024-04-01 15:00:00");

        // Act
        String result = dto.getLocalTime();

        // Assert
        assertEquals(localTime, result);
    }

    @Test
    void setLocalTime_ShouldSetLocalTime_WhenCalled() {
        // Arrange
        TimeConversionDTO dto = new TimeConversionDTO();
        String localTime = "2024-04-01 10:00:00";

        // Act
        dto.setLocalTime(localTime);

        // Assert
        assertEquals(localTime, dto.getLocalTime());
    }

    @Test
    void getGmtTime_ShouldReturnInitializedValue_WhenInitializedWithConstructor() {
        // Arrange
        String gmtTime = "2024-04-01 15:00:00";
        TimeConversionDTO dto = new TimeConversionDTO("2024-04-01 10:00:00", gmtTime);

        // Act
        String result = dto.getGmtTime();

        // Assert
        assertEquals(gmtTime, result);
    }

    @Test
    void setGmtTime_ShouldSetGmtTime_WhenCalled() {
        // Arrange
        TimeConversionDTO dto = new TimeConversionDTO();
        String gmtTime = "2024-04-01 15:00:00";

        // Act
        dto.setGmtTime(gmtTime);

        // Assert
        assertEquals(gmtTime, dto.getGmtTime());
    }
}
