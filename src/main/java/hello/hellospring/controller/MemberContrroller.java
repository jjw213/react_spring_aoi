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
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());
        member.setPassword(form.getPassword());
        System.out.println("member = " + member.getName());
        memberService.join(member);
        return "redirect:/";
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
//    public String log(MemberForm form) {
//        Member member = new Member();
//        member.setName(form.getName());
//        member.setPassword(form.getPassword());
//        System.out.println("member = " + member.getName());
//        memberService.join(member);
//        return "/";
//    }
}
