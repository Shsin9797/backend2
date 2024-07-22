package com.runtobeat.first.dto;

import java.time.LocalDate;

public class WeeklyRecordRequestDTO {
    private String weeklyRecordId;
    private String memberId;
    private Long weeklyTotalDistance;
    private Long weeklyTotalTime;
    private LocalDate yearWeek;
    private Double weeklyRecordPace;

    public WeeklyRecordRequestDTO(String weeklyRecordId, String memberId, Long weeklyTotalDistance, Long weeklyTotalTime, LocalDate yearWeek, Double weeklyRecordPace) {
        this.weeklyRecordId = weeklyRecordId;
        this.memberId = memberId;
        this.weeklyTotalDistance = weeklyTotalDistance;
        this.weeklyTotalTime = weeklyTotalTime;
        this.yearWeek = yearWeek;
        this.weeklyRecordPace = weeklyRecordPace;
    }
}
