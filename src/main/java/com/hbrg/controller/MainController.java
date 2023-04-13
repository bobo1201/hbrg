package com.hbrg.controller;

import com.hbrg.dto.BoardFormDto;
import com.hbrg.entity.Board;
import com.hbrg.repository.BoardRepository;
import com.hbrg.service.BoardService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/list")
@RequiredArgsConstructor
public class BoardController {

    private final BoardRepository boardRepository;

    private final BoardService boardService;

    @GetMapping(value = "")
    public String getBoardList(Model model) {
        List<Board> boardList = boardRepository.findAll();
        model.addAttribute("boardList", boardList);
        return "main";
    }

//    @GetMapping(value = "/ex01")
//    public String getBoardList1(Model model){
//        List<Board> boardList = boardRepository.findAll();
//        model.addAttribute("boardList", boardList);
//        return "sub01";
//    }

    @GetMapping(value = "/ex01")
    public String boardForm(Model model){
        model.addAttribute("BoardFormDto", new BoardFormDto());
        return "sub01Form";
    }

    @PostMapping(value = "/ex01")
    public String boardForm(BoardFormDto boardFormDto){

        Board board = Board.createBoard(boardFormDto);
        boardService.saveBoard(board);
        return "main";
    }
}
