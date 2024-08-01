package com.runtobeat.first.service;


import com.runtobeat.first.dto.WeeklyRecordRequestDTO;
import com.runtobeat.first.dto.WeeklyRecordResponseDTO;
import com.runtobeat.first.entity.Record;
import com.runtobeat.first.entity.WeeklyRecord;
import com.runtobeat.first.repository.MemberRepository;
import com.runtobeat.first.repository.WeeklyRecordJDBCRepository;
import com.runtobeat.first.repository.WeeklyRecordRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.IsoFields;
import java.util.List;

@Service
public class WeeklyRecordService {

    private WeeklyRecordRepository weeklyRecordRepository;
    private WeeklyRecordJDBCRepository weeklyRecordJDBCRepository;
    private MemberRepository memberRepository;

    public String getWeekYear(LocalDate date) {
        int weekNumber = date.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);
        int year = date.get(IsoFields.WEEK_BASED_YEAR);
        return year + "-" + weekNumber;
    }

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

    public WeeklyRecord getWeeklyRecordById(Long id) {
        return weeklyRecordRepository.findById(id).orElseThrow(() -> new RuntimeException("Record not found"));
    }

    public List<WeeklyRecordResponseDTO> getWeeklyRecordListByMemberId(Long memberId) {
        List<WeeklyRecord> weeklyRecordList = weeklyRecordRepository.findAllByMemberMemberId(memberId);
        return weeklyRecordList.stream().map(this::fromEntity).toList();
    }

    public WeeklyRecordResponseDTO fromEntity(WeeklyRecord weeklyRecord) {
        return new WeeklyRecordResponseDTO(
                weeklyRecord.getWeeklyRecordId(),
                weeklyRecord.getMember().getMemberId(),
                weeklyRecord.getWeeklyTotalDistance(),
                weeklyRecord.getWeeklyTotalTime(),
                weeklyRecord.getYearWeek(),
                weeklyRecord.getWeeklyRecordPace(),
                weeklyRecord.getWeeklyRunningStep(),
                weeklyRecord.getWeekYear());
    }

    public List<WeeklyRecord> getAllWeeklyRecords() {
        return weeklyRecordRepository.findAll();
    }

    public WeeklyRecord updateWeeklyRecord(Long id, WeeklyRecordRequestDTO requestDTO) {
        WeeklyRecord existingRecord = weeklyRecordRepository.findById(id).orElseThrow(() -> new RuntimeException("Record not found"));
        existingRecord.getMember().setMemberId(requestDTO.getMemberId());
        existingRecord.setWeeklyTotalDistance(requestDTO.getWeeklyTotalDistance());
        existingRecord.setWeeklyTotalTime(requestDTO.getWeeklyTotalTime());
        existingRecord.setYearWeek(requestDTO.getYearWeek());
        existingRecord.setWeeklyRecordPace(requestDTO.getWeeklyRecordPace());
        return weeklyRecordRepository.save(existingRecord);
    }

    public void updateWeeklyRecord(Record savedRecord) {
        String weekYear = getWeekYear(savedRecord.getRecordDate());
        WeeklyRecord originWeekly = weeklyRecordRepository.findByMemberMemberIdAndWeekYear(
                savedRecord.getMember().getMemberId(), weekYear);

        if (originWeekly == null) {
            originWeekly = new WeeklyRecord(
                    savedRecord.getMember(),
                    savedRecord.getRunningDistance(),
                    savedRecord.getRunningTime(),
                    savedRecord.getRecordDate(),
                    savedRecord.getRecordPace(),
                    savedRecord.getRunningStep(),
                    weekYear
            );
        } else {
            double newWeeklyDistance = originWeekly.getWeeklyTotalDistance() + savedRecord.getRunningDistance();
            long totalExistingSeconds = originWeekly.getWeeklyTotalTime();
            long totalNewSeconds = savedRecord.getRunningTime();
            long updateTotalSeconds = totalExistingSeconds + totalNewSeconds;
            long newWeeklyTime = updateTotalSeconds;
            double newWeeklyPace = (newWeeklyDistance > 0) ? (newWeeklyTime / newWeeklyDistance) : 0.0;

            long newWeeklyStep = originWeekly.getWeeklyRunningStep() + savedRecord.getRunningStep();

            originWeekly.setWeeklyTotalDistance(newWeeklyDistance);
            originWeekly.setWeeklyTotalTime(newWeeklyTime);
            originWeekly.setWeeklyRecordPace(newWeeklyPace);
            originWeekly.setWeeklyRunningStep(newWeeklyStep);
        }

        weeklyRecordRepository.save(originWeekly);
    }


    public void deleteWeeklyRecord(Long id) {
        weeklyRecordRepository.deleteById(id);
    }

    public Double getThisWeekAvgDistance() {
        return weeklyRecordJDBCRepository.getThisWeekAvgDistance();
    }
}
