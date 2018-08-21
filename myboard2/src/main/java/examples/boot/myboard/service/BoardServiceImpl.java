package examples.boot.myboard.service;

import examples.boot.myboard.domain.Board;
import examples.boot.myboard.repository.BoardRepository;
import examples.boot.myboard.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    BoardRepository boardRepository;

    @Override
    @Transactional
    public Board addBoard(Long memberId, Board board) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Board> getBoards(int page) {
        return null;
    }
}
