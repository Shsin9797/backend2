package com.runtobeat.first.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "Member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;
    private String memberName;
    private Double totalDistance; // 타입확인
    private LocalTime totalTime; // 타입확인
    private Double avgPace;

    public Member(String memberName, Double totalDistance, LocalTime totalTime, Double avgPace) {
        this.memberName = memberName;
        this.totalDistance = totalDistance;
        this.totalTime = totalTime;
        this.avgPace = avgPace;
    }
}
