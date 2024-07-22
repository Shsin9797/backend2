package com.runtobeat.first.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "Record")
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String recordId;
    private String memberId;
    private Long runningDistance;
    private LocalTime runningTime;
    private Long runningStep;
    private LocalDate recordDate;
    private Double recordPace;

    public Record() {
    }

    public Record(String recordId, String memberId, Long runningDistance, LocalTime runningTime, Long runningStep, LocalDate recordDate, Double recordPace) {
        this.recordId = recordId;
        this.memberId = memberId;
        this.runningDistance = runningDistance;
        this.runningTime = runningTime;
        this.runningStep = runningStep;
        this.recordDate = recordDate;
        this.recordPace = recordPace;
    }
}
