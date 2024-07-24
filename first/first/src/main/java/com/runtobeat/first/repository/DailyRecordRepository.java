package com.runtobeat.first.repository;

import com.runtobeat.first.entity.DailyRecord;
import com.runtobeat.first.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DailyRecordRepository extends JpaRepository<DailyRecord, String> {
    public DailyRecord findByMemberId(String memberId);

    List<DailyRecord> findAllByMemberId(String memberId);
}
