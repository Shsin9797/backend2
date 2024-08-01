package com.runtobeat.first.service;

import com.runtobeat.first.dto.MonthlyRecordRequestDTO;
import com.runtobeat.first.dto.MonthlyRecordResponseDTO;
import com.runtobeat.first.entity.MonthlyRecord;
import com.runtobeat.first.entity.Record;
import com.runtobeat.first.repository.MemberRepository;
import com.runtobeat.first.repository.MonthlyRecordJDBCRepository;
import com.runtobeat.first.repository.MonthlyRecordRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    public String getMonthYear(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
        return date.format(formatter);
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
        //existingRecord.getMember().setMemberId(requestDTO.getMemberId());
        existingRecord.setMonthlyTotalDistance(requestDTO.getMonthlyTotalDistance());
        existingRecord.setMonthlyTotalTime(requestDTO.getMonthlyTotalTime());
        //existingRecord.setYearMonths(requestDTO.getYearMonth());
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
        String monthYear = getMonthYear(savedRecord.getRecordDate());
        MonthlyRecord originMonthly = monthlyRecordRepository.getByMemberMemberIdAndMonthYears(
                savedRecord.getMember().getMemberId(), monthYear);

        if (originMonthly == null) {
            originMonthly = new MonthlyRecord(
                    savedRecord.getMember(),
                    savedRecord.getRunningDistance(),
                    savedRecord.getRunningTime(),
                    savedRecord.getRecordDate(),
                    savedRecord.getRecordPace(),
                    savedRecord.getRunningStep(),
                    monthYear
            );
        } else {
            double newMonthlyDistance = originMonthly.getMonthlyTotalDistance() + savedRecord.getRunningDistance();
            long totalExistingSeconds = originMonthly.getMonthlyTotalTime();
            long totalNewSeconds = savedRecord.getRunningTime();
            long updateTotalSeconds = totalExistingSeconds + totalNewSeconds;
            long newMonthlyTime = updateTotalSeconds;

            double newMonthlyPace = (newMonthlyDistance > 0) ? (newMonthlyTime / newMonthlyDistance) : 0.0;
            long newMonthlyStep = originMonthly.getMonthlyRunningStep() + savedRecord.getRunningStep();
            originMonthly.setMonthlyTotalDistance(newMonthlyDistance);
            originMonthly.setMonthlyTotalTime(newMonthlyTime);
            originMonthly.setMonthlyRecordPace(newMonthlyPace);
            originMonthly.setMonthlyRunningStep(newMonthlyStep);
        }
        monthlyRecordRepository.save(originMonthly);
    }

    public void deleteMonthlyRecord(Long id) {
        monthlyRecordRepository.deleteById(id);
    }

    public Double getThisMonthAvgDistance() {
        return monthlyRecordJDBCRepository.getThisMonthAvgDistance();
    }
}

