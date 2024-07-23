package com.runtobeat.first.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MemberResponseDTO {
    private String memberId;
    private String memberName;
    private Double totalDistance;
    private Double totalTime;
    private Double avgPace;
}
