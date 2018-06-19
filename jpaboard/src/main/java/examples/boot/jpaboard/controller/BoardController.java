package examples.boot.jpaboard.controller;

import examples.boot.jpaboard.domain.Board;
import examples.boot.jpaboard.domain.Member;
import examples.boot.jpaboard.service.BoardService;
import examples.boot.jpaboard.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/boards")
public class BoardController {
    @Autowired
    BoardService boardService;
    @Autowired
    MemberService memberService;

    @GetMapping(path = "/writeform")
    public String writeform(){
        return "boards/writeform";
    }

    @PostMapping
    public String write(Principal principal
            , @ModelAttribute Board board){
        Member member =
                memberService.getMemberByEmail(principal.getName());
        board.setMember(member);
        board.setRegdate(LocalDateTime.now());
        boardService.addBoard(board);

        return "redirect:/boards";
    }
}
