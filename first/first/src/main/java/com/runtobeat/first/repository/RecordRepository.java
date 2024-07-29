package com.runtobeat.first.repository;

import com.runtobeat.first.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordRepository extends JpaRepository<Record, Long> {
}
