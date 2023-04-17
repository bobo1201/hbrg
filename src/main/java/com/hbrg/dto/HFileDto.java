package com.hbrg.dto;

import com.hbrg.entity.HFile;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter
@Setter
public class HFileDto {
    private Long fileId;
    private String fileNm;
    private String oriFileNm;
    private String fileUrl;
    private String repImgYn;

    private static ModelMapper modelMapper = new ModelMapper();

    public static HFileDto of(HFile hFile){
        return modelMapper.map(hFile, HFileDto.class);
    }
}
