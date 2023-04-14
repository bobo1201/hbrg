package com.hbrg.dto;

import com.hbrg.entity.Reply;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReplyRequestDto {
    private String id;              // id
    private String reContent;       // 댓글 내용
    private Long reId;              // 댓글 번호
    private String reCDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));  // 생성날짜
    private String reUDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));  // 수정 날짜
    private Long boardId;           // 게시물 순서


    /* Dto > Entity */
    public Reply toEntity() {
        Reply Replys = Reply.builder()
                .id(id)
                .reContent(reContent)
                .reId(reId)
                .reCDate(reCDate)
                .reUDate(reUDate)
                .boardId(boardId)
                .build();
        return Replys;
    }
}
