package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    private final MemberRepsitory memberRepsitory;

    @Autowired
    public MemberService(MemberRepsitory memberRepsitory) {
        this.memberRepsitory = memberRepsitory;
    }

    public Long join(Member member) {
        memberRepsitory.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원이름!");
                });
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

    public Optional<Member> findOne(Long memberId) {
        return memberRepsitory.findById(memberId);
    }
}
