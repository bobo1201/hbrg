package com.hbrg.repository;

import com.hbrg.entity.Board;
import com.hbrg.entity.Hfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileRepository extends JpaRepository<Hfile, Long> {
    List<Hfile> findByBoardIdOrderByFileIdAsc(Board boardId);

    Hfile findByFileId(Long fileId);

    void deleteByBoardId(Long boardId);

    List<Hfile> findByBoard(Board board);
}
