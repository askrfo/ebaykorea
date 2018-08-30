package examples.boot.myboard.controller.api;

import examples.boot.myboard.domain.Board;
import examples.boot.myboard.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/boards")
public class BoardApiController {
    @Autowired
    BoardService boardService;

    @GetMapping
    ResponseEntity<Page<Board>> boards(
            @RequestParam(name = "page",
                    required = false,
                    defaultValue = "1") int page){
        if(1 == 1)
            throw new RuntimeException("exception!!!!");
        Page<Board>boardPage  = boardService.getBoards(page);

        return new ResponseEntity<>(boardPage, HttpStatus.OK);
    }
}
