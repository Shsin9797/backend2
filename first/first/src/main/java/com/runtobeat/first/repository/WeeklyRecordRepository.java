package com.runtobeat.first.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WeeklyRecordRepository extends JpaRepository<com.runtobeat.first.entity.WeeklyRecord, String> {
}
