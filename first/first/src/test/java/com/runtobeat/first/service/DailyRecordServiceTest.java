package com.runtobeat.first.service;

import com.runtobeat.first.dto.DailyRecordRequestDTO;
import com.runtobeat.first.dto.DailyRecordResponseDTO;
import com.runtobeat.first.entity.DailyRecord;
import com.runtobeat.first.repository.DailyRecordRepository;
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
public class DailyRecordServiceTest {

    @Mock
    private DailyRecordRepository dailyRecordRepository;

    @InjectMocks
    private DailyRecordService dailyRecordService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createDailyRecord() {
        DailyRecordRequestDTO requestDTO = new DailyRecordRequestDTO(100.0, LocalTime.of(1, 0), LocalDate.now(), 5.0, 5000);
        DailyRecord dailyRecord = new DailyRecord(100.0, LocalTime.of(1, 0), LocalDate.now(), 5.0, 5000);

        when(dailyRecordRepository.save(any(DailyRecord.class))).thenReturn(dailyRecord);

        DailyRecord result = dailyRecordService.createDailyRecord(requestDTO);

        assertEquals(dailyRecord, result);
        verify(dailyRecordRepository, times(1)).save(any(DailyRecord.class));
    }

    @Test
    void getDailyRecordByMemberId() {
        String memberId = "member1";
        DailyRecord dailyRecord = new DailyRecord(100.0, LocalTime.of(1, 0), LocalDate.now(), 5.0, 5000);

        when(dailyRecordRepository.findById(memberId)).thenReturn(Optional.of(dailyRecord));

        DailyRecord result = dailyRecordService.getDailyRecordByMemberId(memberId);

        assertEquals(dailyRecord, result);
        verify(dailyRecordRepository, times(1)).findById(memberId);
    }
}
