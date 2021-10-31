package lavaspoon.hellospring.service;

import lavaspoon.hellospring.domain.Member;
import lavaspoon.hellospring.repository.MemberRepository;
import lavaspoon.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @AfterEach
    public void afterEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }
    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("hello");
        //when
        long saveId = memberService.join(member);
        //then(id값으로 검색한 ID의 이름과 비교)
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }
    @Test
    public void 중복회원예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");
        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }
    @Test
    void ㅈ() {
    }

    @Test
    void findOne() {
    }
}