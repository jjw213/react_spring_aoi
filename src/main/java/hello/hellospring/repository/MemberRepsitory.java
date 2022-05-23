package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepsitory {
    Member save(Member member);

    Optional<Member> findById(Integer id);

    Optional<Member> findByName(String name);

    Optional<Member> findByKakao(long name);

    List<Member> findAll();
}
