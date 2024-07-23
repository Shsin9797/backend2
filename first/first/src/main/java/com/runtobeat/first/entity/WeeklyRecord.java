package com.runtobeat.first.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Entity
@NoArgsConstructor
@AllArgsConstructor
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


}
