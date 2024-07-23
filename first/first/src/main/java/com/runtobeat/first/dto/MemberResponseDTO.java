package com.runtobeat.first.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class MemberResponseDTO {
    private String memberId;
    private String memberName;
    private Double totalDistance;
    private Double totalTime;
    private Double avgPace;
}
