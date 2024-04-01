package org.date.dateconverter.service;

import org.date.dateconverter.dto.TimeConversionDTO;
import org.date.dateconverter.models.Conversion;
import org.date.dateconverter.models.TimeEntry;
import org.date.dateconverter.models.TimeZones;
import org.date.dateconverter.repository.ConversionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class TimeConversionServiceTest {

    @Mock
    private ConversionRepository conversionRepository;

    @InjectMocks
    private TimeConversionService timeConversionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void convertTime() {
        // Arrange
        long milliseconds = 1617325533000L;
        TimeEntry timeEntry = new TimeEntry();
        TimeZones timeZone = new TimeZones();

        Map<String, String> expectedResult = new HashMap<>();
        expectedResult.put("local_time", "2022-04-01 15:18:53");
        expectedResult.put("gmt_time", "2022-04-01 10:18:53");

        Conversion savedConversion = new Conversion();
        savedConversion.setTimeInCurrentTimeZone(expectedResult.get("local_time"));
        savedConversion.setTimeInGMT(expectedResult.get("gmt_time"));
        savedConversion.addTimeEntry(timeEntry);
        savedConversion.setTimeZone(timeZone);

        when(conversionRepository.save(any())).thenReturn(savedConversion);

        // Act
        TimeConversionDTO result = timeConversionService.convertTime(milliseconds, timeEntry, timeZone);

        // Assert
        assertEquals(expectedResult.get("local_time"), result.getLocalTime());
        assertEquals(expectedResult.get("gmt_time"), result.getGmtTime());
        verify(conversionRepository, times(1)).save(any());
    }

    @Test
    void getTimeConversionById() {
        // Arrange
        long id = 1L;
        String localTime = "2022-04-01 15:18:53";
        String gmtTime = "2022-04-01 10:18:53";

        Conversion conversion = new Conversion();
        conversion.setId(id);
        conversion.setTimeInCurrentTimeZone(localTime);
        conversion.setTimeInGMT(gmtTime);

        when(conversionRepository.findById(id)).thenReturn(Optional.of(conversion));

        // Act
        TimeConversionDTO result = timeConversionService.getTimeConversionById(id);

        // Assert
        assertEquals(localTime, result.getLocalTime());
        assertEquals(gmtTime, result.getGmtTime());
    }

    @Test
    void deleteTimeConversion() {
        // Arrange
        long id = 1L;

        // Act
        timeConversionService.deleteTimeConversion(id);

        // Assert
        verify(conversionRepository, times(1)).deleteById(id);
    }

    private Map<String, String> convertTimeToString(long milliseconds) {
        Instant instant = Instant.ofEpochMilli(milliseconds);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        LocalDateTime gmtDateTime = LocalDateTime.ofInstant(instant, ZoneId.of("GMT"));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return Map.of(
                "local_time", localDateTime.format(formatter),
                "gmt_time", gmtDateTime.format(formatter)
        );
    }
}
