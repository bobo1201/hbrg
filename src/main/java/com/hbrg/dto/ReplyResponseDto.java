package com.hbrg.dto;

import com.hbrg.entity.Reply;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class ReplyResponseDto {

    private String id;                // id
    private String reContent;       // 댓글 내용
    private String reCDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));  // 생성날짜
    private String reUDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));  // 수정 날짜
    //private Long reId;              // 댓글 번호
    private Long boardId;           // 게시물 순서

    /* Entity > Dto */
    public ReplyResponseDto(Reply reply) {
        this.id = reply.getId();
        this.reContent = reply.getReContent();
        this.reCDate = reply.getReCDate();
        this.reUDate = reply.getReUDate();
        this.boardId = reply.getBoardId();

    }
}
