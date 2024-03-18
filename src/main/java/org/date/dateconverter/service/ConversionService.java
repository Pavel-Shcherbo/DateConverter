package org.date.dateconverter.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
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
    @PersistenceContext
    private EntityManager entityManager;
    private final ConversionRepository conversionRepository;
    private final TimeZonesRepository timeZonesRepository;

    @Autowired
    public ConversionService(ConversionRepository conversionRepository, TimeZonesRepository timeZonesRepository) {
        this.conversionRepository = conversionRepository;
        this.timeZonesRepository = timeZonesRepository;
    }

    public Conversion createConversion(Conversion conversion) {
        return conversionRepository.save(conversion);
    }

    public Conversion createConversion(Conversion conversion, String timeZone) {
        TimeZones existingTimeZone = timeZonesRepository.findByTimeZone(timeZone);
        if (existingTimeZone == null) {
            existingTimeZone = new TimeZones();
            existingTimeZone.setTimeZone(timeZone);
            existingTimeZone = timeZonesRepository.save(existingTimeZone);
        }
        conversion.setTimeZone(existingTimeZone); // Установка связи между Conversion и TimeZones
        return conversionRepository.save(conversion);
    }


    public List<Conversion> getAllConversions() {
        return conversionRepository.findAll();
    }

    public Optional<Conversion> getConversionById(Long id) {
        return conversionRepository.findById(id);
    }

    public void updateConversion(Conversion conversion) {
        conversionRepository.save(conversion);
    }

    public void deleteConversionById(Long id) {
        conversionRepository.deleteById(id);
    }

    public List<Conversion> getUsefulData(Long timeZoneId, Long timeEntryId) {
        String queryString = "SELECT c FROM Conversion c " +
                "JOIN c.timeZone tz " +
                "JOIN c.timeEntries te " +
                "WHERE tz.id = :timeZoneId " +
                "AND te.id = :timeEntryId";
        return entityManager.createQuery(queryString, Conversion.class)
                .setParameter("timeZoneId", timeZoneId)
                .setParameter("timeEntryId", timeEntryId)
                .getResultList();
    }
}
