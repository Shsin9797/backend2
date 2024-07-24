package com.runtobeat.first.service;

import com.runtobeat.first.dto.DailyRecordRequestDTO;
import com.runtobeat.first.entity.DailyRecord;
import com.runtobeat.first.repository.DailyRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DailyRecordService {

    @Autowired
    private DailyRecordRepository dailyRecordRepository;

    public DailyRecord createDailyRecord(DailyRecordRequestDTO requestDTO) {
        DailyRecord dailyRecord = new DailyRecord(
                null,
                requestDTO.getDailyTotalDistance(),
                requestDTO.getDailyTotalTime(),
                requestDTO.getYearMonthDate(),
                requestDTO.getDailyRecordPace()
        );
        return dailyRecordRepository.save(dailyRecord);
    }

    public DailyRecord getDailyRecordById(String id) {
        return dailyRecordRepository.findById(id).orElseThrow(() -> new RuntimeException("Record not found"));
    }

    public List<DailyRecord> getAllDailyRecords() {
        return dailyRecordRepository.findAll();
    }

    public DailyRecord updateDailyRecord(String id, DailyRecordRequestDTO requestDTO) {
        DailyRecord existingRecord = dailyRecordRepository.findById(id).orElseThrow(() -> new RuntimeException("Record not found"));
        existingRecord.setDailyTotalDistance(requestDTO.getDailyTotalDistance());
        existingRecord.setDailyTotalTime(requestDTO.getDailyTotalTime());
        existingRecord.setYearMonthDate(requestDTO.getYearMonthDate());
        existingRecord.setDailyRecordPace(requestDTO.getDailyRecordPace());
        return dailyRecordRepository.save(existingRecord);
    }

    public void deleteDailyRecord(String id) {
        dailyRecordRepository.deleteById(id);
    }
}
