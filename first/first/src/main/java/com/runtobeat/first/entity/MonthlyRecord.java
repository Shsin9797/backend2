package com.runtobeat.first.entity;

import java.time.LocalDate;

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
    private Long monthlyTotalDistance;
    private Long monthlyTotalTime;
    private LocalDate yearMonth;
    private Double monthlyRecordPace;

}
