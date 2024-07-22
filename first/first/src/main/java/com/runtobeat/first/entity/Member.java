package com.runtobeat.first.entity;

import jakarta.persistence.*;

@Entity
@Table(name ="Member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String memberId;
    private String memberName;
    private Double totalDistance; // 타입확인
    private Double totalTime; // 타입확인
    private Double avgPace;

    public Member() {
    }

    public Member(String memberId, String memberName, Double totalDistance, Double totalTime, Double avgPace) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.totalDistance = totalDistance;
        this.totalTime = totalTime;
        this.avgPace = avgPace;
    }
}
