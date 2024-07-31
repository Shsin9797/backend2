package com.runtobeat.first.controller;

import com.runtobeat.first.dto.DailyRecordResponseDTO;
import com.runtobeat.first.dto.MonthlyRecordRequestDTO;
import com.runtobeat.first.dto.MonthlyRecordResponseDTO;
import com.runtobeat.first.dto.WeeklyRecordResponseDTO;
import com.runtobeat.first.entity.MonthlyRecord;
import com.runtobeat.first.service.MonthlyRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/monthly-record")
public class MonthlyRecordController {

    @Autowired
    private MonthlyRecordService monthlyRecordService;

    @PostMapping
    public ResponseEntity<MonthlyRecordResponseDTO> createMonthlyRecord(@RequestBody MonthlyRecordRequestDTO requestDTO) {
        MonthlyRecord monthlyRecord = monthlyRecordService.createMonthlyRecord(requestDTO);
        return ResponseEntity.ok(new MonthlyRecordResponseDTO(
                monthlyRecord.getMember().getMemberId(),
                monthlyRecord.getMonthlyTotalDistance(),
                monthlyRecord.getMonthlyTotalTime(),
                monthlyRecord.getYearMonths(),
                monthlyRecord.getMonthlyRecordPace(),
                monthlyRecord.getMonthlyRunningStep(),
                monthlyRecord.getMonthYears()
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MonthlyRecordResponseDTO> getMonthlyRecordById(@PathVariable Long id) {
        MonthlyRecord monthlyRecord = monthlyRecordService.getMonthlyRecordById(id);
        return ResponseEntity.ok(new MonthlyRecordResponseDTO(
                monthlyRecord.getMonthlyRecordId(),
                monthlyRecord.getMember().getMemberId(),
                monthlyRecord.getMonthlyTotalDistance(),
                monthlyRecord.getMonthlyTotalTime(),
                monthlyRecord.getYearMonths(),
                monthlyRecord.getMonthlyRecordPace(),
                monthlyRecord.getMonthlyRunningStep(),
                monthlyRecord.getMonthYears()
        ));
    }

    @GetMapping
    public ResponseEntity<List<MonthlyRecordResponseDTO>> getAllMonthlyRecords() {
        List<MonthlyRecord> monthlyRecords = monthlyRecordService.getAllMonthlyRecords();
        List<MonthlyRecordResponseDTO> responseDTOs = monthlyRecords.stream().map(monthlyRecord -> new MonthlyRecordResponseDTO(
                monthlyRecord.getMonthlyRecordId(),
                monthlyRecord.getMember().getMemberId(),
                monthlyRecord.getMonthlyTotalDistance(),
                monthlyRecord.getMonthlyTotalTime(),
                monthlyRecord.getYearMonths(),
                monthlyRecord.getMonthlyRecordPace(),
                monthlyRecord.getMonthlyRunningStep(),
                monthlyRecord.getMonthYears()
        )).collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOs);
    }

    @GetMapping("/stats/avg/thismonth")
    public ResponseEntity<Double> getThisMonthAvgDistance() {
        return ResponseEntity.ok(monthlyRecordService.getThisMonthAvgDistance());
    }

    @PutMapping("/{id}")
    public ResponseEntity<MonthlyRecordResponseDTO> updateMonthlyRecord(@PathVariable Long id, @RequestBody MonthlyRecordRequestDTO requestDTO) {
        MonthlyRecord monthlyRecord = monthlyRecordService.updateMonthlyRecord(id, requestDTO);
        return ResponseEntity.ok(new MonthlyRecordResponseDTO(
                monthlyRecord.getMonthlyRecordId(),
                monthlyRecord.getMember().getMemberId(),
                monthlyRecord.getMonthlyTotalDistance(),
                monthlyRecord.getMonthlyTotalTime(),
                monthlyRecord.getYearMonths(),
                monthlyRecord.getMonthlyRecordPace(),
                monthlyRecord.getMonthlyRunningStep(),
                monthlyRecord.getMonthYears()
        ));
    }
    @GetMapping("/list/{memberId}")
    public ResponseEntity<List<MonthlyRecordResponseDTO>> getDailyRecordListById(@PathVariable Long memberId) {
        List<MonthlyRecordResponseDTO> monthlyRecord = monthlyRecordService.getMonthlyRecordListByMemberId(memberId);
        return ResponseEntity.ok(monthlyRecord);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMonthlyRecord(@PathVariable Long id) {
        monthlyRecordService.deleteMonthlyRecord(id);
        return ResponseEntity.noContent().build();
    }
}
