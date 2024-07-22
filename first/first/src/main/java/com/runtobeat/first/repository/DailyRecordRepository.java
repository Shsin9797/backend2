package com.runtobeat.first.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyRecordRepository extends JpaRepository<Record, String> {
}
