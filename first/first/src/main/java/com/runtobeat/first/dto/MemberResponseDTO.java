package com.runtobeat.first.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MemberResponseDTO {
    private Long memberId;
    private String memberName;
    private Double totalDistance;
    private Long totalTime;
    private Double avgPace;

    public MemberResponseDTO(String memberName, Double totalDistance, Long totalTime, Double avgPace) {
        this.memberName = memberName;
        this.totalDistance = totalDistance;
        this.totalTime = totalTime;
        this.avgPace = avgPace;
    }
}
