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
                dailyRecord.getMember().getMemberId(),
                dailyRecord.getDailyTotalDistance(),
                dailyRecord.getDailyTotalTime(),
                dailyRecord.getYearMonthDate(),
                dailyRecord.getDailyRecordPace(),
                dailyRecord.getDailyRunningStep()
        ));
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<DailyRecordResponseDTO> getDailyRecordById(@PathVariable Long memberId) {
        DailyRecord dailyRecord = dailyRecordService.getDailyRecordByMemberId(memberId);
        return ResponseEntity.ok(new DailyRecordResponseDTO(
                dailyRecord.getDailyRecordId(),
                dailyRecord.getMember().getMemberId(),
                dailyRecord.getDailyTotalDistance(),
                dailyRecord.getDailyTotalTime(),
                dailyRecord.getYearMonthDate(),
                dailyRecord.getDailyRecordPace(),
                dailyRecord.getDailyRunningStep()
        ));
    }

    @GetMapping("/list/{memberId}")
    public ResponseEntity<List<DailyRecordResponseDTO>> getDailyRecordListById(@PathVariable Long memberId) {
        List<DailyRecordResponseDTO> dailyRecord = dailyRecordService.getDailyRecordListByMemberId(memberId);
        return ResponseEntity.ok(dailyRecord);
    }

    @GetMapping
    public ResponseEntity<List<DailyRecordResponseDTO>> getAllDailyRecords() {
        List<DailyRecord> dailyRecords = dailyRecordService.getAllDailyRecords();
        List<DailyRecordResponseDTO> responseDTOs = dailyRecords.stream().map(dailyRecord -> new DailyRecordResponseDTO(
                dailyRecord.getDailyRecordId(),
                dailyRecord.getMember().getMemberId(),
                dailyRecord.getDailyTotalDistance(),
                dailyRecord.getDailyTotalTime(),
                dailyRecord.getYearMonthDate(),
                dailyRecord.getDailyRecordPace(),
                dailyRecord.getDailyRunningStep()
        )).collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOs);
    }

    @GetMapping("/stats/avg/today")
    public ResponseEntity<Double> getTodayAvgDistance() {
        return ResponseEntity.ok(dailyRecordService.getTodayAvgDistance());
    }

    @PutMapping("/{id}")
    public ResponseEntity<DailyRecordResponseDTO> updateDailyRecord(@PathVariable Long id, @RequestBody DailyRecordRequestDTO requestDTO) {
        DailyRecord dailyRecord = dailyRecordService.updateDailyRecord(id, requestDTO);
        return ResponseEntity.ok(new DailyRecordResponseDTO(
                dailyRecord.getDailyRecordId(),
                dailyRecord.getMember().getMemberId(),
                dailyRecord.getDailyTotalDistance(),
                dailyRecord.getDailyTotalTime(),
                dailyRecord.getYearMonthDate(),
                dailyRecord.getDailyRecordPace(),
                dailyRecord.getDailyRunningStep()
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDailyRecord(@PathVariable Long id) {
        dailyRecordService.deleteDailyRecord(id);
        return ResponseEntity.noContent().build();
    }
}
