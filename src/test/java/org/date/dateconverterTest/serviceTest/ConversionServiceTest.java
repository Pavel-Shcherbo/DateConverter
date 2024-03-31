package org.date.dateconverterTest.serviceTest;
import org.date.dateconverter.models.Conversion;
import org.date.dateconverter.models.TimeZones;
import org.date.dateconverter.repository.ConversionRepository;
import org.date.dateconverter.repository.TimeZonesRepository;
import org.date.dateconverter.service.ConversionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ConversionServiceTest {

    @Mock
    private ConversionRepository conversionRepository;

    @Mock
    private TimeZonesRepository timeZonesRepository;

    @InjectMocks
    private ConversionService conversionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateConversion() {
        Conversion conversion = new Conversion();
        when(conversionRepository.save(conversion)).thenReturn(conversion);

        Conversion savedConversion = conversionService.createConversion(conversion);

        assertEquals(conversion, savedConversion);
        verify(conversionRepository, times(1)).save(conversion);
    }

    @Test
    void testCreateConversionWithTimeZone() {
        Conversion conversion = new Conversion();
        String timeZone = "Europe/Berlin";
        TimeZones existingTimeZone = new TimeZones();
        existingTimeZone.setTimeZone(timeZone);
        when(timeZonesRepository.findByTimeZone(timeZone)).thenReturn(existingTimeZone);
        when(conversionRepository.save(conversion)).thenReturn(conversion);

        Conversion savedConversion = conversionService.createConversion(conversion, timeZone);

        assertEquals(conversion, savedConversion);
        verify(conversionRepository, times(1)).save(conversion);
    }

    @Test
    void testCreateConversionWithNewTimeZone() {
        Conversion conversion = new Conversion();
        String timeZone = "Europe/Berlin";
        when(timeZonesRepository.findByTimeZone(timeZone)).thenReturn(null);
        when(conversionRepository.save(conversion)).thenReturn(conversion);

        Conversion savedConversion = conversionService.createConversion(conversion, timeZone);

        assertEquals(conversion, savedConversion);
        verify(conversionRepository, times(1)).save(conversion);
        verify(timeZonesRepository, times(1)).save(any(TimeZones.class));
    }

    @Test
    void testGetAllConversions() {
        List<Conversion> conversions = new ArrayList<>();
        when(conversionRepository.findAll()).thenReturn(conversions);

        List<Conversion> allConversions = conversionService.getAllConversions();

        assertEquals(conversions, allConversions);
        verify(conversionRepository, times(1)).findAll();
    }

    @Test
    void testGetConversionById() {
        Long id = 1L;
        Conversion conversion = new Conversion();
        when(conversionRepository.findById(id)).thenReturn(Optional.of(conversion));

        Optional<Conversion> foundConversion = conversionService.getConversionById(id);

        assertEquals(Optional.of(conversion), foundConversion);
        verify(conversionRepository, times(1)).findById(id);
    }

    // Add tests for updateConversion, deleteConversionById, and getUsefulData methods
}
