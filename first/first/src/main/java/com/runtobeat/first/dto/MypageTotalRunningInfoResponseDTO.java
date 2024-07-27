package com.runtobeat.first.dto;

import lombok.Getter;

@Getter
public class MypageTotalRunningInfoResponseDTO {
    private Double totalDistance;
    private Double myAvgPace;

    public MypageTotalRunningInfoResponseDTO(Double totalDistance, Double myAvgPace) {
        this.totalDistance = totalDistance;
        this.myAvgPace = myAvgPace;
    }
}
