package com.hbrg.dto;

import com.hbrg.entity.Reply;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReReplyFormDto {

    private Long reReId;

    private Reply Reply;

    private String id;

    private Long reId;

    private LocalDateTime reReCDate;

    private String reReContent;
}