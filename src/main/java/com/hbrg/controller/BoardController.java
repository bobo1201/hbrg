package com.hbrg.controller;

import com.hbrg.dto.Hbrg_BoardDto;
import com.hbrg.entity.Hbrg_Board;
import com.hbrg.repository.Hbrg_BoardRepository;
import com.hbrg.service.Hbrg_BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/list")
@RequiredArgsConstructor
public class Hbrg_BoardController {

    private final Hbrg_BoardRepository hbrg_boardRepository;

    private final Hbrg_BoardService hbrg_boardService;

    @GetMapping(value = "")
    public String getBoardList(Model model) {
        List<Hbrg_Board> hbrg_boardList = hbrg_boardRepository.findAll();
        model.addAttribute("hbrg_boardList", hbrg_boardList);
        return "main";
    }

    @GetMapping(value = "/ex01")
    public String getBoardList1(Model model){
        List<Hbrg_Board> hbrg_boardList = hbrg_boardRepository.findAll();
        model.addAttribute("hbrg_boardList", hbrg_boardList);
        return "sub01";
    }
}
