package com.hbrg.repository;

import com.hbrg.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findAll();

    List<Board> findById(String id);


    void deleteByBoardId(Long boardId);

    Board findByBoardId(Long boardId);


//    페이징 처리 추가 23/04/17 16:22 아래 문구 추가
    Page<Board> findAll(Pageable pageable);

}
