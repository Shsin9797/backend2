package com.runtobeat.first.service;

import com.runtobeat.first.dto.MemberRequestDTO;
import com.runtobeat.first.dto.MemberResponseDTO;
import com.runtobeat.first.dto.MypageTotalRunningInfoResponseDTO;
import com.runtobeat.first.dto.RecordRequestDTO;
import com.runtobeat.first.entity.Member;
import com.runtobeat.first.entity.Record;
import com.runtobeat.first.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public MemberResponseDTO createMember(MemberRequestDTO memberRequestDTO) {
        Member member = new Member(
                memberRequestDTO.getMemberId(),
                memberRequestDTO.getMemberName(),
                memberRequestDTO.getTotalDistance(),
                memberRequestDTO.getTotalTime(),
                memberRequestDTO.getAvgPace()
        );
        Member savedMember = memberRepository.save(member);
        return new MemberResponseDTO(
                savedMember.getMemberId(),
                savedMember.getMemberName(),
                savedMember.getTotalDistance(),
                savedMember.getTotalTime(),
                savedMember.getAvgPace()
        );
    }

    public MemberResponseDTO getMember(String memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
        return new MemberResponseDTO(
                member.getMemberId(),
                member.getMemberName(),
                member.getTotalDistance(),
                member.getTotalTime(),
                member.getAvgPace()
        );
    }

    public Member getMemberEntity(String memberId) {
        return memberRepository.findById(memberId).orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
    }

    public List<MemberResponseDTO> getAllMembers() {
        return memberRepository.findAll().stream().map(member -> new MemberResponseDTO(
                member.getMemberId(),
                member.getMemberName(),
                member.getTotalDistance(),
                member.getTotalTime(),
                member.getAvgPace()
        )).collect(Collectors.toList());
    }

    public MemberResponseDTO updateMember(String memberId, MemberRequestDTO memberRequestDTO) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new RuntimeException("Member not found"));
        member.setMemberName(memberRequestDTO.getMemberName());
        member.setTotalDistance(memberRequestDTO.getTotalDistance());
        member.setTotalTime(memberRequestDTO.getTotalTime());
        member.setAvgPace(memberRequestDTO.getAvgPace());
        Member updatedMember = memberRepository.save(member);
        return new MemberResponseDTO(
                updatedMember.getMemberId(),
                updatedMember.getMemberName(),
                updatedMember.getTotalDistance(),
                updatedMember.getTotalTime(),
                updatedMember.getAvgPace()
        );
    }

    public MemberResponseDTO updateMember(String memberId, RecordRequestDTO recordRequestDTO) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new RuntimeException("Member not found"));
        member.setTotalDistance(member.getTotalDistance() + recordRequestDTO.getRunningDistance());
        LocalTime time = recordRequestDTO.getRunningTime();
        member.setTotalTime(member.getTotalTime().plusHours(time.getHour()).plusMinutes(time.getMinute()).plusSeconds(time.getSecond()));
        member.setAvgPace(recordRequestDTO.getRecordPace());
        Member updatedMember = memberRepository.save(member);
        return new MemberResponseDTO(
                updatedMember.getMemberId(),
                updatedMember.getMemberName(),
                updatedMember.getTotalDistance(),
                updatedMember.getTotalTime(),
                updatedMember.getAvgPace()
        );
    }

    public void deleteMember(String memberId) {
        memberRepository.deleteById(memberId);
    }

    public MypageTotalRunningInfoResponseDTO getMemberRunningInfo(String memberId) {
        Member member = memberRepository.findById(memberId).get();
        return new MypageTotalRunningInfoResponseDTO(member.getTotalDistance(), member.getAvgPace());
    }

    public void updateMemberRunningInfo(Record savedRecord) {
        Member originMember = memberRepository.findById(savedRecord.getMember().getMemberId()).get();
        Double newDistance = originMember.getTotalDistance() + savedRecord.getRunningDistance();

        LocalTime runningTime = savedRecord.getRunningTime();
        LocalTime newTime = originMember.getTotalTime()
                .plusHours(runningTime.getHour())
                .plusMinutes(runningTime.getMinute())
                .plusSeconds(runningTime.getSecond());

        Double newPaceDouble = (newTime.getHour()*3600 + newTime.getMinute()*60 + newTime.getSecond()) / newDistance ;

        originMember.setTotalDistance(newDistance);
        originMember.setTotalTime(newTime);
        originMember.setAvgPace(newPaceDouble);

        memberRepository.save(originMember);
    }


}
