package com.runtobeat.first.service;

import com.runtobeat.first.dto.MemberCreateRequestDTO;
import com.runtobeat.first.dto.MemberResponseDTO;
import com.runtobeat.first.dto.MypageTotalRunningInfoResponseDTO;
import com.runtobeat.first.dto.RecordCreateRequestDTO;
import com.runtobeat.first.entity.Member;
import com.runtobeat.first.entity.Record;
import com.runtobeat.first.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public MemberResponseDTO createMember(MemberCreateRequestDTO memberCreateRequestDTO) {
        Member member = new Member(memberCreateRequestDTO.getMemberName(), 0.0, 0L, 0.0);
        Member savedMember = memberRepository.save(member);
        return new MemberResponseDTO(
                savedMember.getMemberId(),
                savedMember.getMemberName(),
                savedMember.getTotalDistance(),
                savedMember.getTotalTime(),
                savedMember.getAvgPace());
    }

    public MemberResponseDTO getMember(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
        return new MemberResponseDTO(
                member.getMemberId(),
                member.getMemberName(),
                member.getTotalDistance(),
                member.getTotalTime(),
                member.getAvgPace()
        );
    }

    public Member getMemberEntity(Long memberId) {
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

//    public MemberResponseDTO updateMember(Long memberId, MemberCreateRequestDTO memberCreateRequestDTO) {
//        Member member = memberRepository.findById(memberId).orElseThrow(() -> new RuntimeException("Member not found"));
//        member.setMemberName(memberCreateRequestDTO.getMemberName());
//
//        member.setTotalDistance(memberCreateRequestDTO.getTotalDistance());
//        member.setTotalTime(memberCreateRequestDTO.getTotalTime());
//        member.setAvgPace(memberCreateRequestDTO.getAvgPace());
//
//        Member updatedMember = memberRepository.save(member);
//        return new MemberResponseDTO(
//                updatedMember.getMemberId(),
//                updatedMember.getMemberName(),
//                updatedMember.getTotalDistance(),
//                updatedMember.getTotalTime(),
//                updatedMember.getAvgPace()
//        );
//    }

    public MemberResponseDTO updateMemberRecord(RecordCreateRequestDTO recordCreateRequestDTO) {
        Long memberId = recordCreateRequestDTO.getMemberId();
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new RuntimeException("Member not found"));
        member.setTotalDistance(member.getTotalDistance() + recordCreateRequestDTO.getRunningDistance());
        // recordCreateRequestDTO에서 시간을 초 단위로 가져오기
        Long runningTime = recordCreateRequestDTO.getRunningTime(); // 이미 초 단위로 제공된다고 가정

        // 회원의 총 시간을 초 단위로 가져오기
        Long totalMemberTime = member.getTotalTime(); // 초 단위로 저장된 총 시간

        // 새로운 시간을 기존 총 시간에 더하기
        Long newTotalTime = totalMemberTime + runningTime;

        // 업데이트된 총 시간을 회원 객체에 설정
        member.setTotalTime(newTotalTime);

        member.setAvgPace(recordCreateRequestDTO.getRecordPace());
        Member updatedMember = memberRepository.save(member);
        return new MemberResponseDTO(
                updatedMember.getMemberId(),
                updatedMember.getMemberName(),
                updatedMember.getTotalDistance(),
                updatedMember.getTotalTime(),
                updatedMember.getAvgPace()
        );
    }

    public void deleteMember(Long memberId) {
        memberRepository.deleteById(memberId);
    }

    public MypageTotalRunningInfoResponseDTO getMemberRunningInfo(Long memberId) {
        Member member = memberRepository.findById(memberId).get();
        return new MypageTotalRunningInfoResponseDTO(member.getTotalDistance(), member.getAvgPace());
    }

    public void updateMemberRunningInfo(Record savedRecord) {
        Member originMember = memberRepository.findById(savedRecord.getMember().getMemberId()).get();
        Double newDistance = originMember.getTotalDistance() + savedRecord.getRunningDistance();

        Long totalTime = originMember.getTotalTime();
        Long runningTime = savedRecord.getRunningTime();

        Long newTime = totalTime + runningTime;

        Double newPaceDouble = (newTime / newDistance);

        originMember.setTotalDistance(newDistance);
        originMember.setTotalTime(newTime);
        originMember.setAvgPace(newPaceDouble);

        memberRepository.save(originMember);
    }

}
