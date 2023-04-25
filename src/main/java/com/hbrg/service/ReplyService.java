package com.hbrg.service;

import com.hbrg.dto.ReplyFormDto;
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


    // 댓글 수정을 위한 댓글 읽기
    @Transactional(readOnly = true)
    public ReplyFormDto getReplyDtl(Long reId){

        // 이미지 파일 저장
        Reply reply = replyRepository.findByReId(reId);

        // replyform으로 데이터 전달 및 저장
        ReplyFormDto replyFormDto = ReplyFormDto.of(reply);
        return replyFormDto;
    }

    // 댓글 수정을 위한 메소드 생성
    public Long updateReply(ReplyFormDto replyFormDto) throws Exception{
        // 댓글 수정
        Reply reply = replyRepository.findByReId(replyFormDto.getReId());
        reply.createReply(replyFormDto);
        return reply.getReId();
    }


    // 댓글 삭제 기능 구현
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
