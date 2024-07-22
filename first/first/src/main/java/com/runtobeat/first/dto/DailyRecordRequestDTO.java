package com.runtobeat.first.dto;

import java.time.LocalDate;

public class DailyRecordRequestDTO {
    private String dailyRecordId;
    private Double dailyTotalDistance;
    private Double dailyTotalTime;
    private LocalDate yearMonthDate;
    private Double dailyRecordPace;

    public DailyRecordRequestDTO(String dailyRecordId, Double dailyTotalDistance, Double dailyTotalTime, LocalDate yearMonthDate, Double dailyRecordPace) {
        this.dailyRecordId = dailyRecordId;
        this.dailyTotalDistance = dailyTotalDistance;
        this.dailyTotalTime = dailyTotalTime;
        this.yearMonthDate = yearMonthDate;
        this.dailyRecordPace = dailyRecordPace;
    }
}
