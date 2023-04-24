package com.hbrg.service;

import com.hbrg.entity.Board;
import com.hbrg.entity.ReReply;
import com.hbrg.entity.Reply;
import com.hbrg.repository.BoardRepository;
import com.hbrg.repository.ReReplyRepository;
import com.hbrg.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final BoardRepository boardRepository;

    private final ReReplyService reReplyService;

    private final ReReplyRepository reReplyRepository;




    @Transactional(readOnly = true)
    public List<Reply> replyList(){

        return replyRepository.findAll();
    }
    @Transactional(readOnly = true)
    public List<Reply> replyList(Board board){
        return replyRepository.findByBoard(board);
    }


    public void addReply(Reply reply){
        replyRepository.save(reply);
    }


    // 댓글 수정 기능 구현
    public void deleteReply(Long reId) {
        Reply reply = replyRepository.findByReId(reId);
        List<ReReply> reReplies = reReplyService.reReplyList(reply);
//        ReReplyFormDto reReplyFormDto = new ReReplyFormDto();
//        reReplyFormDto.setReply(reply);


        if (!CollectionUtils.isEmpty(reReplies)) {
            reReplyRepository.deleteAll(reReplies);
        }

        reReplies.clear();
        replyRepository.deleteByReId(reId);
    }
}
