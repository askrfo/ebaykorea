package examples.boot.myboard.service;

import examples.boot.myboard.domain.Board;

import java.util.List;

public interface BoardService {
    public Board addBoard(Long memberId, Board board);
    public List<Board> getBoards(int page);
}
