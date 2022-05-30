package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Controller
public class MemberContrroller {
    private final MemberService memberService;

    @Autowired
    public MemberContrroller(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/members/new")
    @ResponseBody
    public Member create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());
        member.setPassword(form.getPassword());
        member.setKakao_id(form.getKakao_id());
        System.out.println("member = " + member.getName());
        return memberService.join(member);
//        return member;
    }

    @GetMapping("/members/memberList")
    @ResponseBody
    public List<Member> list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
//        return "members/memberList";
        return members;
    }

    @PostMapping("/members/memberLogin")
//    @GetMapping("/members/memberLogin")
    @ResponseBody
    public Optional<Member> login(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());
        member.setPassword(form.getPassword());
//        System.out.println(form.getName());
//        System.out.println(member.getName());

        if (memberService.compareMembers(member)) {
            Optional<Member> theMember = memberService.findOne(member.getName());
            System.out.println("login success");
            return theMember;
        } else
            return null;
    }
    @PostMapping("/members/kakaoLogin")
//    @GetMapping("/members/memberLogin")
    @ResponseBody
    public Optional<Member> kakaoLogin(MemberForm form) {
        Member member = new Member();
        member.setKakao_id(form.getKakao_id());
        System.out.println("카카오 로그인 요청 : " +form.getKakao_id());
//        System.out.println(member.getName());

        if (memberService.compareKakaoMembers(member)) {
            Optional<Member> theMember = memberService.findKakao(member.getKakao_id());
            System.out.println("login success");
            return theMember;
        } else
            return null;
    }
    @GetMapping("/members/memberLogout")
    @ResponseBody
    public Member logout(){
        Member member = new Member();
        member = null;
        return member;
    }

    @PostMapping("/members/memberDelete")
    @ResponseBody
    public Member delete(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());
        member.setPassword(form.getPassword());
//        System.out.println(form.getName());
//        System.out.println(member.getName());

        if (memberService.compareMembers(member)) {
            Optional<Member> theMember = memberService.findOne(member.getName());
            System.out.println("delete success");
            memberService.deleteMember(member.getName());
            member = null;
            return member;
        } else
            return member;
    }
//    public String log(MemberForm form) {
//        Member member = new Member();
//        member.setName(form.getName());
//        member.setPassword(form.getPassword());
//        System.out.println("member = " + member.getName());
//        memberService.join(member);
//        return "/";
//    }
}
