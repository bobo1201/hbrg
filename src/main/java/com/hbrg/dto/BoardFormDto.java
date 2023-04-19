package com.hbrg.dto;

import com.hbrg.entity.Board;
import com.hbrg.entity.HFile;
import com.hbrg.entity.HUser;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class BoardFormDto {

    private Long boardId;

    private String title;

    private String content;

    @ColumnDefault("0")
    private int vc;

    private HFile hFile;

    private HUser hUser;

    private LocalDateTime regTime;
    private LocalDateTime updateTime;


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
