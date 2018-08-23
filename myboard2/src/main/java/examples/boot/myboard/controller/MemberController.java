package examples.boot.myboard.controller;

import examples.boot.myboard.domain.Member;
import examples.boot.myboard.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/members")
public class MemberController {
    @Autowired
    MemberService memberService;

    @GetMapping("/joinform")
    public String joinform(){
        return "joinform";
    }

    @PostMapping("/join")
    public String join(@ModelAttribute Member member){
        member.setRegdate(LocalDateTime.now());
        memberService.addMember(member);

        return "redirect:welcome";
    }

    @GetMapping("/welcome")
    public String welcome(){
        return "welcome";
    }
}
