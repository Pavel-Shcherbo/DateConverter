package org.date.dateconverter.service;

import org.date.dateconverter.models.Conversion;
import org.date.dateconverter.models.TimeZones;
import org.date.dateconverter.repository.ConversionRepository;
import org.date.dateconverter.repository.TimeZonesRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class ConversionServiceTest {

    @Mock
    private ConversionRepository conversionRepository;

    @Mock
    private TimeZonesRepository timeZonesRepository;

    @InjectMocks
    private ConversionService conversionService;

    public ConversionServiceTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void createConversion() {
        Conversion conversion = new Conversion();
        when(conversionRepository.save(any(Conversion.class))).thenReturn(conversion);

        Conversion savedConversion = conversionService.createConversion(conversion);

        assertEquals(conversion, savedConversion);
        verify(conversionRepository, times(1)).save(conversion);
    }

    @Test
    void createConversion_WithTimeZone() {
        Conversion conversion = new Conversion();
        TimeZones timeZone = new TimeZones();
        when(timeZonesRepository.findByTimeZone(anyString())).thenReturn(timeZone);
        when(conversionRepository.save(any(Conversion.class))).thenReturn(conversion);

        Conversion savedConversion = conversionService.createConversion(conversion, "GMT");

        assertEquals(conversion, savedConversion);
        verify(conversionRepository, times(1)).save(conversion);
        verify(timeZonesRepository, times(1)).findByTimeZone("GMT");
        verify(timeZonesRepository, never()).save(any(TimeZones.class));
    }

    @Test
    void getAllConversions() {
        List<Conversion> conversions = Collections.singletonList(new Conversion());
        when(conversionRepository.findAll()).thenReturn(conversions);

        List<Conversion> result = conversionService.getAllConversions();

        assertEquals(conversions, result);
        verify(conversionRepository, times(1)).findAll();
    }

    @Test
    void getConversionById() {
        Conversion conversion = new Conversion();
        when(conversionRepository.findById(anyLong())).thenReturn(Optional.of(conversion));

        Optional<Conversion> result = conversionService.getConversionById(1L);

        assertEquals(Optional.of(conversion), result);
        verify(conversionRepository, times(1)).findById(1L);
    }

    @Test
    void updateConversion() {
        Conversion conversion = new Conversion();

        conversionService.updateConversion(conversion);

        verify(conversionRepository, times(1)).save(conversion);
    }

    @Test
    void deleteConversionById() {
        conversionService.deleteConversionById(1L);

        verify(conversionRepository, times(1)).deleteById(1L);
    }

    // Testing getUsefulData() method requires more complex setup and is left as an exercise
    // Depending on the underlying framework (e.g., Spring), integration tests might be more appropriate
}
