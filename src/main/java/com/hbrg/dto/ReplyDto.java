package com.hbrg.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Hbrg_ReplyDto {
    private Long reId;
    private Long boardId;
    private String id;
    private LocalDateTime reCDate;
    private String reTxt;
}
