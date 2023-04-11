package com.hbrg.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Hbrg_BoardDto {
    private Long BoardId;
    private String Id;
    private String Title;
    private Long VC;
    private Long Like;
    private LocalDateTime CDate;
    private LocalDateTime UDate;
    private String Txt;
}
