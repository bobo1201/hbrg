package com.hbrg.dto;

import com.hbrg.entity.Board;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class BoardFormDto {

    private Long boardId;

    private String title;

    private String content;

    /*private Long reId; //?????*/

    @ColumnDefault("0")
    private Long vc;

    private List<HFileDto> fileDtoList = new ArrayList<>();

    private List<Long> fileIds = new ArrayList<>();

    private static ModelMapper modelMapper = new ModelMapper();

    public Board createBoard(){
        return modelMapper.map(this, Board.class);
    }

    public static BoardFormDto of(Board board){
        return modelMapper.map(board, BoardFormDto.class);
    }



}
