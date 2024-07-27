package com.runtobeat.first.service;

import com.runtobeat.first.dto.WeeklyRecordRequestDTO;
import com.runtobeat.first.entity.WeeklyRecord;
import com.runtobeat.first.repository.WeeklyRecordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@DataJpaTest
public class WeeklyRecordServiceTest {

    @Mock
    private WeeklyRecordRepository weeklyRecordRepository;

    @InjectMocks
    private WeeklyRecordService weeklyRecordService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createWeeklyRecord() {
        WeeklyRecordRequestDTO requestDTO = new WeeklyRecordRequestDTO("member1", 200.0, LocalTime.of(2, 0), "2024-W30", 5.0, 7000);
        WeeklyRecord weeklyRecord = new WeeklyRecord("member1", 200.0, LocalTime.of(2, 0), "2024-W30", 5.0, 7000);

        when(weeklyRecordRepository.save(any(WeeklyRecord.class))).thenReturn(weeklyRecord);

        WeeklyRecord result = weeklyRecordService.createWeeklyRecord(requestDTO);

        assertEquals(weeklyRecord, result);
        verify(weeklyRecordRepository, times(1)).save(any(WeeklyRecord.class));
    }

    @Test
    void getWeeklyRecordById() {
        String id = "weeklyRecord1";
        WeeklyRecord weeklyRecord = new WeeklyRecord("member1", 200.0, LocalTime.of(2, 0), "2024-W30", 5.0, 7000);

        when(weeklyRecordRepository.findById(id)).thenReturn(Optional.of(weeklyRecord));

        WeeklyRecord result = weeklyRecordService.getWeeklyRecordById(id);

        assertEquals(weeklyRecord, result);
        verify(weeklyRecordRepository, times(1)).findById(id);
    }
}
