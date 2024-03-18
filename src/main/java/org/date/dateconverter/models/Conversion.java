package org.date.dateconverter.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;


@Entity
public class Conversion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String timeInCurrentTimeZone;
    private String timeInGMT;

    @ManyToOne
    @JoinColumn(name = "time_zone_id")
    private TimeZones timeZone;
    @ManyToMany
    @JoinTable(
            name = "conversion_time_entry",
            joinColumns = @JoinColumn(name = "conversion_id"),
            inverseJoinColumns = @JoinColumn(name = "time_entry_id")
    )
    private Set<TimeEntry> timeEntries = new HashSet<>();

    // Геттеры и сеттеры
    public void setTimeZone(TimeZones timeZone) {
        this.timeZone = timeZone;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTimeInCurrentTimeZone() {
        return timeInCurrentTimeZone;
    }

    public void setTimeInCurrentTimeZone(String timeInCurrentTimeZone) {
        this.timeInCurrentTimeZone = timeInCurrentTimeZone;
    }

    public String getTimeInGMT() {
        return timeInGMT;
    }

    public void setTimeInGMT(String timeInGMT) {
        this.timeInGMT = timeInGMT;
    }

    public Set<TimeEntry> getTimeEntries() {
        return timeEntries;
    }

    public void setTimeEntries(Set<TimeEntry> timeEntries) {
        this.timeEntries = timeEntries;
    }

    public void addTimeEntry(TimeEntry timeEntry) {
        timeEntries.add(timeEntry);
        timeEntry.getConversions().add(this);
    }

    public void removeTimeEntry(TimeEntry timeEntry) {
        timeEntries.remove(timeEntry);
        timeEntry.getConversions().remove(this);
    }
}
