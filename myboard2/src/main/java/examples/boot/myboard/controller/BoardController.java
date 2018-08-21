package examples.boot.myboard.controller;

import examples.boot.myboard.domain.Board;
import examples.boot.myboard.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/boards")
public class BoardController {
    @Autowired
    BoardService boardService;

    @GetMapping
    public String boards(
       @RequestParam(name = "page", required = false, defaultValue = "1") int page,
       ModelMap modelMap){

        // 읽어들인 값을 view에게 전송하기 위해 modelMap에 값을 담는다.
        Page<Board> boardPage = boardService.getBoards(page);
        modelMap.addAttribute("list", boardPage);

        return "list";
    }
}
