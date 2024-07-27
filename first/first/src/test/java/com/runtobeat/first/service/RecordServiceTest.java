package com.runtobeat.first.service;

import com.runtobeat.first.dto.RecordRequestDTO;
import com.runtobeat.first.dto.RecordResponseDTO;
import com.runtobeat.first.entity.Record;
import com.runtobeat.first.repository.RecordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@DataJpaTest
public class RecordServiceTest {

    @Mock
    private RecordRepository recordRepository;

    @Mock
    private DailyRecordService dailyRecordService;

    @InjectMocks
    private RecordService recordService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createRecord() {
        RecordRequestDTO requestDTO = new RecordRequestDTO("member1", 5.0, LocalTime.of(0, 30), 15000L, LocalDate.now(), 6.0);
        Record record = new Record("record1", "member1", 5.0, LocalTime.of(0, 30), LocalDate.now(), 6.0, 15000L);

        when(recordRepository.save(any(Record.class))).thenReturn(record);
        doNothing().when(dailyRecordService).updateDailyRecord(any(Record.class)); // 수정된 부분

        RecordResponseDTO result = recordService.createRecord(requestDTO);

        assertEquals(new RecordResponseDTO("record1", "member1", 5.0, LocalTime.of(0, 30), 15000L, LocalDate.now(), 6.0), result);
        verify(recordRepository, times(1)).save(any(Record.class));
        verify(dailyRecordService, times(1)).updateDailyRecord(any(Record.class)); // 수정된 부분
    }

    @Test
    void getRecordById() {
        String recordId = "record1";
        Record record = new Record("record1", "member1", 5.0, LocalTime.of(0, 30), LocalDate.now(), 6.0, 15000L);

        when(recordRepository.findById(recordId)).thenReturn(Optional.of(record));

        RecordResponseDTO result = recordService.getRecordById(recordId);

        assertEquals(new RecordResponseDTO("record1", "member1", 5.0, LocalTime.of(0, 30), 15000L, LocalDate.now(), 3.0), result);
        verify(recordRepository, times(1)).findById(recordId);
    }
}
