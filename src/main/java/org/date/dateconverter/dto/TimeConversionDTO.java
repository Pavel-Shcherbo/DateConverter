package org.date.dateconverter.dto;

public class TimeConversionDTO {

    private String localTime;
    private String gmtTime;

    public TimeConversionDTO() {
    }

    public TimeConversionDTO(String localTime, String gmtTime) {
        this.localTime = localTime;
        this.gmtTime = gmtTime;
    }

    public String getLocalTime() {
        return localTime;
    }

    public void setLocalTime(String localTime) {
        this.localTime = localTime;
    }

    public String getGmtTime() {
        return gmtTime;
    }

    public void setGmtTime(String gmtTime) {
        this.gmtTime = gmtTime;
    }
}
