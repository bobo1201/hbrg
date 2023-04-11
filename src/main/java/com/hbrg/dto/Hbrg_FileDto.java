package com.hbrg.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Hbrg_FileDto {
    private Long fileId;
    private String id;
    private Long boardId;
    private String fileNm;
    private String oriFileNm;
    private String fileUrl;
}
