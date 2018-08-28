package examples.boot.myboard.repository;

import examples.boot.myboard.domain.Board;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BoardRepositoryTest {
    @Autowired
    BoardRepository boardRepository;

    @Test
    public void getBoardsByTitle() throws Exception{
        Pageable pageable = PageRequest.of(0, 2);
        Page<Board> boardPage = boardRepository.findAllByTitleContains("44", pageable);
        System.out.println("-----------------------------");
        for(Board board : boardPage){
            System.out.println(board.getId());
            System.out.println(board.getTitle());
        }
        System.out.println("-----------------------------");
    }
}
