package com.runtobeat.first.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "Record")
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recordId;

    @ManyToOne
    @JoinColumn(name = "member_id", referencedColumnName = "memberId")
    private Member member;

    private Double runningDistance;
    private Long runningTime;
    private LocalDate recordDate;
    private Double recordPace;
    private Long runningStep;

    public Record(Member member, Double runningDistance, Long runningTime, LocalDate recordDate, Double recordPace, Long runningStep) {
        this.member = member;
        this.runningDistance = runningDistance;
        this.runningTime = runningTime;
        this.recordDate = recordDate;
        this.recordPace = recordPace;
        this.runningStep = runningStep;
    }
}
