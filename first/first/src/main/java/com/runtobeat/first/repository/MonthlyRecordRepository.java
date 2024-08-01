package com.runtobeat.first.repository;

import com.runtobeat.first.entity.Member;
import com.runtobeat.first.entity.MonthlyRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MonthlyRecordRepository extends JpaRepository<MonthlyRecord,Long> {
    MonthlyRecord getByMonthYears(String monthYears);

    List<MonthlyRecord> findAllByMemberMemberId(Long memberId);


    MonthlyRecord getByMemberAndMonthYears(Member member, String monthYear);
}
