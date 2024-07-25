package com.runtobeat.first.service;

import com.runtobeat.first.dto.TodayRankingResponseDTO;
import com.runtobeat.first.dto.RecordRequestDTO;
import com.runtobeat.first.dto.RecordResponseDTO;
import com.runtobeat.first.entity.Record;
import com.runtobeat.first.repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecordService {

    private final RecordRepository recordRepository;
    private final DailyRecordService dailyRecordService;

    public RecordResponseDTO createRecord(RecordRequestDTO recordRequestDTO) {
        Record record = new Record(
                recordRequestDTO.getMemberId(),
                recordRequestDTO.getRunningDistance(),
                recordRequestDTO.getRunningTime(),
                recordRequestDTO.getRunningStep(),
                recordRequestDTO.getRecordDate(),
                recordRequestDTO.getRecordPace()
        );

        Record savedRecord = recordRepository.save(record);
        dailyRecordService.updateDailyRecord(savedRecord);
        return convertToResponseDTO(savedRecord);
    }

    public RecordResponseDTO getRecordById(String id) {
        Record record = recordRepository.findById(id).orElseThrow(() -> new RuntimeException("Record not found"));
        return convertToResponseDTO(record);
    }

    public List<RecordResponseDTO> getAllRecords() {
        return recordRepository.findAll().stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public RecordResponseDTO updateRecord(String id, RecordRequestDTO recordRequestDTO) {
        Record record = recordRepository.findById(id).orElseThrow(() -> new RuntimeException("Record not found"));

        record.setMemberId(recordRequestDTO.getMemberId());
        record.setRunningDistance(recordRequestDTO.getRunningDistance());
        record.setRunningTime(recordRequestDTO.getRunningTime());
        record.setRunningStep(recordRequestDTO.getRunningStep());
        record.setRecordDate(recordRequestDTO.getRecordDate());
        record.setRecordPace(recordRequestDTO.getRecordPace());

        Record updatedRecord = recordRepository.save(record);
        return convertToResponseDTO(updatedRecord);
    }

    public void deleteRecord(String id) {
        recordRepository.deleteById(id);
    }

    private RecordResponseDTO convertToResponseDTO(Record record) {
        return new RecordResponseDTO(
                record.getRecordId(),
                record.getMemberId(),
                record.getRunningDistance(),
                record.getRunningTime(),
                record.getRunningStep(),
                record.getRecordDate(),
                record.getRecordPace()
        );
    }


    public TodayRankingResponseDTO getMyRecordRanking(String memberId, String recordId) {

        //'나'의 '이번' '레코드 기록'의 /  '오늘'의 '랭킹값' 가져오기 (sql 쿼리로 )
        Integer todayMyThisRanking = recordRepository.getToday

        // '오늘' '전체 사용자' '레코드'의 '페이스' 값을 평균내서 가져오기
        Double todayTotalUserAvgPace = recordRepository.fgf

        // '오늘' '전체 (기록, 레코드수)' 가져오기
        Integer todayTotalUserRecordCount = recordRepository.

        // '랭킹' '리스폰스 디티오' 반환


        return new TodayRankingResponseDTO(todayMyThisRanking,todayTotalUserAvgPace,todayTotalUserAvgPace);
    }
}
