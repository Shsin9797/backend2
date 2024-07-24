package com.runtobeat.first.entity;

import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Dailyrecord")
@Getter
@Setter
public class DailyRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String dailyRecordId;
    private Double dailyTotalDistance;
    private Double dailyTotalTime;
    private LocalDate yearMonthDate;
    private Double dailyRecordPace;
}
