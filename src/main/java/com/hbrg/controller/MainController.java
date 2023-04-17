package com.hbrg.controller;

import com.hbrg.dto.BoardFormDto;
import com.hbrg.entity.Board;
import com.hbrg.repository.BoardRepository;
import com.hbrg.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final BoardRepository boardRepository;

    private final BoardService boardService;

    @GetMapping(value = "/")
    public String main(Model model, @PageableDefault(page=0, size=10, sort="boardId", direction= Sort.Direction.DESC) Pageable pageable){


        // 페이징 처리 위한 코드 주석처리 23/04/17 16:22 아래 문구 주석
        // http://localhost:8080/ 경로로 갈때마다 값이 갱신되도록 select 구문 사용
//        List<Board> boardList = boardRepository.findAll();
//        model.addAttribute("boardList", boardList);
//
//        BoardFormDto boardFormDto = new BoardFormDto();
//        Board board = new Board();
//        assertEquals(board.getBoardId(), boardFormDto.getBoardId());



        // 페이징 처리 위한 코드 구현 23/04/17 16:22 아래 문구 추가
        Page<Board> list = boardService.boardList(pageable);

        //페이지블럭 처리
        //pageable은 0부터라 1을 처리하려면 1을 더해야됨 썅
        int nowPage = list.getPageable().getPageNumber() + 1;

        //현재 페이지에서 제일 앞 페이지 번호를 보여줄 변수
        //-1값이 들어가는 것을 막기 위해서 max값으로 두 개의 값을 넣고 더 큰 값을 넣어줌
        int startPage =  Math.max(nowPage - 2, 1);

        int endPage = Math.min(nowPage+2, list.getTotalPages());

        //1페이지일 때, 5페이지까지 출력
        if(nowPage == 1){
            endPage = Math.min(nowPage + 4, list.getTotalPages());
        }else if(nowPage == 2){
            //2페이지일 때, 5까지 출력되도록
            endPage = Math.min(nowPage + 3, list.getTotalPages());
        }

        model.addAttribute("list", list);
        model.addAttribute("nowPage",nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "main";
    }
}
