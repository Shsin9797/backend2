package com.runtobeat.first.service;

import com.runtobeat.first.dto.RecordCreateRequestDTO;
import com.runtobeat.first.dto.TodayRankingResponseDTO;
import com.runtobeat.first.dto.RecordResponseDTO;
import com.runtobeat.first.entity.Record;
import com.runtobeat.first.repository.RecordJDBCRepository;
import com.runtobeat.first.repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecordService {

    private final RecordRepository recordRepository;
    private final MemberService memberService;
    private final DailyRecordService dailyRecordService;
    private final WeeklyRecordService weeklyRecordService;
    private final MonthlyRecordService monthlyRecordService;

    private final RecordJDBCRepository recordJDBCRepository;

    public RecordResponseDTO createRecord(RecordCreateRequestDTO recordCreateRequestDTO) {
        Record record = new Record(
                memberService.getMemberEntity(recordCreateRequestDTO.getMemberId()),
                recordCreateRequestDTO.getRunningDistance(),
                recordCreateRequestDTO.getRunningTime(),
                recordCreateRequestDTO.getRecordDate(),
                recordCreateRequestDTO.getRecordPace(),
                recordCreateRequestDTO.getRunningStep()
        );

        Record savedRecord = recordRepository.save(record);
        // member 업데이트
        memberService.updateMemberRunningInfo(savedRecord) ;
        //daily 업데이트
        dailyRecordService.updateDailyRecord(savedRecord);
        // weekly 업데이트
        weeklyRecordService.updateWeeklyRecord(savedRecord);
        //monthly 업데이트
        monthlyRecordService.updateMonthlyRecord(savedRecord);
        return convertToResponseDTO(savedRecord);
    }

    public RecordResponseDTO getRecordById(Long id) {
        Record record = recordRepository.findById(id).orElseThrow(() -> new RuntimeException("Record not found"));
        return convertToResponseDTO(record);
    }

    public List<RecordResponseDTO> getAllRecords() {
        return recordRepository.findAll().stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public RecordResponseDTO updateRecord(Long id, RecordCreateRequestDTO recordCreateRequestDTO) {
        Record record = recordRepository.findById(id).orElseThrow(() -> new RuntimeException("Record not found"));

        record.setMember(memberService.getMemberEntity(recordCreateRequestDTO.getMemberId()));
        record.setRunningDistance(recordCreateRequestDTO.getRunningDistance());
        record.setRunningTime(recordCreateRequestDTO.getRunningTime());
        record.setRunningStep(recordCreateRequestDTO.getRunningStep());
        record.setRecordDate(recordCreateRequestDTO.getRecordDate());
        record.setRecordPace(recordCreateRequestDTO.getRecordPace());

        Record updatedRecord = recordRepository.save(record);
        return convertToResponseDTO(updatedRecord);
    }

    public void deleteRecord(Long id) {
        recordRepository.deleteById(id);
    }

    private RecordResponseDTO convertToResponseDTO(Record record) {
        return new RecordResponseDTO(
                record.getRecordId(),
                record.getMember().getMemberId(),
                record.getRunningDistance(),
                record.getRunningTime(),
                record.getRecordDate(),
                record.getRecordPace(),
                record.getRunningStep()
                );
    }

    public TodayRankingResponseDTO getMyRecordRanking(Long memberId, String recordId) {

        //'나'의 '이번' '레코드 기록'의 /  '오늘'의 '랭킹값' 가져오기 (sql 쿼리로 )
        Integer todayMyThisRanking = recordJDBCRepository.getTodayMyThisRanking(memberId,recordId);

        // '오늘' '전체 사용자' '레코드'의 '페이스' 값을 평균내서 가져오기
        Double todayTotalUserRecordAvgPace = recordJDBCRepository.getTodayTotalUserRecordAvgPace();

        // '오늘' '전체 (기록, 레코드수)' 가져오기
        Integer todayAllUserRecordCount = recordJDBCRepository.getTodayTotalRecordCount();

        // '랭킹' '리스폰스 디티오' 반환
        return new TodayRankingResponseDTO(todayMyThisRanking,todayTotalUserRecordAvgPace,todayAllUserRecordCount);
    }

}
