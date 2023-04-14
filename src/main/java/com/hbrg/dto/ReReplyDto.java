package com.hbrg.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReReplyDto {
    private Long reReId;
    private Long reId;
    private String id;
    private LocalDateTime reReCDate;
    private String reReContent;
}
