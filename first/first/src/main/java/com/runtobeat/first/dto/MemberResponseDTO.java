package com.runtobeat.first.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@AllArgsConstructor
@Getter
public class MemberResponseDTO {
    private String memberId;
    private String memberName;
    private Double totalDistance;
    private LocalTime totalTime;
    private Double avgPace;

    public MemberResponseDTO(String memberName, Double totalDistance, LocalTime totalTime, Double avgPace) {
        this.memberName = memberName;
        this.totalDistance = totalDistance;
        this.totalTime = totalTime;
        this.avgPace = avgPace;
    }
}
