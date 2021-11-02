package lavaspoon.hellospring.service;

import lavaspoon.hellospring.domain.Member;
import lavaspoon.hellospring.repository.MemberRepository;
import lavaspoon.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     */
    public long join(Member member) {
        //같은이름 중복체크
        validateDuplicateMember(member);
        //회원가입하면 ID 반환
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        Optional<Member> result = memberRepository.findByName(member.getName());
        result.ifPresent(m -> { //ifPresent : 값이 있으면 로직 동작(옵셔널 guard랑 비슷)
            throw new IllegalStateException("이미 존재하는 회원 입니다.");
        });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

}
