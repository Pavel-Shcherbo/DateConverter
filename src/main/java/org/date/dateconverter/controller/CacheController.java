package org.date.dateconverter.controller;

import lombok.extern.slf4j.Slf4j;
import org.date.dateconverter.service.CacheService;
import org.springframework.http.ResponseEntity;
import org.date.dateconverter.service.RequestCounterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Slf4j
public class CacheController {

    private final CacheService cacheService;
    private final RequestCounterService requestCounterService;

    public CacheController(CacheService cacheService, RequestCounterService requestCounterService) {
        this.cacheService = cacheService;
        this.requestCounterService = requestCounterService;
    }

    @GetMapping("/cache")
    public ResponseEntity<Map<String, Object>> getCacheContent() {
        log.info(String.valueOf(requestCounterService.incrementAndGet()));
        Map<String, Object> cacheContent = cacheService.getAllEntries();
        return ResponseEntity.ok().body(cacheContent);
    }
}

