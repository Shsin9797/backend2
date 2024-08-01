package com.runtobeat.first.controller;

import com.runtobeat.first.dto.MypageTotalRunningInfoResponseDTO;
import com.runtobeat.first.dto.RecordCreateRequestDTO;
import com.runtobeat.first.dto.RecordResponseDTO;
import com.runtobeat.first.dto.TodayRankingResponseDTO;
import com.runtobeat.first.service.DailyRecordService;
import com.runtobeat.first.service.MemberService;
import com.runtobeat.first.service.MonthlyRecordService;
import com.runtobeat.first.service.RecordService;
import com.runtobeat.first.service.WeeklyRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<RecordResponseDTO> createRecord(@RequestBody RecordCreateRequestDTO recordCreateRequestDTO) {
        RecordResponseDTO rd = recordService.createRecord(recordCreateRequestDTO);
        return ResponseEntity.ok(rd);
    }

    @GetMapping("/rank/{memberId}") //해당하는 멤버의 랭킹 가져오기
    public ResponseEntity<TodayRankingResponseDTO> getMyRanking(@PathVariable Long memberId, @RequestParam Long recordId) {
        TodayRankingResponseDTO ranking = recordService.getMyRecordRanking(memberId, recordId);
        return ResponseEntity.ok(ranking);
    }

    @GetMapping("/mypage/total-running-info/{memberId}") //마이페이지에서 총 달린거리와 평균 페이스 가져오기
    public ResponseEntity<MypageTotalRunningInfoResponseDTO> getMypageTotalRunningInfo(@PathVariable Long memberId) {
        MypageTotalRunningInfoResponseDTO myRunningInfo = memberService.getMemberRunningInfo(memberId);
        return ResponseEntity.ok(myRunningInfo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecordResponseDTO> getRecordById(@PathVariable Long id) {
        return ResponseEntity.ok(recordService.getRecordById(id));
    }

    @GetMapping
    public ResponseEntity<List<RecordResponseDTO>> getAllRecords() {
        return ResponseEntity.ok(recordService.getAllRecords());
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecordResponseDTO> updateRecord(@PathVariable Long id, @RequestBody RecordCreateRequestDTO recordCreateRequestDTO) {
        return ResponseEntity.ok(recordService.updateRecord(id, recordCreateRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecord(@PathVariable Long id) {
        recordService.deleteRecord(id);
        return ResponseEntity.noContent().build();
    }
}