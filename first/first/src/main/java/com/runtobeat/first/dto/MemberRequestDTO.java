package com.runtobeat.first.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@AllArgsConstructor
@Getter
@Setter
public class MemberRequestDTO {
    private String memberId;
    private String memberName;
    private Double totalDistance;
    private LocalTime totalTime;
    private Double avgPace;
}
