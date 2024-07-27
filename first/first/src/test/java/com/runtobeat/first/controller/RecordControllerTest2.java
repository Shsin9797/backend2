package com.runtobeat.first.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.runtobeat.first.dto.RecordRequestDTO;
import com.runtobeat.first.dto.RecordResponseDTO;
import com.runtobeat.first.dto.TodayRankingResponseDTO;
import com.runtobeat.first.service.RecordService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(RecordController.class)
public class RecordControllerTest2 {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RecordService recordService;

    @Autowired
    private ObjectMapper objectMapper;

    private RecordRequestDTO recordRequestDTO;
    private RecordResponseDTO recordResponseDTO;
    private TodayRankingResponseDTO todayRankingResponseDTO;

    @BeforeEach
    void setUp() {
        recordRequestDTO = new RecordRequestDTO(
                "member1",
                10.5,
                LocalTime.of(1, 30, 0),
                15000L,
                LocalDate.now(),
                4.2
        );

        recordResponseDTO = new RecordResponseDTO();
        recordResponseDTO.setRecordId("record1");
        recordResponseDTO.setMemberId("member1");
        recordResponseDTO.setRunningDistance(10.5);
        recordResponseDTO.setRunningTime(LocalTime.of(1, 30, 0));
        recordResponseDTO.setRunningStep(15000L);
        recordResponseDTO.setRecordDate(LocalDate.now());
        recordResponseDTO.setRecordPace(4.2);

        todayRankingResponseDTO = new TodayRankingResponseDTO(
                5,
                4.5,
                100
        );
    }

    @Test
    void createRecord_ShouldReturnCreatedRecord() throws Exception {
        when(recordService.createRecord(any(RecordRequestDTO.class))).thenReturn(recordResponseDTO);

        mockMvc.perform(post("/record")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(recordRequestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.recordId").value("record1"))
                .andExpect(jsonPath("$.memberId").value("member1"))
                .andExpect(jsonPath("$.runningDistance").value(10.5))
                .andExpect(jsonPath("$.runningTime").value("01:30:00"))
                .andExpect(jsonPath("$.runningStep").value(15000))
                .andExpect(jsonPath("$.recordDate").value(LocalDate.now().toString()))
                .andExpect(jsonPath("$.recordPace").value(4.2));
    }

        @Test
        void getMyRanking_ShouldReturnRanking() throws Exception {
            // Given
            String memberId = "member1";
            String recordId = "record1";
            TodayRankingResponseDTO todayRankingResponseDTO = new TodayRankingResponseDTO(5, 4.5, 100);

            when(recordService.getMyRecordRanking(anyString(), anyString())).thenReturn(todayRankingResponseDTO);

            // When & Then
            mockMvc.perform(get("/record/rank/{memberId}", memberId)
                            .param("recordId", recordId)
                            .accept("application/json")) // Accept 헤더 추가
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.todayMyThisStoryRanking").value(5))
                    .andExpect(jsonPath("$.todayTotalUserAvgPace").value(4.5))
                    .andExpect(jsonPath("$.todayTotalUserRecordCount").value(100));
        }



    @Test
    void getRecordById_ShouldReturnRecord() throws Exception {
        when(recordService.getRecordById(anyString())).thenReturn(recordResponseDTO);

        mockMvc.perform(get("/record/{id}", "record1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.recordId").value("record1"));
    }

    @Test
    void getAllRecords_ShouldReturnAllRecords() throws Exception {
        List<RecordResponseDTO> records = Arrays.asList(recordResponseDTO, recordResponseDTO);
        when(recordService.getAllRecords()).thenReturn(records);

        mockMvc.perform(get("/record"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void updateRecord_ShouldReturnUpdatedRecord() throws Exception {
        when(recordService.updateRecord(anyString(), any(RecordRequestDTO.class))).thenReturn(recordResponseDTO);

        mockMvc.perform(put("/record/{id}", "record1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(recordRequestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.recordId").value("record1"))
                .andExpect(jsonPath("$.memberId").value("member1"))
                .andExpect(jsonPath("$.runningDistance").value(10.5))
                .andExpect(jsonPath("$.runningTime").value("01:30:00"))
                .andExpect(jsonPath("$.runningStep").value(15000))
                .andExpect(jsonPath("$.recordDate").value(LocalDate.now().toString()))
                .andExpect(jsonPath("$.recordPace").value(4.2));
    }

    @Test
    void deleteRecord_ShouldReturnNoContent() throws Exception {
        doNothing().when(recordService).deleteRecord(anyString());

        mockMvc.perform(delete("/record/{id}", "record1"))
                .andExpect(status().isNoContent());
    }
}
