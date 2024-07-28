package com.runtobeat.first.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@AllArgsConstructor
@Getter
@Setter
public class MemberRequestDTO {
    private Long memberId;
    private String memberName;
    private Double totalDistance;
    private LocalTime totalTime;
    private Double avgPace;

    public MemberRequestDTO(String memberName, Double totalDistance, LocalTime totalTime, Double avgPace) {
        this.memberName = memberName;
        this.totalDistance = totalDistance;
        this.totalTime = totalTime;
        this.avgPace = avgPace;
    }
}
