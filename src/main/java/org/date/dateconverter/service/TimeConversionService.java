package org.date.dateconverter.service;

import org.date.dateconverter.dto.TimeConversionDTO;
import org.date.dateconverter.models.Conversion;
import org.date.dateconverter.models.TimeEntry;
import org.date.dateconverter.models.TimeZones;
import org.date.dateconverter.repository.ConversionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Optional;

@Service
public class TimeConversionService {

    private final ConversionRepository conversionRepository;

    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String LOCAL_TIME_KEY = "local_time";
    private static final String GMT_TIME_KEY = "gmt_time";

    @Autowired
    public TimeConversionService(ConversionRepository conversionRepository,
                                 TimeEntryService timeEntryService) {
        this.conversionRepository = conversionRepository;
    }

    @Transactional
    public TimeConversionDTO convertTime(long milliseconds, TimeEntry timeEntry, TimeZones timeZone) {
        Map<String, String> result = convertTimeToString(milliseconds);
        Conversion conversion = saveConversionResult(result, timeEntry, timeZone);
        return new TimeConversionDTO(conversion.getTimeInCurrentTimeZone(), conversion.getTimeInGMT());
    }

    private Map<String, String> convertTimeToString(long milliseconds) {
        Instant instant = Instant.ofEpochMilli(milliseconds);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        LocalDateTime gmtDateTime = LocalDateTime.ofInstant(instant, ZoneId.of("GMT"));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
        return Map.of(
                LOCAL_TIME_KEY, localDateTime.format(formatter),
                GMT_TIME_KEY, gmtDateTime.format(formatter)
        );
    }

    private Conversion saveConversionResult(Map<String, String> result, TimeEntry timeEntry, TimeZones timeZone) {

        Conversion conversion = new Conversion();
        conversion.setTimeInCurrentTimeZone(result.get(LOCAL_TIME_KEY));
        conversion.setTimeInGMT(result.get(GMT_TIME_KEY));

        // Связываем результат конверсии с TimeEntry
        conversion.addTimeEntry(timeEntry);

        // Устанавливаем связь с TimeZones
        conversion.setTimeZone(timeZone);

        return conversionRepository.save(conversion);
    }

    @Transactional(readOnly = true)
    public TimeConversionDTO getTimeConversionById(Long id) {
        Optional<Conversion> optionalConversion = conversionRepository.findById(id);
        if (optionalConversion.isPresent()) {
            Conversion conversion = optionalConversion.get();
            return new TimeConversionDTO(conversion.getTimeInCurrentTimeZone(), conversion.getTimeInGMT());
        } else {
            return new TimeConversionDTO(null, null);
        }
    }

    @Transactional
    public TimeConversionDTO updateTimeConversion(Long id, long milliseconds, TimeEntry timeEntry, TimeZones timeZone) {
        Optional<Conversion> optionalConversion = conversionRepository.findById(id);
        if (optionalConversion.isPresent()) {
            Map<String, String> result = convertTimeToString(milliseconds);
            Conversion conversion = optionalConversion.get();
            conversion.setTimeInCurrentTimeZone(result.get(LOCAL_TIME_KEY));
            conversion.setTimeInGMT(result.get(GMT_TIME_KEY));
            conversion.addTimeEntry(timeEntry);
            conversion.setTimeZone(timeZone);
            return new TimeConversionDTO(conversion.getTimeInCurrentTimeZone(), conversion.getTimeInGMT());
        } else {
            return new TimeConversionDTO(null, null);
        }
    }

    @Transactional
    public void deleteTimeConversion(Long id) {
        conversionRepository.deleteById(id);
    }
}
