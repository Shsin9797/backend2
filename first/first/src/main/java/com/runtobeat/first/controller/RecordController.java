package com.runtobeat.first.controller;

import com.runtobeat.first.dto.MemberRequestDTO;
import com.runtobeat.first.dto.MonthlyRecordRequestDTO;
import com.runtobeat.first.dto.MypageTotalRunningInfoResponseDTO;
import com.runtobeat.first.dto.TodayRankingResponseDTO;
import com.runtobeat.first.dto.RecordRequestDTO;
import com.runtobeat.first.dto.RecordResponseDTO;
import com.runtobeat.first.service.DailyRecordService;
import com.runtobeat.first.service.MemberService;
import com.runtobeat.first.service.MonthlyRecordService;
import com.runtobeat.first.service.RecordService;
import com.runtobeat.first.service.WeeklyRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/record")
@RequiredArgsConstructor
public class RecordController {

    private final RecordService recordService;
    private final MemberService memberService;
    private final DailyRecordService dailyRecordService;
    private final WeeklyRecordService weeklyRecordService;
    private final MonthlyRecordService monthlyRecordService;


    @PostMapping // api 명세서 1번
    public ResponseEntity<RecordResponseDTO> createRecord(@RequestBody RecordRequestDTO recordRequestDTO) {
        RecordResponseDTO rd = recordService.createRecord(recordRequestDTO);
        // member 업데이트
        memberService.updateMember(recordRequestDTO.getMemberId(),recordRequestDTO);
        //daily 업데이트
        dailyRecordService.updateDailyRecord(rd);
        // weekly 업데이트
        weeklyRecordService.updateWeeklyRecord(rd);
        //monthly 업데이트
        monthlyRecordService.updateMonthlyRecord(rd);
        return ResponseEntity.ok(rd);
    }

    @GetMapping("/rank/{memberId}")
    public ResponseEntity<TodayRankingResponseDTO> getMyRanking(@PathVariable String memberId, @RequestParam String recordId) {
        TodayRankingResponseDTO ranking = recordService.getMyRecordRanking(memberId, recordId);
        return ResponseEntity.ok(ranking);
    }

    @GetMapping("/mypage/total-running-info/{memberId}")
    public ResponseEntity<MypageTotalRunningInfoResponseDTO> getMypageTotalRunningInfo(@PathVariable String memberId) {
        MypageTotalRunningInfoResponseDTO myRunningInfo = memberService.getMemberRunningInfo(memberId);
        return ResponseEntity.ok(myRunningInfo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecordResponseDTO> getRecordById(@PathVariable String id) {
        return ResponseEntity.ok(recordService.getRecordById(id));
    }

    @GetMapping
    public ResponseEntity<List<RecordResponseDTO>> getAllRecords() {
        return ResponseEntity.ok(recordService.getAllRecords());
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecordResponseDTO> updateRecord(@PathVariable String id, @RequestBody RecordRequestDTO recordRequestDTO) {
        return ResponseEntity.ok(recordService.updateRecord(id, recordRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecord(@PathVariable String id) {
        recordService.deleteRecord(id);
        return ResponseEntity.noContent().build();
    }
}