package lavaspoon.hellospring.repository;

import lavaspoon.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

//멤버객체 저장하는 인터페이스
public interface MemberRepository {
    Member save(Member member); //회원을 저장하는 기능
    Optional<Member> findById(Long id); //Id를 찾는 기능
    Optional<Member> findByName(String name); //이름을 찾는 기능
    List<Member> findAll(); //전체를 찾는 기능
}
