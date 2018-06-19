package examples.boot.jpaboard.repository;

import examples.boot.jpaboard.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository
        extends JpaRepository<Board, Long> {
}
