package com.hbrg.controller;

import com.hbrg.dto.BoardFormDto;
import com.hbrg.entity.Board;
import com.hbrg.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final BoardRepository boardRepository;

    @GetMapping(value = "/")
    public String main(Model model) {
        List<Board> boardList = boardRepository.findAll();
        model.addAttribute("boardList", boardList);

        BoardFormDto boardFormDto = new BoardFormDto();
        Board board = new Board();
        assertEquals(board.getBoardId(), boardFormDto.getBoardId());

        return "main";
    }
//    @GetMapping(value="/")
//    public String main() {
//        return "main";
//    }
}
