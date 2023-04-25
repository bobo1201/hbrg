package com.hbrg.dto;

import com.hbrg.entity.Board;
import com.hbrg.entity.Reply;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter
@Setter
public class ReplyFormDto {

    private Long reId;

    private Board board;

    private String reContent;

    private static ModelMapper modelMapper = new ModelMapper();

    public static ReplyFormDto of(Reply reply) {
        return modelMapper.map(reply, ReplyFormDto.class);
    }
}
