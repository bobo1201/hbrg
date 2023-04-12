package com.hbrg.service;

import com.hbrg.dto.Hbrg_BoardDto;
import com.hbrg.entity.Hbrg_Board;
import com.hbrg.repository.Hbrg_BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class Hbrg_BoardService {

    @Autowired
    private Hbrg_BoardRepository hbrg_boardRepository;

    /* Paging */
    public Page<Hbrg_Board> getList(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return this.hbrg_boardRepository.findAll(pageable);
    }

   /* public void create(String title, String txt) {
        Hbrg_Board hb = new Hbrg_Board();
        hb.setTitle(title);
        hb.setTxt(txt);
        this.hbrg_boardRepository.save(hb);
    }*/

    private Hbrg_BoardRepository Hbrg_boardRepository;

    public Hbrg_BoardService(Hbrg_BoardRepository boardRepository) {
        this.hbrg_boardRepository = hbrg_boardRepository;
    }

    @Transactional
    public String savePost(Hbrg_BoardDto hbrg_boardDto) {
        return hbrg_boardRepository.save(hbrg_boardDto.toEntity()).getId();
    }

    @Transactional
    public List<Hbrg_BoardDto> getHbrg_BoardList() {
        List<Hbrg_Board> hbrg_boardList = hbrg_boardRepository.findAll();
        List<Hbrg_BoardDto> hbrg_boardDtoList = new ArrayList<>();

        for(Hbrg_Board Hbrg_board : hbrg_boardList) {
            Hbrg_BoardDto hbrg_boardDto = Hbrg_BoardDto.builder()
                    .id(Hbrg_board.getId())
                    .title(Hbrg_board.getTitle())
                    .txt(Hbrg_board.getTxt())
                    .cDate(Hbrg_board.getCDate())
                    .build();
            hbrg_boardDtoList.add(hbrg_boardDto);
        }
        return hbrg_boardDtoList;
    }


    public void Hbrg_Txt(Hbrg_Board board){

        Hbrg_boardRepository.save(board);
    }
}
