package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;


public class MemberService {
    private final MemberRepsitory memberRepsitory;

    public MemberService(MemberRepsitory memberRepsitory) {
        this.memberRepsitory = memberRepsitory;
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Integer join(Member member) {
        memberRepsitory.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("already exist name!");
                });
        System.out.println(passwordEncoder.encode(member.getPassword()));
        String encodedPassword = passwordEncoder.encode(member.getPassword());

        member.setPassword(encodedPassword);
        memberRepsitory.save(member);

        return member.getId();
    }

    public boolean compareMembers(Member member) {
        Optional<Member> loginUser = memberRepsitory.findByName(member.getName());
//        System.out.println(member.getName());
//        System.out.println(Optional.of(memberRepsitory.findByName(member.getName())));
//        System.out.println(Optional.of(loginUser.get()));
//        System.out.println(loginUser.get().getPassword());
        if (loginUser.isEmpty()) {
            System.out.println("doesn't exist member of the name.");
            return false;
        }
        if (!passwordEncoder.matches(member.getPassword(), loginUser.get().getPassword())) {
            System.out.println("no match password.");
            return false;
        }

        return true;
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

    public Optional<Member> findOne(String memberName) {
        return memberRepsitory.findByName(memberName);
    }
}
