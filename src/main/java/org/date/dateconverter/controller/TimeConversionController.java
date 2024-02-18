package org.date.dateconverter.controller;

import org.date.dateconverter.service.TimeConversionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
public class TimeConversionController {

    private final TimeConversionService timeConversionService;

    public TimeConversionController(TimeConversionService timeConversionService) {
        this.timeConversionService = timeConversionService;
    }

    @GetMapping("/convert")
    public Map<String, String> convertTime(@RequestParam long milliseconds) {
        return timeConversionService.convertTime(milliseconds);
    }
}
