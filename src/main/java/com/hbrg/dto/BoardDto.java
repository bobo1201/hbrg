package com.hbrg.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BoardDto {
    private Long boardId;
    private String id;
    private String title;
    private int vc;
    private int bLike;
    private LocalDateTime cDate;
    private LocalDateTime uDate;
    private String content;


    @Builder
    public BoardDto(String id, String title, String content, LocalDateTime cDate, LocalDateTime uDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.cDate = cDate;
        this.uDate = uDate;
    }
}
