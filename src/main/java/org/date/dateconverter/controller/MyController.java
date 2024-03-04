package org.date.dateconverter.controller;

import org.date.dateconverter.models.Conversion;
import org.date.dateconverter.models.TimeEntry;
import org.date.dateconverter.service.ConversionService;
import org.date.dateconverter.service.TimeDataService;
import org.date.dateconverter.service.TimeEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class MyController {

    private final ConversionService conversionService;
    private final TimeEntryService timeEntryService;
    private final TimeDataService timeDataService;

    @Autowired
    public MyController(ConversionService conversionService, TimeEntryService timeEntryService, TimeDataService timeDataService) {
        this.conversionService = conversionService;
        this.timeEntryService = timeEntryService;
        this.timeDataService = timeDataService;
    }

    // Endpoint для создания Conversion
    @PostMapping("/conversions")
    public ResponseEntity<Conversion> createConversion(@RequestBody Conversion conversion) {
        Conversion createdConversion = conversionService.createConversion(conversion);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdConversion);
    }

    // Endpoint для получения всех Conversion
    @GetMapping("/conversions")
    public ResponseEntity<List<Conversion>> getAllConversions() {
        List<Conversion> conversions = conversionService.getAllConversions();
        return ResponseEntity.ok().body(conversions);
    }

    // Endpoint для получения Conversion по ID
    @GetMapping("/conversions/{id}")
    public ResponseEntity<Conversion> getConversionById(@PathVariable Long id) {
        Optional<Conversion> conversion = conversionService.getConversionById(id);
        return conversion.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint для обновления Conversion
    @PutMapping("/conversions/{id}")
    public ResponseEntity<Void> updateConversion(@PathVariable Long id, @RequestBody Conversion conversion) {
        conversion.setId(id); // Устанавливаем ID для обновления
        conversionService.updateConversion(conversion);
        return ResponseEntity.ok().build();
    }

    // Endpoint для удаления Conversion по ID
    @DeleteMapping("/conversions/{id}")
    public ResponseEntity<Void> deleteConversionById(@PathVariable Long id) {
        conversionService.deleteConversionById(id);
        return ResponseEntity.ok().build();
    }


    // Endpoint для получения всех TimeEntry
    @GetMapping("/timeEntries")
    public ResponseEntity<List<TimeEntry>> getAllTimeEntries() {
        List<TimeEntry> timeEntries = timeEntryService.getAllTimeEntries();
        return ResponseEntity.ok().body(timeEntries);
    }

    // Endpoint для получения TimeEntry по ID
    @GetMapping("/timeEntries/{id}")
    public ResponseEntity<TimeEntry> getTimeEntryById(@PathVariable Long id) {
        Optional<TimeEntry> timeEntry = timeEntryService.getTimeEntryById(id);
        return timeEntry.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint для обновления TimeEntry
    @PutMapping("/timeEntries/{id}")
    public ResponseEntity<Void> updateTimeEntry(@PathVariable Long id, @RequestBody TimeEntry timeEntry) {
        timeEntry.setId(id); // Устанавливаем ID для обновления
        timeEntryService.updateTimeEntry(timeEntry);
        return ResponseEntity.ok().build();
    }

    // Endpoint для удаления TimeEntry по ID
    @DeleteMapping( "/timeEntries/{id}")
    public ResponseEntity<Void> deleteTimeEntryById(@PathVariable Long id) {
        timeEntryService.deleteTimeEntryById(id);
        return ResponseEntity.ok().build();
    }


}
