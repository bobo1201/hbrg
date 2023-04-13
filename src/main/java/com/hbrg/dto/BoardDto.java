package com.hbrg.dto;

import com.hbrg.entity.Hbrg_Board;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

@Getter
@Setter
public class Hbrg_BoardDto {
    private Long boardId;
    private String id;
    private String title;
    private Long vC;
    private Long bLike;
    private LocalDateTime cDate;
    private LocalDateTime uDate;
    private String txt;

}
