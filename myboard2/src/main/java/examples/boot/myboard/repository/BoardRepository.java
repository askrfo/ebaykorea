package examples.boot.myboard.repository;

import examples.boot.myboard.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
    // 페이징 처리를 하는 메소드를 추가한다.

    public Page<Board> findAllByOrderByIdDesc(Pageable pageable);
    public Page<Board> findAllByTitleContains(String title, Pageable pageable);
    public Page<Board> findAllByContentContains(String content, Pageable pageable);
}
