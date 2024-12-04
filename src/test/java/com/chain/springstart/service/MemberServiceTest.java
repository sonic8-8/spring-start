package com.chain.springstart.service;

import com.chain.springstart.domain.member.Member;
import com.chain.springstart.repository.MemberRepository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @DisplayName("회원가입")
    @Test
    void join() {
        // given
        Member member = new Member();
        member.setName("하이");

        // when
        memberService.join(member);

        // then
        assertThat(member.getName()).isEqualTo("하이");

    }

    @DisplayName("중복된 이름으로 회원가입 시 예외가 발생한다.")
    @Test
    void duplicate() {
        // given
        Member member1 = new Member();
        Member member2 = new Member();
        member1.setName("민수");
        member2.setName("민수");

        // when then
        memberService.join(member1);

        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

}