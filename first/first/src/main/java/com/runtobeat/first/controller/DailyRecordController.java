package com.runtobeat.first.controller;

import com.runtobeat.first.dto.DailyRecordRequestDTO;
import com.runtobeat.first.dto.DailyRecordResponseDTO;
import com.runtobeat.first.entity.DailyRecord;
import com.runtobeat.first.service.DailyRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/daily-records")
public class DailyRecordController {

    @Autowired
    private DailyRecordService dailyRecordService;

    @PostMapping
    public ResponseEntity<DailyRecordResponseDTO> createDailyRecord(@RequestBody DailyRecordRequestDTO requestDTO) {
        DailyRecord dailyRecord = dailyRecordService.createDailyRecord(requestDTO);
        return ResponseEntity.ok(new DailyRecordResponseDTO(
                dailyRecord.getDailyRecordId(),
                dailyRecord.getDailyTotalDistance(),
                dailyRecord.getDailyTotalTime(),
                dailyRecord.getYearMonthDate(),
                dailyRecord.getDailyRecordPace()
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DailyRecordResponseDTO> getDailyRecordById(@PathVariable String id) {
        DailyRecord dailyRecord = dailyRecordService.getDailyRecordById(id);
        return ResponseEntity.ok(new DailyRecordResponseDTO(
                dailyRecord.getDailyRecordId(),
                dailyRecord.getDailyTotalDistance(),
                dailyRecord.getDailyTotalTime(),
                dailyRecord.getYearMonthDate(),
                dailyRecord.getDailyRecordPace()
        ));
    }

    @GetMapping
    public ResponseEntity<List<DailyRecordResponseDTO>> getAllDailyRecords() {
        List<DailyRecord> dailyRecords = dailyRecordService.getAllDailyRecords();
        List<DailyRecordResponseDTO> responseDTOs = dailyRecords.stream().map(dailyRecord -> new DailyRecordResponseDTO(
                dailyRecord.getDailyRecordId(),
                dailyRecord.getDailyTotalDistance(),
                dailyRecord.getDailyTotalTime(),
                dailyRecord.getYearMonthDate(),
                dailyRecord.getDailyRecordPace()
        )).collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOs);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DailyRecordResponseDTO> updateDailyRecord(@PathVariable String id, @RequestBody DailyRecordRequestDTO requestDTO) {
        DailyRecord dailyRecord = dailyRecordService.updateDailyRecord(id, requestDTO);
        return ResponseEntity.ok(new DailyRecordResponseDTO(
                dailyRecord.getDailyRecordId(),
                dailyRecord.getDailyTotalDistance(),
                dailyRecord.getDailyTotalTime(),
                dailyRecord.getYearMonthDate(),
                dailyRecord.getDailyRecordPace()
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDailyRecord(@PathVariable String id) {
        dailyRecordService.deleteDailyRecord(id);
        return ResponseEntity.noContent().build();
    }
}
