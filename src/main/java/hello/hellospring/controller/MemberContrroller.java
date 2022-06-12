package hello.hellospring.controller;

import hello.hellospring.domain.MailDTO;
import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import hello.hellospring.service.SendEmailService;
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
    private final SendEmailService sendEmailService;

    @Autowired
    public MemberContrroller(MemberService memberService, SendEmailService sendEmailService) {
        this.memberService = memberService;
        this.sendEmailService = sendEmailService;
    }

    @PostMapping("/members/new")
    @ResponseBody
    public Member create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());
        member.setPassword(form.getPassword());
        member.setKakao_id(form.getKakao_id());
        member.setEmail(form.getEmail());
        try {
            return memberService.join(member);
        } catch (IllegalStateException e) {
            return null;
        }
    }

    @PostMapping("/members/check")
    @ResponseBody
    public Optional<Member> check(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());
        try {
            return memberService.findOneName(member.getName());
        } catch (IllegalStateException e) {
            return null;
        }
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
        member.setEmail(form.getEmail());
        member.setPassword(form.getPassword());
//        System.out.println(form.getName());
//        System.out.println(member.getName());

        if (memberService.compareMembers(member)) {
            Optional<Member> theMember = memberService.findOne(member.getEmail());
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
        System.out.println("카카오 로그인 요청 : " + form.getKakao_id());
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
    public Member logout() {
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

    @PostMapping("/members/codeCheck")
    public @ResponseBody
    Optional<Member> codeCheck(MemberForm form) {
        Optional<Member> member = memberService.findOneName(form.getName());
        if (memberService.codeCheck(form.getCode(), member.get().getCode(), member.get().getName())) {
            return memberService.findOneName(form.getName());
        } else return member;
    }

    @PostMapping("/members/sendEmail")
    public @ResponseBody
    void sendEmail(String userEmail, String name) {
        MailDTO dto = sendEmailService.createMailAndChangePassword(userEmail, name);
        sendEmailService.mailSend(dto);

    }
}
