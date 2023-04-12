package com.hbrg.service;

import com.hbrg.entity.Hbrg_Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

public class Hbrg_BoardService {

    /* Paging */
    @Transactional(readOnly = true)
    public Page<Hbrg_Board> pageList(Pageable pageable) {
        return Hbrg_BoardRepository.findAll(pageable);
    }
}
