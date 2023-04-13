package com.hbrg.service;

import com.hbrg.dto.Hbrg_BoardDto;
import com.hbrg.entity.Hbrg_Board;
import com.hbrg.repository.Hbrg_BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class Hbrg_BoardService {

    private final Hbrg_BoardRepository hbrg_boardRepository;

    public Hbrg_Board saveBoard(Hbrg_Board hbrg_board){
        return hbrg_boardRepository.save(hbrg_board);
    }

//    public Long saveItem(Hbrg_BoardDto hbrg_boardDto) throws Exception {
//        //상품 등록
//        Hbrg_Board hbrg_board = Hbrg_BoardDto.createItem();
//        itemRepository.save(item);
//    }
}
