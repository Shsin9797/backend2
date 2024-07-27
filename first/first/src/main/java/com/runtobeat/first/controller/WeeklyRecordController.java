package com.runtobeat.first.controller;

import com.runtobeat.first.dto.WeeklyRecordRequestDTO;
import com.runtobeat.first.dto.WeeklyRecordResponseDTO;
import com.runtobeat.first.entity.WeeklyRecord;
import com.runtobeat.first.service.WeeklyRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/weekly-record")
public class WeeklyRecordController {

    @Autowired
    private WeeklyRecordService weeklyRecordService;

    @PostMapping
    public ResponseEntity<WeeklyRecordResponseDTO> createWeeklyRecord(@RequestBody WeeklyRecordRequestDTO requestDTO) {
        WeeklyRecord weeklyRecord = weeklyRecordService.createWeeklyRecord(requestDTO);
        return ResponseEntity.ok(new WeeklyRecordResponseDTO(
                weeklyRecord.getMemberId(),
                weeklyRecord.getWeeklyTotalDistance(),
                weeklyRecord.getWeeklyTotalTime(),
                weeklyRecord.getYearWeek(),
                weeklyRecord.getWeeklyRecordPace(),
                weeklyRecord.getWeeklyRunningStep(),
                weeklyRecord.getWeekYear()
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<WeeklyRecordResponseDTO> getWeeklyRecordById(@PathVariable String id) {
        WeeklyRecord weeklyRecord = weeklyRecordService.getWeeklyRecordById(id);
        return ResponseEntity.ok(new WeeklyRecordResponseDTO(
                weeklyRecord.getWeeklyRecordId(),
                weeklyRecord.getMemberId(),
                weeklyRecord.getWeeklyTotalDistance(),
                weeklyRecord.getWeeklyTotalTime(),
                weeklyRecord.getYearWeek(),
                weeklyRecord.getWeeklyRecordPace(),
                weeklyRecord.getWeeklyRunningStep(),
                weeklyRecord.getWeekYear()
        ));
    }

    @GetMapping
    public ResponseEntity<List<WeeklyRecordResponseDTO>> getAllWeeklyRecords() {
        List<WeeklyRecord> weeklyRecords = weeklyRecordService.getAllWeeklyRecords();
        List<WeeklyRecordResponseDTO> responseDTOs = weeklyRecords.stream().map(weeklyRecord -> new WeeklyRecordResponseDTO(
                weeklyRecord.getWeeklyRecordId(),
                weeklyRecord.getMemberId(),
                weeklyRecord.getWeeklyTotalDistance(),
                weeklyRecord.getWeeklyTotalTime(),
                weeklyRecord.getYearWeek(),
                weeklyRecord.getWeeklyRecordPace(),
                weeklyRecord.getWeeklyRunningStep(),
                weeklyRecord.getWeekYear()
        )).collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOs);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WeeklyRecordResponseDTO> updateWeeklyRecord(@PathVariable String id, @RequestBody WeeklyRecordRequestDTO requestDTO) {
        WeeklyRecord weeklyRecord = weeklyRecordService.updateWeeklyRecord(id, requestDTO);
        return ResponseEntity.ok(new WeeklyRecordResponseDTO(
                weeklyRecord.getWeeklyRecordId(),
                weeklyRecord.getMemberId(),
                weeklyRecord.getWeeklyTotalDistance(),
                weeklyRecord.getWeeklyTotalTime(),
                weeklyRecord.getYearWeek(),
                weeklyRecord.getWeeklyRecordPace(),
                weeklyRecord.getWeeklyRunningStep(),
                weeklyRecord.getWeekYear()
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWeeklyRecord(@PathVariable String id) {
        weeklyRecordService.deleteWeeklyRecord(id);
        return ResponseEntity.noContent().build();
    }
}
