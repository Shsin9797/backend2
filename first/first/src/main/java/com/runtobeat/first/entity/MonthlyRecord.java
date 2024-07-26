package com.runtobeat.first.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "MonthlyRecord")
@Getter
@Setter
public class MonthlyRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String monthlyRecordId;
    private String memberId;
    private Double monthlyTotalDistance;
    private LocalTime monthlyTotalTime;
    private LocalDate yearMonth;
    private Double monthlyRecordPace;
    private Long monthlyRunningStep;
    private String monthYear;

    public MonthlyRecord(Double monthlyTotalDistance, LocalTime monthlyTotalTime, LocalDate yearMonth, Double monthlyRecordPace, Long monthlyRunningStep, String monthYear) {
        this.monthlyTotalDistance = monthlyTotalDistance;
        this.monthlyTotalTime = monthlyTotalTime;
        this.yearMonth = yearMonth;
        this.monthlyRecordPace = monthlyRecordPace;
        this.monthlyRunningStep = monthlyRunningStep;
        this.monthYear = monthYear;
    }

    public MonthlyRecord(String memberId, Double monthlyTotalDistance, LocalTime monthlyTotalTime, LocalDate yearMonth, Double monthlyRecordPace, Long monthlyRunningStep, String monthYear) {
        this.monthlyTotalDistance = monthlyTotalDistance;
        this.monthlyTotalTime = monthlyTotalTime;
        this.yearMonth = yearMonth;
        this.monthlyRecordPace = monthlyRecordPace;
        this.monthlyRunningStep = monthlyRunningStep;
        this.monthYear = monthYear;
    }
}

