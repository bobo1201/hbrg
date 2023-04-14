package com.hbrg.dto;

import com.hbrg.entity.Board;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class BoardResponseDto {

    private String id;
    private String title;
    private String writer;
    private String content;
    private LocalDateTime CDate;
    private LocalDateTime UDate;
    private List<ReplyResponseDto> Reply;

    /* Entity -> Dto */
    public BoardResponseDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.writer = board.getWriter();
        this.content = board.getContent();
        this.CDate = board.getCDate();
        this.UDate = board.getUDate();
        this.Reply = board.getReply().stream().map(ReplyResponseDto::new).collect(Collectors.toList());
    }
}
