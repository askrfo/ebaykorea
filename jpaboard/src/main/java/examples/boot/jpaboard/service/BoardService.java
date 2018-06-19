package examples.boot.jpaboard.service;

import examples.boot.jpaboard.domain.Board;
import org.springframework.data.domain.Page;

public interface BoardService {
    public Board addBoard(Board board);
    // searchType : "title", "content", null
    // searchStr : 사용자가 입력한 문자열
    // page : 페이지값.
    public Page<Board>
        getBoards(String searchType, String searchStr, int page);
}
