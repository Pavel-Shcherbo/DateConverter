package org.date.dateconverter.controller;

import lombok.extern.slf4j.Slf4j;
import org.date.dateconverter.models.Conversion;
import org.date.dateconverter.models.TimeEntry;
import org.date.dateconverter.models.TimeZones;
import org.date.dateconverter.service.ConversionService;
import org.date.dateconverter.service.RequestCounterService;
import org.date.dateconverter.service.TimeEntryService;
import org.date.dateconverter.service.TimeZonesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class DateController {

    private final ConversionService conversionService;
    private final TimeEntryService timeEntryService;
    private final TimeZonesService timeZonesService;
    private final RequestCounterService requestCounterService;


    @Autowired
    public DateController(ConversionService conversionService, TimeEntryService timeEntryService, TimeZonesService timeZonesService, RequestCounterService requestCounterService) {
        this.conversionService = conversionService;
        this.timeEntryService = timeEntryService;
        this.timeZonesService = timeZonesService;

        this.requestCounterService = requestCounterService;
    }

    // Endpoint для создания Conversion
    @PostMapping("/conversions")
    public ResponseEntity<Conversion> createConversion(@RequestBody Conversion conversion) {
        log.info(String.valueOf(requestCounterService.incrementAndGet()));
        Conversion createdConversion = conversionService.createConversion(conversion);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdConversion);
    }

    // Endpoint для получения всех Conversion
    @GetMapping("/conversions")
    public ResponseEntity<List<Conversion>> getAllConversions() {
        log.info(String.valueOf(requestCounterService.incrementAndGet()));
        List<Conversion> conversions = conversionService.getAllConversions();
        return ResponseEntity.ok().body(conversions);
    }

    // Endpoint для получения Conversion по ID
    @GetMapping("/conversions/{id}")
    public ResponseEntity<Conversion> getConversionById(@PathVariable Long id) {
        log.info(String.valueOf(requestCounterService.incrementAndGet()));
        Optional<Conversion> conversion = conversionService.getConversionById(id);
        return conversion.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint для обновления Conversion
    @PutMapping("/conversions/{id}")
    public ResponseEntity<Void> updateConversion(@PathVariable Long id, @RequestBody Conversion conversion) {
        log.info(String.valueOf(requestCounterService.incrementAndGet()));
        conversion.setId(id); // Устанавливаем ID для обновления
        conversionService.updateConversion(conversion);
        return ResponseEntity.ok().build();
    }

    // Endpoint для удаления Conversion по ID
    @DeleteMapping("/conversions/{id}")
    public ResponseEntity<Void> deleteConversionById(@PathVariable Long id) {
        log.info(String.valueOf(requestCounterService.incrementAndGet()));
        conversionService.deleteConversionById(id);
        return ResponseEntity.ok().build();
    }


    // Endpoint для получения всех TimeEntry
    @GetMapping("/timeEntries")
    public ResponseEntity<List<TimeEntry>> getAllTimeEntries() {
        log.info(String.valueOf(requestCounterService.incrementAndGet()));
        List<TimeEntry> timeEntries = timeEntryService.getAllTimeEntries();
        return ResponseEntity.ok().body(timeEntries);
    }

    // Endpoint для получения TimeEntry по ID
    @GetMapping("/timeEntries/{id}")
    public ResponseEntity<TimeEntry> getTimeEntryById(@PathVariable Long id) {
        log.info(String.valueOf(requestCounterService.incrementAndGet()));
        Optional<TimeEntry> timeEntry = timeEntryService.getTimeEntryById(id);
        return timeEntry.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint для обновления TimeEntry
    @PutMapping("/timeEntries/{id}")
    public ResponseEntity<Void> updateTimeEntry(@PathVariable Long id, @RequestBody TimeEntry timeEntry) {
        log.info(String.valueOf(requestCounterService.incrementAndGet()));
        timeEntry.setId(id); // Устанавливаем ID для обновления
        timeEntryService.updateTimeEntry(timeEntry);
        return ResponseEntity.ok().build();
    }

    // Endpoint для удаления TimeEntry по ID
    @DeleteMapping("/timeEntries/{id}")
    public ResponseEntity<Void> deleteTimeEntryById(@PathVariable Long id) {
        log.info(String.valueOf(requestCounterService.incrementAndGet()));
        timeEntryService.deleteTimeEntryById(id);
        return ResponseEntity.ok().build();
    }

    // Эндпоинт для создания TimeZone
    @PostMapping("/timeZones")
    public ResponseEntity<TimeZones> createTimeZone(@RequestBody TimeZones timeZone) {
        log.info(String.valueOf(requestCounterService.incrementAndGet()));
        TimeZones createdTimeZone = timeZonesService.createTimeZone(timeZone);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTimeZone);
    }

    // Эндпоинт для получения всех TimeZones
    @GetMapping("/timeZones")
    public ResponseEntity<List<TimeZones>> getAllTimeZones() {
        log.info(String.valueOf(requestCounterService.incrementAndGet()));
        List<TimeZones> timeZones = timeZonesService.getAllTimeZones();
        return ResponseEntity.ok().body(timeZones);
    }

    // Эндпоинт для получения TimeZone по ID
    @GetMapping("/timeZones/{id}")
    public ResponseEntity<TimeZones> getTimeZoneById(@PathVariable Long id) {
        log.info(String.valueOf(requestCounterService.incrementAndGet()));
        Optional<TimeZones> timeZone = timeZonesService.getTimeZoneById(id);
        return timeZone.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Эндпоинт для обновления TimeZone
    @PutMapping("/timeZones/{id}")
    public ResponseEntity<Void> updateTimeZone(@PathVariable Long id, @RequestBody TimeZones timeZone) {
        log.info(String.valueOf(requestCounterService.incrementAndGet()));
        timeZone.setId(id); // Устанавливаем ID для обновления
        timeZonesService.updateTimeZone(timeZone);
        return ResponseEntity.ok().build();
    }

    // Эндпоинт для удаления TimeZone по ID
    @DeleteMapping("/timeZones/{id}")
    public ResponseEntity<Void> deleteTimeZoneById(@PathVariable Long id) {
        log.info(String.valueOf(requestCounterService.incrementAndGet()));
        timeZonesService.deleteTimeZoneById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/TimeZoneIdAndTimeEntry")
    public ResponseEntity<List<Conversion>> getUsefulData(

            @RequestParam(name = "timeZoneId") Long timeZoneId,
            @RequestParam(name = "timeEntryId") Long timeEntryId) {
        log.info(String.valueOf(requestCounterService.incrementAndGet()));
        if (timeZoneId == null || timeEntryId == null) {
            throw new IllegalArgumentException("Invalid timeZoneId or timeEntryId");
        }

        // Вызываем метод сервиса, который выполняет кастомный запрос
        List<Conversion> usefulData = conversionService.getUsefulData(timeZoneId, timeEntryId);

        // Проверяем результат и возвращаем соответствующий ResponseEntity
        if (usefulData != null && !usefulData.isEmpty()) {
            return ResponseEntity.ok(usefulData);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/conversions/bulk")
    public ResponseEntity<List<Conversion>> createBulkConversions(@RequestBody List<Conversion> conversions) {
        log.info(String.valueOf(requestCounterService.incrementAndGet()));
        List<Conversion> createdConversions = conversions.stream()
                .map(conversionService::createConversion)
                .toList();
        return ResponseEntity.status(HttpStatus.CREATED).body(createdConversions);
    }
}

