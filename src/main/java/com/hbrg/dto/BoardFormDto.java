package com.hbrg.dto;

import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class BoardFormDto {

    private Long boardId;

    private String title;

    private String txt;

    private List<FileDto> fileDtoList = new ArrayList<>();

    private List<Long> fileIds = new ArrayList<>();

    private static ModelMapper modelMapper = new ModelMapper();

}
