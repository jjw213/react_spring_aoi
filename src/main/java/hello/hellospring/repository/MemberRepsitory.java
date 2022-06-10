package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepsitory {
    Member save(Member member);

    void saveCode(String str, String userName);

    Optional<Member> findById(Integer id);

    Optional<Member> findByName(String memberName);
    Optional<Member> findByEmail(String email);

    Optional<Member> findByKakao(long name);

    List<Member> findAll();

    boolean deleteByName(String memberName);

    boolean update(String memberName);
}
