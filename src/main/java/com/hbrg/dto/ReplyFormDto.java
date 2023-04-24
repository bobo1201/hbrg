package com.hbrg.dto;

import com.hbrg.entity.Board;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReplyFormDto {

    private Long reId;

    private Board board;

    private String reContent;
}
