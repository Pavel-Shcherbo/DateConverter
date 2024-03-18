package org.date.dateconverter.controller;

import org.date.dateconverter.service.CacheService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class CacheController {

    private final CacheService cacheService;

    public CacheController(CacheService cacheService) {
        this.cacheService = cacheService;
    }

    @GetMapping("/cache")
    public ResponseEntity<Map<String, Object>> getCacheContent() {
        Map<String, Object> cacheContent = cacheService.getAllEntries();
        return ResponseEntity.ok().body(cacheContent);
    }
}

