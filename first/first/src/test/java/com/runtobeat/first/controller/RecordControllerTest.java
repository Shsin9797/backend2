//package com.runtobeat.first.controller;
//
//import com.runtobeat.first.dto.TodayRankingResponseDTO;
//import com.runtobeat.first.dto.RecordRequestDTO;
//import com.runtobeat.first.dto.RecordResponseDTO;
//import com.runtobeat.first.service.RecordService;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import java.time.LocalDate;
//import java.time.LocalTime;
//import java.util.Arrays;
//import java.util.List;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.runtobeat.first.dto.TodayRankingResponseDTO;
//import com.runtobeat.first.dto.RecordRequestDTO;
//import com.runtobeat.first.dto.RecordResponseDTO;
//import com.runtobeat.first.service.RecordService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.mockito.ArgumentMatchers.*;
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@WebMvcTest(RecordController.class)
//public class RecordControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private RecordService recordService;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    private RecordRequestDTO recordRequestDTO;
//    private RecordResponseDTO recordResponseDTO;
//    private TodayRankingResponseDTO todayRankingResponseDTO;
//
//    @BeforeEach
//    void setUp() {
//        recordRequestDTO = new RecordRequestDTO(
//                "member1",
//                10.5,  // runningDistance
//                LocalTime.of(1, 30, 0),  // runningTime (1시간 30분)
//                15000L,  // runningStep
//                LocalDate.now(),  // recordDate
//                4.2  // recordPace (예: 4.2분/km)
//        );
//
//        recordResponseDTO = new RecordResponseDTO();
//        recordResponseDTO.setRecordId("record1");
//        recordResponseDTO.setMemberId("member1");
//        recordResponseDTO.setRunningDistance(10.5);
//        recordResponseDTO.setRunningTime(LocalTime.of(1, 30, 0));
//        recordResponseDTO.setRunningStep(15000L);
//        recordResponseDTO.setRecordDate(LocalDate.now());
//        recordResponseDTO.setRecordPace(4.2);
//
//        todayRankingResponseDTO = new TodayRankingResponseDTO(
//                5,      // todayMyThisStoryRanking
//                4.5,    // todayTotalUserAvgPace
//                100     // todayTotalUserRecordCount
//        );
//    }
//
//
//    @Test
//    public void testCreateRecord() throws Exception {
//        RecordResponseDTO recordResponse = new RecordResponseDTO();
//        Mockito.when(recordService.createRecord(any(RecordRequestDTO.class))).thenReturn(recordResponse);
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/record")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{\"field1\": \"value1\", \"field2\": \"value2\"}"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.field1").value("value1"));  // Adjust this to match your DTO fields
//    }
//
//    @Test
//    public void testGetMyRanking() throws Exception {
//        TodayRankingResponseDTO rankingResponse = new TodayRankingResponseDTO(1,10D,10);
//        Mockito.when(recordService.getMyRecordRanking(anyString(), anyString())).thenReturn(rankingResponse);
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/record/rank/member123")
//                        .param("recordId", "record456"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.ranking").value(1));
//    }
//
//    @Test
//    public void testGetRecordById() throws Exception {
//        RecordResponseDTO recordResponse = new RecordResponseDTO();
//        Mockito.when(recordService.getRecordById(anyString())).thenReturn(recordResponse);
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/record/record123"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
//    }
//
//    @Test
//    public void testGetAllRecords() throws Exception {
//        RecordResponseDTO record1 = new RecordResponseDTO();
//        RecordResponseDTO record2 = new RecordResponseDTO();
//        List<RecordResponseDTO> records = Arrays.asList(record1, record2);
//        Mockito.when(recordService.getAllRecords()).thenReturn(records);
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/record"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.length()").value(2));
//    }
//
//    @Test
//    public void testUpdateRecord() throws Exception {
//        RecordResponseDTO recordResponse = new RecordResponseDTO();
//        Mockito.when(recordService.updateRecord(anyString(), any(RecordRequestDTO.class))).thenReturn(recordResponse);
//
//        mockMvc.perform(MockMvcRequestBuilders.put("/record/record123")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{\"field1\": \"value1\", \"field2\": \"value2\"}"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
//    }
//
//    @Test
//    public void testDeleteRecord() throws Exception {
//        Mockito.doNothing().when(recordService).deleteRecord(anyString());
//
//        mockMvc.perform(MockMvcRequestBuilders.delete("/record/record123"))
//                .andExpect(status().isNoContent());
//    }
//
//
//    @Test
//    void createRecord_ShouldReturnCreatedRecord() throws Exception {
//        when(recordService.createRecord(any(RecordRequestDTO.class))).thenReturn(recordResponseDTO);
//
//        mockMvc.perform(post("/record")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(recordRequestDTO)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(recordResponseDTO.getRecordId()));
//    }
//
//    @Test
//    void getMyRanking_ShouldReturnRanking() throws Exception {
//
//        when(recordService.getMyRecordRanking(anyString(), anyString())).thenReturn(rankingDTO);
//
//        mockMvc.perform(get("/record/rank/{memberId}", "member1")
//                        .param("recordId", "record1"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.ranking").value(rankingDTO.getRanking()));
//    }
//
//    @Test
//    void getRecordById_ShouldReturnRecord() throws Exception {
//        when(recordService.getRecordById(anyString())).thenReturn(recordResponseDTO);
//
//        mockMvc.perform(get("/record/{id}", "record1"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(recordResponseDTO.getId()));
//    }
//
//    @Test
//    void getAllRecords_ShouldReturnAllRecords() throws Exception {
//        List<RecordResponseDTO> records = Arrays.asList(recordResponseDTO, recordResponseDTO);
//        when(recordService.getAllRecords()).thenReturn(records);
//
//        mockMvc.perform(get("/record"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.length()").value(2));
//    }
//
//    @Test
//    void updateRecord_ShouldReturnUpdatedRecord() throws Exception {
//        when(recordService.updateRecord(anyString(), any(RecordRequestDTO.class))).thenReturn(recordResponseDTO);
//
//        mockMvc.perform(put("/record/{id}", "record1")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(recordRequestDTO)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(recordResponseDTO.getId()));
//    }
//
//    @Test
//    void deleteRecord_ShouldReturnNoContent() throws Exception {
//        doNothing().when(recordService).deleteRecord(anyString());
//
//        mockMvc.perform(delete("/record/{id}", "record1"))
//                .andExpect(status().isNoContent());
//    }
//
//    @Test
//    void createRecord_ShouldReturnCreatedRecord2() throws Exception {
//        when(recordService.createRecord(any(RecordRequestDTO.class))).thenReturn(recordResponseDTO);
//
//        mockMvc.perform(post("/record")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(recordRequestDTO)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value("record1"))
//                .andExpect(jsonPath("$.memberId").value("member1"))
//                .andExpect(jsonPath("$.runningDistance").value(10.5))
//                .andExpect(jsonPath("$.runningTime").value("01:30:00"))
//                .andExpect(jsonPath("$.runningStep").value(15000))
//                .andExpect(jsonPath("$.recordDate").value(LocalDate.now().toString()))
//                .andExpect(jsonPath("$.recordPace").value(4.2));
//    }
//
//    // ... 다른 테스트 메소드들 ...
//
//    @Test
//    void updateRecord_ShouldReturnUpdatedRecord2() throws Exception {
//        when(recordService.updateRecord(anyString(), any(RecordRequestDTO.class))).thenReturn(recordResponseDTO);
//
//        mockMvc.perform(put("/record/{id}", "record1")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(recordRequestDTO)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value("record1"))
//                .andExpect(jsonPath("$.memberId").value("member1"))
//                .andExpect(jsonPath("$.runningDistance").value(10.5))
//                .andExpect(jsonPath("$.runningTime").value("01:30:00"))
//                .andExpect(jsonPath("$.runningStep").value(15000))
//                .andExpect(jsonPath("$.recordDate").value(LocalDate.now().toString()))
//                .andExpect(jsonPath("$.recordPace").value(4.2));
//    }
//
//}
