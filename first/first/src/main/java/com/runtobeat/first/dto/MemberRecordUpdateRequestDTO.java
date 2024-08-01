package com.runtobeat.first.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MemberRecordUpdateRequestDTO {
    private Double totalDistance;
    private Long totalTime;
    private Double avgPace;
}
