package org.date.dateconverter.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "time_zone")
public class TimeZones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String timeZone;

    @OneToMany(mappedBy = "timeZone")
    private Set<Conversion> conversions = new HashSet<>();

    // Геттеры и сеттеры

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public Set<Conversion> getConversions() {
        return conversions;
    }

    public void setConversions(Set<Conversion> conversions) {
        this.conversions = conversions;
    }
}
