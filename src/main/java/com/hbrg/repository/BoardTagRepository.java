package com.hbrg.repository;

import com.hbrg.entity.Board;
import com.hbrg.entity.BoardTag;
import com.hbrg.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardTagRepository extends JpaRepository<BoardTag, Long> {

    List<BoardTag> findByBoard(Board board);

    List<BoardTag> findByTag(Tag tag);

    void deleteByBoard(Board board);
    //Board에 맞는 Tag 삭제 모두
}
