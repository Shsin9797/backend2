package com.runtobeat.first.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "monthly_record")
@Getter
@Setter
public class MonthlyRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long monthlyRecordId;

    @ManyToOne
    @JoinColumn(name = "member_id", referencedColumnName = "memberId")
    private Member member;

    private Double monthlyTotalDistance;
    private LocalTime monthlyTotalTime;
    private LocalDate yearMonth;
    private Double monthlyRecordPace;
    private Long monthlyRunningStep;
    private String monthYear;

    public MonthlyRecord(Member member, Double monthlyTotalDistance, LocalTime monthlyTotalTime, LocalDate yearMonth, Double monthlyRecordPace, Long monthlyRunningStep, String monthYear) {
        this.member = member;
        this.monthlyTotalDistance = monthlyTotalDistance;
        this.monthlyTotalTime = monthlyTotalTime;
        this.yearMonth = yearMonth;
        this.monthlyRecordPace = monthlyRecordPace;
        this.monthlyRunningStep = monthlyRunningStep;
        this.monthYear = monthYear;
    }
}

