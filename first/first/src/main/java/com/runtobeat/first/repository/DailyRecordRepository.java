package com.runtobeat.first.repository;

import com.runtobeat.first.entity.DailyRecord;
import com.runtobeat.first.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;


import java.time.LocalDate;
import java.util.List;

public interface DailyRecordRepository extends JpaRepository<DailyRecord, Long> {
    DailyRecord findByMemberMemberId(Long memberId);

    List<DailyRecord> findAllByMemberMemberId(Long memberId);

    DailyRecord getByYearMonthDate(LocalDate recordDate);

    DailyRecord findByMemberAndYearMonthDate(Member member, LocalDate recordDate);
}
