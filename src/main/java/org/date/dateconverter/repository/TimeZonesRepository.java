package org.date.dateconverter.repository;

import org.date.dateconverter.models.TimeZones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeZonesRepository extends JpaRepository<TimeZones, Long> {
    TimeZones findByTimeZone(String timeZone);
}