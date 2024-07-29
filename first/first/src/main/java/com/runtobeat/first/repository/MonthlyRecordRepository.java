package com.runtobeat.first.repository;

import com.runtobeat.first.entity.MonthlyRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonthlyRecordRepository extends JpaRepository<MonthlyRecord,Long> {
    MonthlyRecord getByMonthYears(String monthYears);
}
