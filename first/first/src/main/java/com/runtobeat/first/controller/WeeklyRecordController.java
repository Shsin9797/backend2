package com.runtobeat.first.controller;

import com.runtobeat.first.dto.WeeklyRecordRequestDTO;
import com.runtobeat.first.dto.WeeklyRecordResponseDTO;
import com.runtobeat.first.entity.WeeklyRecord;
import com.runtobeat.first.service.WeeklyRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
                weeklyRecord.getMember().getMemberId(),
                weeklyRecord.getWeeklyTotalDistance(),
                weeklyRecord.getWeeklyTotalTime(),
                weeklyRecord.getYearWeek(),
                weeklyRecord.getWeeklyRecordPace(),
                weeklyRecord.getWeeklyRunningStep(),
                weeklyRecord.getWeekYear()
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<WeeklyRecordResponseDTO> getWeeklyRecordById(@PathVariable Long id) {
        WeeklyRecord weeklyRecord = weeklyRecordService.getWeeklyRecordById(id);
        return ResponseEntity.ok(new WeeklyRecordResponseDTO(
                weeklyRecord.getWeeklyRecordId(),
                weeklyRecord.getMember().getMemberId(),
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
                weeklyRecord.getMember().getMemberId(),
                weeklyRecord.getWeeklyTotalDistance(),
                weeklyRecord.getWeeklyTotalTime(),
                weeklyRecord.getYearWeek(),
                weeklyRecord.getWeeklyRecordPace(),
                weeklyRecord.getWeeklyRunningStep(),
                weeklyRecord.getWeekYear()
        )).collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOs);
    }

    @GetMapping("/list/{memberId}")
    public ResponseEntity<List<WeeklyRecordResponseDTO>> getDailyRecordListById(@PathVariable Long memberId) {
        List<WeeklyRecordResponseDTO> weeklyRecord = weeklyRecordService.getWeeklyRecordListByMemberId(memberId);
        return ResponseEntity.ok(weeklyRecord);
    }

    @GetMapping("/stats/avg/thisweek")
    public ResponseEntity<Double> getThisWeekAvgDistance() {
        return ResponseEntity.ok(weeklyRecordService.getThisWeekAvgDistance());
    }

    @PutMapping("/{id}")
    public ResponseEntity<WeeklyRecordResponseDTO> updateWeeklyRecord(@PathVariable Long id, @RequestBody WeeklyRecordRequestDTO requestDTO) {
        WeeklyRecord weeklyRecord = weeklyRecordService.updateWeeklyRecord(id, requestDTO);
        return ResponseEntity.ok(new WeeklyRecordResponseDTO(
                weeklyRecord.getWeeklyRecordId(),
                weeklyRecord.getMember().getMemberId(),
                weeklyRecord.getWeeklyTotalDistance(),
                weeklyRecord.getWeeklyTotalTime(),
                weeklyRecord.getYearWeek(),
                weeklyRecord.getWeeklyRecordPace(),
                weeklyRecord.getWeeklyRunningStep(),
                weeklyRecord.getWeekYear()
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWeeklyRecord(@PathVariable Long id) {
        weeklyRecordService.deleteWeeklyRecord(id);
        return ResponseEntity.noContent().build();
    }
}
