package org.date.dateconverter.service;

import org.date.dateconverter.models.TimeZones;
import org.date.dateconverter.repository.TimeZonesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        timeZonesRepository.deleteById(id);
    }
}
