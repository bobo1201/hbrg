package com.hbrg.repository;

import com.hbrg.entity.Hbrg_Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Hbrg_BoardRepository extends JpaRepository<Hbrg_Board, Long> {

    Hbrg_Board findByTitle(String title);
    Hbrg_Board findByTitleAndTxt(String title, String Txt);
    List<Hbrg_Board> findByTitleLike(String title);

    Page<Hbrg_Board> findAll(Pageable pageable);

}
