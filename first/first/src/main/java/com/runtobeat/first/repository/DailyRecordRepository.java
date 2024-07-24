package com.runtobeat.first.repository;

import com.runtobeat.first.entity.DailyRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface DailyRecordRepository extends JpaRepository<DailyRecord, String> {
}
