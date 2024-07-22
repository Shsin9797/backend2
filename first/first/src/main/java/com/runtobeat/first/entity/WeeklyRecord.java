package com.runtobeat.first.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
@Entity
@Table(name = "WeeklyRecord")

public class WeeklyRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String weeklyRecordId;
    private String memberId;
    private Long weeklyTotalDistance;
    private Long weeklyTotalTime;
    private LocalDate yearWeek;
    private Double weeklyRecordPace;

    public WeeklyRecord() {
    }

    public WeeklyRecord(String weeklyRecordId, String memberId, Long weeklyTotalDistance, Long weeklyTotalTime, LocalDate yearWeek, Double weeklyRecordPace) {
        this.weeklyRecordId = weeklyRecordId;
        this.memberId = memberId;
        this.weeklyTotalDistance = weeklyTotalDistance;
        this.weeklyTotalTime = weeklyTotalTime;
        this.yearWeek = yearWeek;
        this.weeklyRecordPace = weeklyRecordPace;
    }
}
