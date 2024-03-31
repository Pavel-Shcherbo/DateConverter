package org.date.dateconverterTest.serviceTest;

import org.date.dateconverter.models.Conversion;
import org.date.dateconverter.models.TimeZones;
import org.date.dateconverter.repository.TimeZonesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TimeZonesService {
    private final TimeZonesRepository timeZonesRepository;

    @Autowired
    public TimeZonesService(TimeZonesRepository timeZonesRepository) {
        this.timeZonesRepository = timeZonesRepository;
    }

    public TimeZones createTimeZone(TimeZones timeZone) {
        return timeZonesRepository.save(timeZone);
    }

    public List<TimeZones> getAllTimeZones() {
        return timeZonesRepository.findAll();
    }

    public Optional<TimeZones> getTimeZoneById(Long id) {
        return timeZonesRepository.findById(id);
    }

    public void updateTimeZone(TimeZones timeZone) {
        timeZonesRepository.save(timeZone);
    }

    public void deleteTimeZoneById(Long id) {
        TimeZones timeZone = timeZonesRepository.findById(id).orElseThrow();
        Set<Conversion> conversions = timeZone.getConversions();
        for (Conversion conversion : conversions) {
            conversion.setTimeZone(null);
        }
        timeZonesRepository.deleteById(id);
    }

}
