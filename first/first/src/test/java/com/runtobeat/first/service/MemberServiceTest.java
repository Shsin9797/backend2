package com.runtobeat.first.service;

import com.runtobeat.first.dto.MemberRequestDTO;
import com.runtobeat.first.dto.MemberResponseDTO;
import com.runtobeat.first.entity.Member;
import com.runtobeat.first.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@DataJpaTest
public class MemberServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberService memberService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createMember() {
        MemberRequestDTO requestDTO = new MemberRequestDTO("member1", "John Doe", 1000.0, LocalTime.of(1, 0), 5.0);
        Member member = new Member("member1", "John Doe", 1000.0, LocalTime.of(1, 0), 5.0);

        when(memberRepository.save(any(Member.class))).thenReturn(member);

        MemberResponseDTO result = memberService.createMember(requestDTO);

        assertEquals(new MemberResponseDTO("member1", "John Doe", 1000.0, LocalTime.of(1, 0), 5.0), result);
        verify(memberRepository, times(1)).save(any(Member.class));
    }

    @Test
    void getMember() {
        String memberId = "member1";
        Member member = new Member("member1", "John Doe", 1000.0, LocalTime.of(1, 0), 5.0);

        when(memberRepository.findById(memberId)).thenReturn(Optional.of(member));

        MemberResponseDTO result = memberService.getMember(memberId);

        assertEquals(new MemberResponseDTO("member1", "John Doe", 1000.0, LocalTime.of(1, 0), 5.0), result);
        verify(memberRepository, times(1)).findById(memberId);
    }
}
