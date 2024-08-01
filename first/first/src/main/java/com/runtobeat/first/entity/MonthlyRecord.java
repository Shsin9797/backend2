package com.runtobeat.first.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

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
    private Long monthlyTotalTime;
    private LocalDate yearMonths;
    private Double monthlyRecordPace;
    private Long monthlyRunningStep;
    private String monthYears;

    public MonthlyRecord(Member member, Double monthlyTotalDistance, Long monthlyTotalTime, LocalDate yearMonths, Double monthlyRecordPace, Long monthlyRunningStep, String monthYears) {
        this.member = member;
        this.monthlyTotalDistance = monthlyTotalDistance;
        this.monthlyTotalTime = monthlyTotalTime;
        this.yearMonths = yearMonths;
        this.monthlyRecordPace = monthlyRecordPace;
        this.monthlyRunningStep = monthlyRunningStep;
        this.monthYears = monthYears;
    }
}

