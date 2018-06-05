package examples.boot.jpaexam2.controller;

import examples.boot.jpaexam2.domain.Board;
import examples.boot.jpaexam2.domain.Label;
import examples.boot.jpaexam2.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/boards")
public class BoardApiController {
    @Autowired
    BoardService boardService;

    // 게시물 목록을 가지고 오는 쿼리
    // 게시물별로 label들을 가지고 오는 쿼리
    // 1 * n query
    @GetMapping
    List<Board> getBoards() {
//        List<Board> list =  boardService.getBoards();
        List<Board> list =
                boardService.getBoards("subject", "게시");
        return list;
    }
}
