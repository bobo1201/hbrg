package com.hbrg.repository;

import com.hbrg.entity.HFile;
import com.hbrg.entity.HUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileRepository extends JpaRepository<HFile, Long> {
    List<HFile> findByBoardIdOrderByFileIdAsc(Long boardId);

}
