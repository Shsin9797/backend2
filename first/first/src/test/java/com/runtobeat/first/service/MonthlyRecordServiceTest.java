package com.runtobeat.first.service;

import com.runtobeat.first.dto.MonthlyRecordRequestDTO;
import com.runtobeat.first.entity.MonthlyRecord;
import com.runtobeat.first.repository.MonthlyRecordRepository;
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
public class MonthlyRecordServiceTest {

    @Mock
    private MonthlyRecordRepository monthlyRecordRepository;

    @InjectMocks
    private MonthlyRecordService monthlyRecordService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createMonthlyRecord() {
        MonthlyRecordRequestDTO requestDTO = new MonthlyRecordRequestDTO("member1", 500.0, LocalTime.of(5, 0), "2024-07", 5.0, 15000, "2024-07");
        MonthlyRecord monthlyRecord = new MonthlyRecord("member1", 500.0, LocalTime.of(5, 0), LocalDate.of(2024, 7, 1), 5.0, 15000L, "2024-07");

        when(monthlyRecordRepository.save(any(MonthlyRecord.class))).thenReturn(monthlyRecord);

        MonthlyRecord result = monthlyRecordService.createMonthlyRecord(requestDTO);

        assertEquals(monthlyRecord, result);
        verify(monthlyRecordRepository, times(1)).save(any(MonthlyRecord.class));
    }

    @Test
    void getMonthlyRecordById() {
        String id = "monthlyRecord1";
        MonthlyRecord monthlyRecord = new MonthlyRecord("member1", 500.0, LocalTime.of(5, 0), LocalDate.of(2024, 7, 1), 5.0, 15000L, "2024-07");

        when(monthlyRecordRepository.findById(id)).thenReturn(Optional.of(monthlyRecord));

        MonthlyRecord result = monthlyRecordService.getMonthlyRecordById(id);

        assertEquals(monthlyRecord, result);
        verify(monthlyRecordRepository, times(1)).findById(id);
    }
}
