package org.date.dateconverter.service;

import org.date.dateconverter.models.Conversion;
import org.date.dateconverter.models.TimeZones;
import org.date.dateconverter.repository.ConversionRepository;
import org.date.dateconverter.repository.TimeZonesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConversionService {

    private final ConversionRepository conversionRepository;
    private final TimeZonesRepository timeZonesRepository;


    @Autowired
    public ConversionService(ConversionRepository conversionRepository, TimeZonesRepository timeZonesRepository) {
        this.conversionRepository = conversionRepository;
        this.timeZonesRepository = timeZonesRepository;
    }
    public Conversion createConversion(Conversion conversion, Long timeZoneId) {
        TimeZones timeZone = timeZonesRepository.findById(timeZoneId).orElseThrow(() -> new IllegalArgumentException("Invalid time zone Id"));
        conversion.setTimeZone(timeZone);
        return conversionRepository.save(conversion);
    }
    public List<Conversion> getAllConversions() {
        return conversionRepository.findAll();
    }

    public Optional<Conversion> getConversionById(Long id) {
        return conversionRepository.findById(id);
    }

    public Conversion createConversion(Conversion conversion) {
        return conversionRepository.save(conversion);
    }

    public void updateConversion(Conversion conversion) {
        conversionRepository.save(conversion);
    }

    public void deleteConversionById(Long id) {
        conversionRepository.deleteById(id);
    }
}
