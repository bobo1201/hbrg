package com.hbrg.repository;

import com.hbrg.entity.Hbrg_Board;
import com.hbrg.entity.Hbrg_User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Hbrg_BoardRepository extends JpaRepository<Hbrg_Board, Long> {
     List<Hbrg_Board> findAll();

     List<Hbrg_Board> findById(String id);

     List<Hbrg_Board> findByBoardId(Long boardId);
}
