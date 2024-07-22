package com.runtobeat.first.entity;

import java.time.LocalDate;
import jakarta.persistence.*;
@Entity
@Table(name = "MonthlyRecord")
public class MonthlyRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String monthlyRecordId;
    private String memberId;
    private Long monthlyTotalDistance;
    private Long monthlyTotalTime;
    private LocalDate yearMonth;
    private Double monthlyRecordPace;

    public MonthlyRecord() {

    }

    public MonthlyRecord(String monthlyRecordId, String memberId, Long monthlyTotalDistance, Long monthlyTotalTime, LocalDate yearMonth, Double monthlyRecordPace) {
        this.monthlyRecordId = monthlyRecordId;
        this.memberId = memberId;
        this.monthlyTotalDistance = monthlyTotalDistance;
        this.monthlyTotalTime = monthlyTotalTime;
        this.yearMonth = yearMonth;
        this.monthlyRecordPace = monthlyRecordPace;
    }


}
