package com.hbrg.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BoardDto {
    private Long boardId;
    private String id;
    private String title;
    private Long vC;
    private Long bLike;
    private LocalDateTime cDate;
    private LocalDateTime uDate;
    private String txt;

}
