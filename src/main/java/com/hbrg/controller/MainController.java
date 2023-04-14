package com.hbrg.controller;

import com.hbrg.entity.Board;
import com.hbrg.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final BoardRepository boardRepository;

//    @GetMapping(value = "/")
//    public String getBoardList(Model model) {
//        List<Board> boardList = boardRepository.findAll();
//        model.addAttribute("boardList", boardList);
//        return "main";
//    }

    @GetMapping(value="/")
    public String main() {
        return "main";
    }
}
