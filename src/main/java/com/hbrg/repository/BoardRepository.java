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

    List<Board> findById(String id);


    void deleteByBoardId(Long boardId);

    Board findByBoardId(Long boardId);

    Page<Board> findAll(Pageable pageable);

    @Modifying
    @Query("update Board b set b.vC = b.vC + 1 where b.boardId = :boardId")
    int updateView(Long boardId);
}
