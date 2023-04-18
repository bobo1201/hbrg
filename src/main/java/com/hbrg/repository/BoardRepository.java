package com.hbrg.repository;

import com.hbrg.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findAll();

//    List<Board> findById(String id);


    void deleteByBoardId(Long boardId);

    Board findByBoardId(Long boardId);

    Page<Board> findAll(Pageable pageable);

    // 조회수를 위한 쿼리 설정(값 증가)
    @Modifying
    @Query("update Board b set b.vc = b.vc + 1 where b.boardId = :boardId")
    int updateView(Long boardId);


    Page<Board> findByTitleContaining(String searchKeyword, Pageable pageable);

    Page<Board> findByContentContaining(String searchKeyword, Pageable pageable);

    Page<Board> findByVcContaining(String searchKeyword, Pageable pageable);

//    Page<Board> findByBlikeContaining(String searchKeyword, Pageable pageable);


}
