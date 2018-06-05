package examples.boot.jpaexam2.repository.custom;

import examples.boot.jpaexam2.domain.Board;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface BoardRepositoryCustom {
    // criteria 사용
//    List<Board> searchBoards(String searchType, String searchStr);
    // querydsl 사용,Pageable(페이징 처리)
    List<Board> searchBoards(String searchType, String searchStr, Pageable pageable);
}
