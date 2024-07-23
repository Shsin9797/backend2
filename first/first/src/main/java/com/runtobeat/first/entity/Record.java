package com.runtobeat.first.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
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


}
