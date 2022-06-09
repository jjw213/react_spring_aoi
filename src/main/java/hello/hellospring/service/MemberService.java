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

    public Member join(Member member) {
        memberRepsitory.findByEmail(member.getEmail())
                .ifPresent(m -> {
                    throw new IllegalStateException("already exist email!");
                });
        String encodedPassword = passwordEncoder.encode(member.getPassword());

        member.setPassword(encodedPassword);
        return memberRepsitory.save(member);

//        return member.getId();
    }

    public boolean compareMembers(Member member) {
        Optional<Member> loginUser = memberRepsitory.findByEmail(member.getEmail());
        if (loginUser.isEmpty()) {
            System.out.println("doesn't exist member of the email.");
            return false;
        }
        if (!passwordEncoder.matches(member.getPassword(), loginUser.get().getPassword())) {
            System.out.println("no match password.");
            return false;
        }
        return true;
    }

    public boolean compareKakaoMembers(Member member) {
        Optional<Member> loginUser = memberRepsitory.findByKakao(member.getKakao_id());
//        System.out.println(member.getName());
//        System.out.println(Optional.of(memberRepsitory.findByName(member.getName())));
//        System.out.println(Optional.of(loginUser.get()));
//        System.out.println(loginUser.get().getPassword());
        if (loginUser.isEmpty()) {
            System.out.println("doesn't exist member of the kakao_id.");
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

    public Optional<Member> findOne(String memberEmail) {
        return memberRepsitory.findByEmail(memberEmail);
    }
    public Optional<Member> findOneName(String memberName) {
        return memberRepsitory.findByName(memberName);
    }
    public Optional<Member> findKakao(long kakaoName) {
        return memberRepsitory.findByKakao(kakaoName);
    }
    public boolean deleteMember(String memberName){
        return memberRepsitory.deleteByName(memberName);
    }
}
