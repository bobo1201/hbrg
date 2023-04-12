package com.hbrg.repository;

import com.hbrg.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    Board findByTitle(String title);
    Board findByTitleAndContent(String title, String Content);
    List<Board> findByTitleLike(String title);

    Page<Board> findAll(Pageable pageable);

}
