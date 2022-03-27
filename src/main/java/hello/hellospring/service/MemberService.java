package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.MemberRepsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;


public class MemberService {
    private final MemberRepsitory memberRepsitory;

    public MemberService(MemberRepsitory memberRepsitory) {
        this.memberRepsitory = memberRepsitory;
    }

    private PasswordEncoder passwordEncoder;

    public Integer join(Member member) {
        memberRepsitory.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원이름!");
                });
        System.out.println(passwordEncoder.encode(member.getPassword()));
        String encodedPassword = passwordEncoder.encode(member.getPassword());

        member.setPassword(encodedPassword);
        memberRepsitory.save(member);
        return member.getId();
    }

    /**
     * 전체회원조회
     *
     * @return
     */
    public List<Member> findMembers() {
        return memberRepsitory.findAll();
    }

    public Optional<Member> findOne(Integer memberId) {
        return memberRepsitory.findById(memberId);
    }
}
