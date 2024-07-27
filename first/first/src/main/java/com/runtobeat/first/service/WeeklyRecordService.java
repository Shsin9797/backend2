package com.runtobeat.first.service;

import com.runtobeat.first.dto.RecordResponseDTO;
import com.runtobeat.first.dto.WeeklyRecordRequestDTO;
import com.runtobeat.first.entity.Record;
import com.runtobeat.first.entity.WeeklyRecord;
import com.runtobeat.first.repository.MemberRepository;
import com.runtobeat.first.repository.WeeklyRecordJDBCRepository;
import com.runtobeat.first.repository.WeeklyRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeeklyRecordService {

    private WeeklyRecordRepository weeklyRecordRepository;
    private WeeklyRecordJDBCRepository weeklyRecordJDBCRepository;
    private MemberRepository memberRepository;

    public WeeklyRecordService(WeeklyRecordRepository weeklyRecordRepository, WeeklyRecordJDBCRepository weeklyRecordJDBCRepository, MemberRepository memberRepository) {
        this.weeklyRecordRepository = weeklyRecordRepository;
        this.weeklyRecordJDBCRepository = weeklyRecordJDBCRepository;
        this.memberRepository = memberRepository;
    }

    public WeeklyRecord createWeeklyRecord(WeeklyRecordRequestDTO requestDTO) {
        WeeklyRecord weeklyRecord = new WeeklyRecord(
                memberRepository.findById(requestDTO.getMemberId()).get(),
                requestDTO.getWeeklyTotalDistance(),
                requestDTO.getWeeklyTotalTime(),
                requestDTO.getYearWeek(),
                requestDTO.getWeeklyRecordPace(),
                requestDTO.getWeeklyRunningStep(),
                requestDTO.getWeekYear()
        );
        return weeklyRecordRepository.save(weeklyRecord);
    }

    public WeeklyRecord getWeeklyRecordById(String id) {
        return weeklyRecordRepository.findById(id).orElseThrow(() -> new RuntimeException("Record not found"));
    }

    public List<WeeklyRecord> getAllWeeklyRecords() {
        return weeklyRecordRepository.findAll();
    }

    public WeeklyRecord updateWeeklyRecord(String id, WeeklyRecordRequestDTO requestDTO) {
        WeeklyRecord existingRecord = weeklyRecordRepository.findById(id).orElseThrow(() -> new RuntimeException("Record not found"));
        existingRecord.getMember().setMemberId(requestDTO.getMemberId());
        existingRecord.setWeeklyTotalDistance(requestDTO.getWeeklyTotalDistance());
        existingRecord.setWeeklyTotalTime(requestDTO.getWeeklyTotalTime());
        existingRecord.setYearWeek(requestDTO.getYearWeek());
        existingRecord.setWeeklyRecordPace(requestDTO.getWeeklyRecordPace());
        return weeklyRecordRepository.save(existingRecord);
    }

    public void updateWeeklyRecord(Record savedRecord) {
        weeklyRecordJDBCRepository.save(savedRecord);
    }

    public void deleteWeeklyRecord(String id) {
        weeklyRecordRepository.deleteById(id);
    }

    public Double getThisWeekAvgDistance() {
        return weeklyRecordJDBCRepository.getThisWeekAvgDistance();
    }
}
