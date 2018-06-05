package examples.boot.jpaexam2.service.impl;

import examples.boot.jpaexam2.domain.Board;
import examples.boot.jpaexam2.domain.Image;
import examples.boot.jpaexam2.repository.BoardRepository;
import examples.boot.jpaexam2.repository.ImageRepository;
import examples.boot.jpaexam2.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardServiceImpl  implements BoardService {
    @Autowired
    BoardRepository boardRepository;

    @Autowired
    ImageRepository imageRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Board> getBoards() {
        List<Board> boards = boardRepository.findAll();
//        List<Board> boards = boardRepository.getBoardList();
//        List<Long> boardNoList = new ArrayList<>();
//
//        boards.stream().forEach(board -> {boardNoList.add(board.getNo());});
//
//        List<Image> imagesByBoardNoList = imageRepository.getImagesByBoardNoList(boardNoList);
//
//        for(Board board : boards){
//            for(Image image: imagesByBoardNoList){
//                if(board == image.getBoard()){
//                    board.getImages().add(image);
//                }
//            }
//        }
        return boards;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Board> getBoards(String searchType, String searchStr) {
//        return boardRepository.searchBoards(searchType, searchStr);
        Pageable pageable = PageRequest.of(2, 2);
        return boardRepository.searchBoards(searchType, searchStr, pageable);
    }

}
