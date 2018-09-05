package examples.boot.myboard.service;

import examples.boot.myboard.domain.Board;
import examples.boot.myboard.repository.BoardRepository;
import examples.boot.myboard.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public Page<Board> getBoards(int page) {
        // 0페이지가 시작 페이지, 3건씩 보여준다.
        PageRequest pageRequest = PageRequest.of(page - 1, 3);
        Page<Board> boardPage = boardRepository.findAllByOrderByIdDesc(pageRequest);
        return boardPage;
    }

    @Override
    public Board getBoard(Long id) {
        return boardRepository.getOne(id);
    }
}
