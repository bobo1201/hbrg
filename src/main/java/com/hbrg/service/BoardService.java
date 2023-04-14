package com.hbrg.service;

import com.hbrg.dto.BoardDto;
import com.hbrg.entity.Board;
import com.hbrg.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    /* Paging */
//    public Page<Board> getList(int page) {
//        Pageable pageable = PageRequest.of(page, 10);
//        return this.boardRepository.findAll(pageable);
//    }

   /* public void create(String title, String content) {
        Board hb = new Board();
        hb.setTitle(title);
        hb.setContent(content);
        this.boardRepository.save(hb);
    }*/



//    public BoardService(BoardRepository boardRepository) {
//        this.boardRepository = boardRepository;
//    }

    @Transactional
    public String savePost(BoardDto boardDto) {
        return boardRepository.save(boardDto.toEntity()).getId();
    }

//    @Transactional
//    public List<BoardDto> getBoardList() {
//        List<Board> boardList = boardRepository.findAll();
//        List<BoardDto> boardDtoList = new ArrayList<>();
//
//        for(Board board : boardList) {
//            BoardDto boardDto = BoardDto.builder()
//                    .id(board.getId())
//                    .title(board.getTitle())
//                    .content(board.getContent())
//                    .cDate(board.getCDate())
//                    .build();
//            boardDtoList.add(boardDto);
//        }
//        return boardDtoList;
//    }


    public void Content(Board board){

        boardRepository.save(board);
    }

}
