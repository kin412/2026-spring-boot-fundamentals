package com.kin.springboot2026.controller;

import com.kin.springboot2026.domain.Member;
import com.kin.springboot2026.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    
    // di 주입 3가지 필드주입, 생성자주입, setter 주입
    
    //private final MemberService memberService = new MemberService();
    
    // @Autowired //필드주입
    private MemberService memberService;

    @Autowired // 생성자 주입 -  조립시점에 한번만 호출하고 막아버리기때문에 요즘은 이걸 쓴다. 의존관계가 실행중에 동적으로 바뀌는 경우는 없기 때문
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

//    @Autowired //setter 주입 - 누구나다 setter를 통해 호출을 할 수 있기 때문에 요즘은 안함.
//    public void setMemberService(MemberService memberService) {
//        this.memberService = memberService;
//    }

    @GetMapping("members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";

    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }

}
