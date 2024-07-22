package com.runtobeat.first.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MonthlyRecordRepository extends JpaRepository<com.runtobeat.first.entity.MonthlyRecord, String> {
}
