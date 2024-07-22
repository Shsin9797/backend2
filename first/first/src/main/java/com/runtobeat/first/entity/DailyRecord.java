package com.runtobeat.first.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Dailyrecord")
public class DailyRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String dailyRecordId;
    private Double dailyTotalDistance;
    private Double dailyTotalTime;
    private LocalDate yearMonthDate;
    private Double dailyRecordPace;

    public DailyRecord() {
    }

    public DailyRecord(String dailyRecordId, Double dailyTotalDistance, Double dailyTotalTime, LocalDate yearMonthDate, Double dailyRecordPace) {
        this.dailyRecordId = dailyRecordId;
        this.dailyTotalDistance = dailyTotalDistance;
        this.dailyTotalTime = dailyTotalTime;
        this.yearMonthDate = yearMonthDate;
        this.dailyRecordPace = dailyRecordPace;
    }
}
