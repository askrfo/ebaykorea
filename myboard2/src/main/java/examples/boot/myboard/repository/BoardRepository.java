package examples.boot.myboard.repository;

import examples.boot.myboard.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<Board, Long> {
    // 페이징 처리를 하는 메소드를 추가한다.

    @Query(value = "select distinct b from Board b join fetch b.member order by b.id desc",
            countQuery = "select count(b) from Board b")
    public Page<Board> findAllByOrderByIdDesc(Pageable pageable);

    @Query(value = "select distinct b from Board b join fetch b.member where b.title like concat('%',:title,'%') order by b.id desc",
            countQuery = "select count(b) from Board b where b.title like concat('%',:title,'%')")
    public Page<Board> findAllByTitleContains(@Param("title") String title, Pageable pageable);

    @Query(value = "select distinct b from Board b join fetch b.member where b.content like concat('%',:content,'%') order by b.id desc",
            countQuery = "select count(b) from Board b where b.content like concat('%',:content,'%')")
    public Page<Board> findAllByContentContains(@Param("content") String content, Pageable pageable);
}
