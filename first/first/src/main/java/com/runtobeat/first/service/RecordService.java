package com.runtobeat.first.service;

import com.runtobeat.first.dto.RecordRequestDTO;
import com.runtobeat.first.dto.RecordResponseDTO;
import com.runtobeat.first.entity.Record;
import com.runtobeat.first.repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecordService {

    private final RecordRepository recordRepository;

    public RecordResponseDTO createRecord(RecordRequestDTO recordRequestDTO) {
        Record record = new Record(
                recordRequestDTO.getRecordId(),
                recordRequestDTO.getMemberId(),
                recordRequestDTO.getRunningDistance(),
                recordRequestDTO.getRunningTime(),
                recordRequestDTO.getRunningStep(),
                recordRequestDTO.getRecordDate(),
                recordRequestDTO.getRecordPace()
        );

        Record savedRecord = recordRepository.save(record);
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
}
