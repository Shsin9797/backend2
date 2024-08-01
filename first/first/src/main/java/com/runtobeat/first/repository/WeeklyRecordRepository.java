package com.runtobeat.first.repository;

import com.runtobeat.first.entity.DailyRecord;
import com.runtobeat.first.entity.Member;
import com.runtobeat.first.entity.WeeklyRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WeeklyRecordRepository extends JpaRepository<WeeklyRecord, Long> {
    WeeklyRecord getByWeekYear(String weekYear);

    List<WeeklyRecord> findAllByMemberMemberId(Long memberId);

    WeeklyRecord findByMemberMemberIdAndWeekYear(Long memberId, String weekYear);
}
