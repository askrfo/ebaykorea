package examples.boot.jpaexam2.repository.custom;

import com.querydsl.jpa.JPQLQuery;
import examples.boot.jpaexam2.domain.Board;
import examples.boot.jpaexam2.domain.QBoard;
import examples.boot.jpaexam2.domain.QImage;
import examples.boot.jpaexam2.domain.QLabel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.List;

public class BoardRepositoryImpl
        extends QuerydslRepositorySupport
        implements BoardRepositoryCustom{
    @PersistenceContext
    private EntityManager entityManager;

    // QuerydslRepositorySupport 는 기본생성자가 없다.
    // 해당 Repository가 사용하는 Entity클래스 정보를 파라미터로 전달한다.
    public BoardRepositoryImpl(){
        super(Board.class);
    }

//    @Override
//    public List<Board> searchBoards(String searchType, String searchStr) {
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Board> q = cb.createQuery(Board.class);
//
//        Root<Board> board = q.from(Board.class);
//
//        Path<String> subjectPath = board.get("subject");
//        Path<String> contentPath = board.get("content");
//
//        Expression<String> searchStrLiteral = cb.literal("%" + searchStr + "%");
//        if("subject".equals(searchType)) {
//            q.select(board)
//                    .where(cb.like(subjectPath, searchStrLiteral));
//        }else{
//            q.select(board)
//                    .where(cb.like(contentPath, searchStrLiteral));
//        }
//        return entityManager.createQuery(q).getResultList();
//    }


    @Override
    public List<Board>
        searchBoards(String searchType, String searchStr, Pageable pageable) {
        // QClass에 대한 인스턴스를 생성
        QBoard qBoard = QBoard.board;
        QImage qImage = QImage.image;
        QLabel qLabel = QLabel.label;

        // select b from Board b
//        JPQLQuery query = from(qBoard);
        // Board, Label 을 패치조인.
//        JPQLQuery query = from(qBoard)
//                .leftJoin(qBoard.labels, qLabel).fetchJoin();

        // image가 중복되서나온다. board는 N+1
//        JPQLQuery query = from(qBoard)
//                .leftJoin(qBoard.labels, qLabel)
//                .leftJoin(qBoard.images, qImage)
//                .fetchJoin();

        // MultipleBagFetchException
        JPQLQuery query = from(qBoard)
                .distinct()
                .leftJoin(qBoard.labels, qLabel)
                .fetchJoin()
                .leftJoin(qBoard.images, qImage)
                .fetchJoin();

        // where 절 조건
        if("subject".equals(searchType)) {
            query.where(qBoard.subject.like("%" + searchStr + "%"));
        }else{
            query.where(qBoard.content.like("%" + searchStr + "%"));
        }
        // 정렬
        query.orderBy(qBoard.no.desc());

        // 페이징처리
        List<Board> list = getQuerydsl().applyPagination(pageable, query).fetch();


        return list;
    }
}
