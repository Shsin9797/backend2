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
@RequestMapping("/daily-record")
public class DailyRecordController {

    @Autowired
    private DailyRecordService dailyRecordService;

    @PostMapping
    public ResponseEntity<DailyRecordResponseDTO> createDailyRecord(@RequestBody DailyRecordRequestDTO requestDTO) {
        DailyRecord dailyRecord = dailyRecordService.createDailyRecord(requestDTO);
        return ResponseEntity.ok(new DailyRecordResponseDTO(
                dailyRecord.getDailyRecordId(),
                dailyRecord.getMemberId(),
                dailyRecord.getDailyTotalDistance(),
                dailyRecord.getDailyTotalTime(),
                dailyRecord.getYearMonthDate(),
                dailyRecord.getDailyRecordPace(),
                dailyRecord.getDailyRunningStep()
        ));
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<DailyRecordResponseDTO> getDailyRecordById(@PathVariable String memberId) {
        DailyRecord dailyRecord = dailyRecordService.getDailyRecordByMemberId(memberId);
        return ResponseEntity.ok(new DailyRecordResponseDTO(
                dailyRecord.getDailyRecordId(),
                dailyRecord.getMemberId(),
                dailyRecord.getDailyTotalDistance(),
                dailyRecord.getDailyTotalTime(),
                dailyRecord.getYearMonthDate(),
                dailyRecord.getDailyRecordPace(),
                dailyRecord.getDailyRunningStep()
        ));
    }

    @GetMapping("/list/{memberId}")
    public ResponseEntity<List<DailyRecordResponseDTO>> getDailyRecordListById(@PathVariable String memberId) {
        List<DailyRecordResponseDTO> dailyRecord = dailyRecordService.getDailyRecordListByMemberId(memberId);
        return ResponseEntity.ok(dailyRecord);
    }

    @GetMapping
    public ResponseEntity<List<DailyRecordResponseDTO>> getAllDailyRecords() {
        List<DailyRecord> dailyRecords = dailyRecordService.getAllDailyRecords();
        List<DailyRecordResponseDTO> responseDTOs = dailyRecords.stream().map(dailyRecord -> new DailyRecordResponseDTO(
                dailyRecord.getDailyRecordId(),
                dailyRecord.getMemberId(),
                dailyRecord.getDailyTotalDistance(),
                dailyRecord.getDailyTotalTime(),
                dailyRecord.getYearMonthDate(),
                dailyRecord.getDailyRecordPace(),
                dailyRecord.getDailyRunningStep()
        )).collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOs);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DailyRecordResponseDTO> updateDailyRecord(@PathVariable String id, @RequestBody DailyRecordRequestDTO requestDTO) {
        DailyRecord dailyRecord = dailyRecordService.updateDailyRecord(id, requestDTO);
        return ResponseEntity.ok(new DailyRecordResponseDTO(
                dailyRecord.getDailyRecordId(),
                dailyRecord.getMemberId(),
                dailyRecord.getDailyTotalDistance(),
                dailyRecord.getDailyTotalTime(),
                dailyRecord.getYearMonthDate(),
                dailyRecord.getDailyRecordPace(),
                dailyRecord.getDailyRunningStep()
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDailyRecord(@PathVariable String id) {
        dailyRecordService.deleteDailyRecord(id);
        return ResponseEntity.noContent().build();
    }
}
