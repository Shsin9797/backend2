package com.runtobeat.first.service;

import com.runtobeat.first.dto.DailyRecordResponseDTO;
import com.runtobeat.first.dto.MonthlyRecordRequestDTO;
import com.runtobeat.first.dto.MonthlyRecordResponseDTO;
import com.runtobeat.first.entity.DailyRecord;
import com.runtobeat.first.entity.MonthlyRecord;
import com.runtobeat.first.entity.Record;
import com.runtobeat.first.repository.MemberRepository;
import com.runtobeat.first.repository.MonthlyRecordJDBCRepository;
import com.runtobeat.first.repository.MonthlyRecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonthlyRecordService {

    private MonthlyRecordRepository monthlyRecordRepository;
    private MonthlyRecordJDBCRepository monthlyRecordJDBCRepository;
    private MemberRepository memberRepository;

    public MonthlyRecordService(MonthlyRecordRepository monthlyRecordRepository, MonthlyRecordJDBCRepository monthlyRecordJDBCRepository, MemberRepository memberRepository) {
        this.monthlyRecordRepository = monthlyRecordRepository;
        this.monthlyRecordJDBCRepository = monthlyRecordJDBCRepository;
        this.memberRepository = memberRepository;
    }

    public MonthlyRecord createMonthlyRecord(MonthlyRecordRequestDTO requestDTO) {
        MonthlyRecord monthlyRecord = new MonthlyRecord(
                memberRepository.findById(requestDTO.getMemberId()).get(),
                requestDTO.getMonthlyTotalDistance(),
                requestDTO.getMonthlyTotalTime(),
                requestDTO.getYearMonth(),
                requestDTO.getMonthlyRecordPace(),
                requestDTO.getMonthlyRunningStep(),
                requestDTO.getMonthYear()
        );
        return monthlyRecordRepository.save(monthlyRecord);
    }

    public MonthlyRecord getMonthlyRecordById(Long id) {
        return monthlyRecordRepository.findById(id).orElseThrow(() -> new RuntimeException("Record not found"));
    }

    public List<MonthlyRecord> getAllMonthlyRecords() {
        return monthlyRecordRepository.findAll();
    }

    public MonthlyRecord updateMonthlyRecord(Long id, MonthlyRecordRequestDTO requestDTO) {
        MonthlyRecord existingRecord = monthlyRecordRepository.findById(id).orElseThrow(() -> new RuntimeException("Record not found"));
        existingRecord.getMember().setMemberId(requestDTO.getMemberId());
        existingRecord.setMonthlyTotalDistance(requestDTO.getMonthlyTotalDistance());
        existingRecord.setMonthlyTotalTime(requestDTO.getMonthlyTotalTime());
        existingRecord.setYearMonths(requestDTO.getYearMonth());
        existingRecord.setMonthlyRecordPace(requestDTO.getMonthlyRecordPace());
        return monthlyRecordRepository.save(existingRecord);
    }

    public List<MonthlyRecordResponseDTO> getMonthlyRecordListByMemberId(Long memberId) {
        List<MonthlyRecord> monthlyRecordList = monthlyRecordRepository.findAllByMemberMemberId(memberId);
        return monthlyRecordList.stream().map(this::fromEntity).toList();
    }

    public MonthlyRecordResponseDTO fromEntity(MonthlyRecord monthlyRecord) {
        return new MonthlyRecordResponseDTO(
                monthlyRecord.getMonthlyRecordId(),
                monthlyRecord.getMember().getMemberId(),
                monthlyRecord.getMonthlyTotalDistance(),
                monthlyRecord.getMonthlyTotalTime(),
                monthlyRecord.getYearMonths(),
                monthlyRecord.getMonthlyRecordPace(),
                monthlyRecord.getMonthlyRunningStep(),
                monthlyRecord.getMonthYears());
    }


    public void updateMonthlyRecord(Record savedRecord) {
        monthlyRecordJDBCRepository.save(savedRecord);
    }

    public void deleteMonthlyRecord(Long id) {
        monthlyRecordRepository.deleteById(id);
    }

    public Double getThisMonthAvgDistance() {
        return monthlyRecordJDBCRepository.getThisMonthAvgDistance();
    }
}
