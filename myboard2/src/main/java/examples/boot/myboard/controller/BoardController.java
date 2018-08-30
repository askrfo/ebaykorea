package examples.boot.myboard.controller;

import examples.boot.myboard.domain.Board;
import examples.boot.myboard.security.AuthUser;
import examples.boot.myboard.security.MemberLoginInfo;
import examples.boot.myboard.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.jws.WebParam;
import java.security.Principal;

@Controller
@RequestMapping("/boards")
public class BoardController {
    @Autowired
    BoardService boardService;

    @GetMapping
    public String boards(
       @RequestParam(name = "page", required = false, defaultValue = "1") int page,
       ModelMap modelMap){

        if(1 == 1)
            throw new IllegalArgumentException("exception!!!!!");
        // 읽어들인 값을 view에게 전송하기 위해 modelMap에 값을 담는다.
        Page<Board> boardPage = boardService.getBoards(page);
        modelMap.addAttribute("list", boardPage);

        return "list";
    }

    // Principal 객체는 로그인한 사용자의 id를 가지고 있다.
    @GetMapping("/writeform")
    public String writeform(@AuthUser MemberLoginInfo memberLoginInfo,
                            ModelMap modelMap){
        modelMap.addAttribute("name", memberLoginInfo.getName());
        System.out.println("------------------------");
        System.out.println(memberLoginInfo.getId());
        System.out.println(memberLoginInfo.getName());
        System.out.println(memberLoginInfo.getUsername()); // login id
        System.out.println("------------------------");
        return "writeform";
    }
}
