package com.hbrg.repository;

import com.hbrg.entity.ReReply;
import com.hbrg.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReReplyRepository extends JpaRepository<ReReply, Long> {
    List<ReReply> findByReply(Reply reply);

    void deleteByReReId(Long reReId);
}
