package org.date.dateconverter.controller;

import org.date.dateconverter.dto.TimeConversionDTO;
import org.date.dateconverter.models.TimeEntry;
import org.date.dateconverter.models.TimeZones;
import org.date.dateconverter.repository.TimeZonesRepository;
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
    private final TimeZonesRepository timeZonesRepository; // Добавляем репозиторий для доступа к таблице TimeZones

    public TimeConversionController(TimeConversionService timeConversionService, TimeEntryService timeEntryService, TimeZonesRepository timeZonesRepository) {
        this.timeConversionService = timeConversionService;
        this.timeEntryService = timeEntryService;
        this.timeZonesRepository = timeZonesRepository;
    }

    @GetMapping("/convert")
    public ResponseEntity<TimeConversionDTO> convertTime(@RequestParam long milliseconds) {
        // Создание объекта TimeEntry
        TimeEntry timeEntry = timeEntryService.createTimeEntry(milliseconds);

        // Получение текущего часового пояса в момент запроса
        String timeZone = getTimeZone();

        // Создание объекта TimeZones или получение существующего по часовому поясу
        TimeZones existingTimeZone = timeZonesRepository.findByTimeZone(timeZone);
        if (existingTimeZone == null) {
            existingTimeZone = new TimeZones();
            existingTimeZone.setTimeZone(timeZone);
            existingTimeZone = timeZonesRepository.save(existingTimeZone);
        }

        // Вызов сервиса для конвертации времени
        TimeConversionDTO result = timeConversionService.convertTime(milliseconds, timeEntry, existingTimeZone);

        return ResponseEntity.ok().body(result);
    }

    // Метод для получения текущего часового пояса в момент запроса
    private String getTimeZone() {
        TimeZone timeZone = TimeZone.getDefault();
        return timeZone.getID();
    }
}
