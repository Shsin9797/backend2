package com.runtobeat.first.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class RecordJDBCRepositoryTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Mock
    private RecordRepository recordRepository;

    private RecordJDBCRepository recordJDBCRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        recordJDBCRepository = new RecordJDBCRepository(jdbcTemplate, recordRepository);
    }

    @Test
    void getTodayMyThisRanking_ShouldReturnCorrectRank() {
        String memberId = "member1";
        String recordId = "record1";
        Integer expectedRank = 5;

        when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), eq(Integer.class)))
                .thenReturn(expectedRank);

        Integer result = recordJDBCRepository.getTodayMyThisRanking(memberId, recordId);

        assertEquals(expectedRank, result);
        verify(jdbcTemplate).queryForObject(anyString(), eq(new Object[]{memberId, recordId}), eq(Integer.class));
    }

    @Test
    void getTodayMyThisRanking2_ShouldReturnCorrectRanking() {
        String memberId = "member1";
        String recordId = "record1";
        Integer expectedRanking = 3;

        when(jdbcTemplate.queryForObject(anyString(), eq(Integer.class), eq(recordId), eq(memberId)))
                .thenReturn(expectedRanking);

        Integer result = recordJDBCRepository.getTodayMyThisRanking2(memberId, recordId);

        assertEquals(expectedRanking, result);
        verify(jdbcTemplate).queryForObject(anyString(), eq(Integer.class), eq(recordId), eq(memberId));
    }

    @Test
    void getTodayTotalUserRecordAvgPace_ShouldReturnCorrectAverage() {
        Double expectedAvgPace = 5.5;

        when(jdbcTemplate.queryForObject(anyString(), eq(Double.class)))
                .thenReturn(expectedAvgPace);

        Double result = recordJDBCRepository.getTodayTotalUserRecordAvgPace();

        assertEquals(expectedAvgPace, result);
        verify(jdbcTemplate).queryForObject(anyString(), eq(Double.class));
    }

    @Test
    void getTodayTotalRecordCount_ShouldReturnCorrectCount() {
        Integer expectedCount = 10;

        when(jdbcTemplate.queryForObject(anyString(), eq(Integer.class)))
                .thenReturn(expectedCount);

        Integer result = recordJDBCRepository.getTodayTotalRecordCount();

        assertEquals(expectedCount, result);
        verify(jdbcTemplate).queryForObject(anyString(), eq(Integer.class));
    }
}