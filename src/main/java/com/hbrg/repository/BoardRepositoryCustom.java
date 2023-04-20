package com.hbrg.repository;

import com.hbrg.dto.BoardSearchDto;
import com.hbrg.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardRepositoryCustom {
    Page<Board> getAdminItemPage(BoardSearchDto boardSearchDto, Pageable pageable);

//    Page<MainItemDto> getMainItemPage(ItemSearchDto itemSearchDto, Pageable pageable);

}