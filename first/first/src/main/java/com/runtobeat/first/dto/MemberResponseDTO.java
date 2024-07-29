package com.runtobeat.first.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MemberResponseDTO {
    private Long memberId;
    private String memberName;
    private Double totalDistance;
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime totalTime;
    private Double avgPace;

    public MemberResponseDTO(String memberName, Double totalDistance, LocalTime totalTime, Double avgPace) {
        this.memberName = memberName;
        this.totalDistance = totalDistance;
        this.totalTime = totalTime;
        this.avgPace = avgPace;
    }
}
