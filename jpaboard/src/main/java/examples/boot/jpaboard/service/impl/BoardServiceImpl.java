package examples.boot.jpaboard.service.impl;

import examples.boot.jpaboard.domain.Board;
import examples.boot.jpaboard.repository.BoardRepository;
import examples.boot.jpaboard.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService {
    @Autowired
    BoardRepository boardRepository;

    @Override
    public Board addBoard(Board board) {
        // Board에 이미 Member를 설정하였다.
        return boardRepository.save(board);
    }
}
