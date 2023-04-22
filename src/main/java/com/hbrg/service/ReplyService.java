package com.hbrg.service;

import com.hbrg.entity.Board;
import com.hbrg.entity.Reply;
import com.hbrg.repository.BoardRepository;
import com.hbrg.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final BoardRepository boardRepository;

    @Transactional(readOnly = true)
    public List<Reply> replyList(){

        return replyRepository.findAll();
    }
    @Transactional(readOnly = true)
    public List<Reply> replyList(Board board){
        return replyRepository.findByBoard(board);
    }




//    public Reply addReply(Long boardId, String content) {
//        Board board = boardRepository.findById(boardId)
//                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));
//
//        Reply reply = new Reply();
//        reply.setReContent(content);
//        reply.setBoard(board);
//
//        return replyRepository.save(reply);
//    }

    public void addReply(Reply reply){
        replyRepository.save(reply);
    }

    public void deleteReply(Long replyId) {

        replyRepository.deleteById(replyId);
    }


}
