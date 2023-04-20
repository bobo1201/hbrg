package com.hbrg.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;

@Getter
@Setter
public class BoardSearchDto {

    private String searchDateType;

    private String searchBy;

    private String searchQuery = "";

    private String sort;

    private Sort.Direction direction = Sort.Direction.DESC;


}