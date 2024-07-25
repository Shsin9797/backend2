package com.runtobeat.first.controller;

import com.runtobeat.first.dto.TodayRankingResponseDTO;
import com.runtobeat.first.dto.RecordRequestDTO;
import com.runtobeat.first.dto.RecordResponseDTO;
import com.runtobeat.first.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/record")
@RequiredArgsConstructor
public class RecordController {

    private final RecordService recordService;

    @PostMapping // api 명세서 1번
    public ResponseEntity<RecordResponseDTO> createRecord(@RequestBody RecordRequestDTO recordRequestDTO) {
        return ResponseEntity.ok(recordService.createRecord(recordRequestDTO));
    }

    @GetMapping("/rank/{memberId}")
    public ResponseEntity<TodayRankingResponseDTO> getMyRanking(@RequestParam String memberId) {
        return ResponseEntity.ok(recordService.getMyRecordRanking(memberId));
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