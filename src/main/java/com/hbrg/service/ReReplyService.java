package com.hbrg.service;

import com.hbrg.entity.ReReply;
import com.hbrg.entity.Reply;
import com.hbrg.repository.ReReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReReplyService {

    private final ReReplyRepository reReplyRepository;


    @Transactional(readOnly = true)
    public List<ReReply> reReplyList(Reply reply){

        return reReplyRepository.findByReply(reply);
    }

    public void addReReply(ReReply reReply){
        reReplyRepository.save(reReply);
    }

    public void deleteReReply(Long reReId) {
        reReplyRepository.deleteByReReId(reReId);
    }
}
