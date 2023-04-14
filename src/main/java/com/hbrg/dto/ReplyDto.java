package com.hbrg.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReplyDto {
    private Long reId;              // 댓글 번호
    private Long boardId;           // 게시물 순서
    private String id;              // id
    private LocalDateTime reCDate;  // 생성날짜
    private String reContent;       // 댓글 내용
}
