package org.date.dateconverter.controller;

import org.date.dateconverter.dto.TimeConversionDTO;
import org.date.dateconverter.models.TimeEntry;
import org.date.dateconverter.models.TimeZones;
import org.date.dateconverter.repository.TimeZonesRepository;
import org.date.dateconverter.service.CacheService;
import org.date.dateconverter.service.TimeConversionService;
import org.date.dateconverter.service.TimeEntryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.TimeZone;

@RestController
public class TimeConversionController {
    private final TimeConversionService timeConversionService;
    private final TimeEntryService timeEntryService;
    private final TimeZonesRepository timeZonesRepository;
    private final CacheService cacheService;

    public TimeConversionController(TimeConversionService timeConversionService, TimeEntryService timeEntryService, TimeZonesRepository timeZonesRepository, CacheService cacheService) {
        this.timeConversionService = timeConversionService;
        this.timeEntryService = timeEntryService;
        this.timeZonesRepository = timeZonesRepository;
        this.cacheService = cacheService;
    }

    @GetMapping("/convert")

    public ResponseEntity<TimeConversionDTO> convertTime(@RequestParam long milliseconds) {
        String cacheKey = "conversion_" + milliseconds;
        if (cacheService.containsKey(cacheKey)) {

            TimeConversionDTO cachedResult = (TimeConversionDTO) cacheService.get(cacheKey);
            return ResponseEntity.ok().body(cachedResult);
        }

        TimeEntry timeEntry = timeEntryService.createTimeEntry(milliseconds);

        String timeZone = getTimeZone();

        TimeZones existingTimeZone = timeZonesRepository.findByTimeZone(timeZone);
        if (existingTimeZone == null) {
            existingTimeZone = new TimeZones();
            existingTimeZone.setTimeZone(timeZone);
            existingTimeZone = timeZonesRepository.save(existingTimeZone);
        }

        TimeConversionDTO result = timeConversionService.convertTime(milliseconds, timeEntry, existingTimeZone);

        cacheService.put(cacheKey, result);

        return ResponseEntity.ok().body(result);
    }

    // Метод для получения текущего часового пояса в момент запроса
    public String getTimeZone() {
        TimeZone timeZone = TimeZone.getDefault();
        return timeZone.getID();
    }
}
