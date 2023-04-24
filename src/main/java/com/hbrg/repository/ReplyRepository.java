package com.hbrg.repository;

import com.hbrg.entity.Board;
import com.hbrg.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long>{
    List<Reply> findByBoard(Board board);

    @Transactional
    void deleteByReId(Long reId);

    Reply findByReId(Long reId);
}