package com.runtobeat.first.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "weekly_record")
@Getter
@Setter
public class WeeklyRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long weeklyRecordId;

    @ManyToOne
    @JoinColumn(name = "member_id", referencedColumnName = "memberId")
    private Member member;

    private Double weeklyTotalDistance;
    private Long weeklyTotalTime;
    private LocalDate yearWeek;
    private Double weeklyRecordPace;
    private Long weeklyRunningStep;
    private String weekYear;

    public WeeklyRecord(Member member, Double weeklyTotalDistance, Long weeklyTotalTime, LocalDate yearWeek, Double weeklyRecordPace, Long weeklyRunningStep, String weekYear) {
        this.member = member;
        this.weeklyTotalDistance = weeklyTotalDistance;
        this.weeklyTotalTime = weeklyTotalTime;
        this.yearWeek = yearWeek;
        this.weeklyRecordPace = weeklyRecordPace;
        this.weeklyRunningStep = weeklyRunningStep;
        this.weekYear = weekYear;
    }
}
