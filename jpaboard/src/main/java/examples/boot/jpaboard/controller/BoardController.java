package examples.boot.jpaboard.controller;

import examples.boot.jpaboard.domain.Board;
import examples.boot.jpaboard.domain.Member;
import examples.boot.jpaboard.service.BoardService;
import examples.boot.jpaboard.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/boards")
public class BoardController {
    @Autowired
    BoardService boardService;
    @Autowired
    MemberService memberService;

    @GetMapping
    public String list(
            @RequestParam(name = "searchStr", required = false) String searchStr,
            @RequestParam(name = "searchType", required = false) String searchType,
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            ModelMap modelMap){
        Page<Board> pageBoard =
                boardService.getBoards(searchType, searchStr, page);
        modelMap.addAttribute("pageBoard", pageBoard);
        return "boards/list";
    }

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
