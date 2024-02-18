package org.date.dateconverter.service;

import org.date.dateconverter.repository.TimeConversionRepository;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class TimeConversionService {

    private final TimeConversionRepository timeConversionRepository;

    public TimeConversionService(TimeConversionRepository timeConversionRepository) {
        this.timeConversionRepository = timeConversionRepository;
    }

    public Map<String, String> convertTime(long milliseconds) {
        return timeConversionRepository.convertTime(milliseconds);
    }
}
