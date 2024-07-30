package com.runtobeat.first.controller;

import com.runtobeat.first.dto.MemberCreateRequestDTO;
import com.runtobeat.first.dto.MemberResponseDTO;
import com.runtobeat.first.dto.RecordCreateRequestDTO;
import com.runtobeat.first.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @PostMapping
    public ResponseEntity<MemberResponseDTO> createMember(@RequestBody MemberCreateRequestDTO memberCreateRequestDTO) {
        MemberResponseDTO memberResponseDTO = memberService.createMember(memberCreateRequestDTO);
        return ResponseEntity.ok(memberResponseDTO);
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<MemberResponseDTO> getMember(@PathVariable Long memberId) {
        MemberResponseDTO memberResponseDTO = memberService.getMember(memberId);
        return ResponseEntity.ok(memberResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<MemberResponseDTO>> getAllMembers() {
        List<MemberResponseDTO> members = memberService.getAllMembers();
        return ResponseEntity.ok(members);
    }

    @PutMapping("/{memberId}")
    public ResponseEntity<MemberResponseDTO> updateMemberRecord(@PathVariable Long memberId, @RequestBody RecordCreateRequestDTO recordCreateRequestDTO) {
        MemberResponseDTO memberResponseDTO = memberService.updateMemberRecord(recordCreateRequestDTO);
        return ResponseEntity.ok(memberResponseDTO);
    }


    @DeleteMapping("/{memberId}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long memberId) {
        memberService.deleteMember(memberId);
        return ResponseEntity.noContent().build();
    }



}
