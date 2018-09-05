package examples.boot.myboard.controller.api;

import examples.boot.myboard.domain.Board;
import examples.boot.myboard.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

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

        Page<Board>boardPage  = boardService.getBoards(page);

        return new ResponseEntity<>(boardPage, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<Board> getBoardsById(
            @PathVariable(name = "id",
                    required = true) Long id){
        Board board  = boardService.getBoard(id);

        return new ResponseEntity<>( board, HttpStatus.OK);
    }
}
