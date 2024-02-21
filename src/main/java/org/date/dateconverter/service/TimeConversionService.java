package org.date.dateconverter.service;

import org.date.dateconverter.dto.TimeConversionDTO;
import org.date.dateconverter.repository.TimeConversionRepository;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TimeConversionService {

    private final TimeConversionRepository timeConversionRepository;

    public TimeConversionService(TimeConversionRepository timeConversionRepository) {
        this.timeConversionRepository = timeConversionRepository;
    }

    public TimeConversionDTO convertTime(long milliseconds) {
        Map<String, String> result = timeConversionRepository.convertTime(milliseconds);
        return new TimeConversionDTO(result.get("local_time"), result.get("gmt_time"));
    }
}
