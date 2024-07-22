package com.runtobeat.first.dto;

public class MemberResponseDTO {
    private String memberId;
    private String memberName;
    private Double totalDistance;
    private Double totalTime;
    private Double avgPace;

    public MemberResponseDTO(String memberId, String memberName, Double totalDistance, Double totalTime, Double avgPace) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.totalDistance = totalDistance;
        this.totalTime = totalTime;
        this.avgPace = avgPace;
    }
}
