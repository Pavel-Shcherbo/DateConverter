package org.date.dateconverter.service;

import org.date.dateconverter.dto.TimeConversionDTO;
import org.date.dateconverter.models.Conversion;
import org.date.dateconverter.models.TimeEntry;
import org.date.dateconverter.repository.ConversionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Service
public class TimeConversionService {

    private final ConversionRepository conversionRepository;
    private final TimeEntryService timeEntryService;

    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String LOCAL_TIME_KEY = "local_time";
    private static final String GMT_TIME_KEY = "gmt_time";

    @Autowired
    public TimeConversionService(ConversionRepository conversionRepository,
                                 TimeEntryService timeEntryService) {
        this.conversionRepository = conversionRepository;
        this.timeEntryService = timeEntryService;
    }

    @Transactional
    public TimeConversionDTO convertTime(long milliseconds) {
        Map<String, String> result = convertTimeToString(milliseconds);
        Conversion conversion = saveConversionResult(milliseconds, result);
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

    private Conversion saveConversionResult(long milliseconds, Map<String, String> result) {
        TimeEntry timeEntry = timeEntryService.createTimeEntry(milliseconds);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
        LocalDateTime localDateTime = LocalDateTime.parse(result.get(LOCAL_TIME_KEY), formatter);
        LocalDateTime gmtDateTime = LocalDateTime.parse(result.get(GMT_TIME_KEY), formatter);


        Conversion conversion = new Conversion();
        conversion.setTimeInCurrentTimeZone(result.get(LOCAL_TIME_KEY));
        conversion.setTimeInGMT(result.get(GMT_TIME_KEY));

        conversion.addTimeEntry(timeEntry);

        return conversionRepository.save(conversion);
    }
}
