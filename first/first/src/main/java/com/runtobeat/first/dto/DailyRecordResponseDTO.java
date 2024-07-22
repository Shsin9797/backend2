package com.runtobeat.first.dto;

import java.time.LocalDate;

public class DailyRecordResponseDTO {
    private String dailyRecordId;
    private Double dailyTotalDistance;
    private Double dailyTotalTime;
    private LocalDate yearMonthDate;
    private Double dailyRecordPace;

    public DailyRecordResponseDTO(String dailyRecordId, Double dailyTotalDistance, Double dailyTotalTime, LocalDate yearMonthDate, Double dailyRecordPace) {
        this.dailyRecordId = dailyRecordId;
        this.dailyTotalDistance = dailyTotalDistance;
        this.dailyTotalTime = dailyTotalTime;
        this.yearMonthDate = yearMonthDate;
        this.dailyRecordPace = dailyRecordPace;
    }
}
