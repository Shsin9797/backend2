package com.runtobeat.first.service;

import com.runtobeat.first.dto.WeeklyRecordRequestDTO;
import com.runtobeat.first.entity.WeeklyRecord;
import com.runtobeat.first.repository.WeeklyRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeeklyRecordService {

    @Autowired
    private WeeklyRecordRepository weeklyRecordRepository;

    public WeeklyRecord createWeeklyRecord(WeeklyRecordRequestDTO requestDTO) {
        WeeklyRecord weeklyRecord = new WeeklyRecord(
                null,
                requestDTO.getMemberId(),
                requestDTO.getWeeklyTotalDistance(),
                requestDTO.getWeeklyTotalTime(),
                requestDTO.getYearWeek(),
                requestDTO.getWeeklyRecordPace()
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
        existingRecord.setMemberId(requestDTO.getMemberId());
        existingRecord.setWeeklyTotalDistance(requestDTO.getWeeklyTotalDistance());
        existingRecord.setWeeklyTotalTime(requestDTO.getWeeklyTotalTime());
        existingRecord.setYearWeek(requestDTO.getYearWeek());
        existingRecord.setWeeklyRecordPace(requestDTO.getWeeklyRecordPace());
        return weeklyRecordRepository.save(existingRecord);
    }

    public void deleteWeeklyRecord(String id) {
        weeklyRecordRepository.deleteById(id);
    }
}
