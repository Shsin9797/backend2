package com.runtobeat.first.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "Member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String memberId;
    private String memberName;
    private Double totalDistance; // 타입확인
    private Double totalTime; // 타입확인
    private Double avgPace;
}
