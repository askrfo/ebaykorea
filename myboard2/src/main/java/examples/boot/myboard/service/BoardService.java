package examples.boot.myboard.service;

import examples.boot.myboard.domain.Board;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BoardService {
    public Board addBoard(Long memberId, Board board);
    public Page<Board> getBoards(int page);
}
