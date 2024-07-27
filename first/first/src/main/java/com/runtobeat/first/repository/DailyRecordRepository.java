package com.runtobeat.first.repository;

import com.runtobeat.first.entity.DailyRecord;
import org.springframework.data.jpa.repository.JpaRepository;


import java.time.LocalDate;
import java.util.List;

public interface DailyRecordRepository extends JpaRepository<DailyRecord, String> {
    DailyRecord findByMemberMemberId(String memberId);

    List<DailyRecord> findAllByMemberMemberId(String memberId);

    DailyRecord getByYearMonthDate(LocalDate recordDate);
}
