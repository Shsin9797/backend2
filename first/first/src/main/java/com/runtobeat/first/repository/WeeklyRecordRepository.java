package com.runtobeat.first.repository;

import com.runtobeat.first.entity.WeeklyRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeeklyRecordRepository extends JpaRepository<com.runtobeat.first.entity.WeeklyRecord, String> {
    WeeklyRecord getByWeekYear(String weekYear);
}
