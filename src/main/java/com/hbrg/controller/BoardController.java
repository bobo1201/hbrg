package com.hbrg.controller;

import com.hbrg.dto.BoardDto;
import com.hbrg.entity.Board;
import com.hbrg.repository.BoardRepository;
import com.hbrg.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Page;

import java.util.List;

@Controller
@RequestMapping(value="/Hbrg")
public class BoardController {


    public BoardController(BoardService boardService) {
        this.BoardService = boardService;
    }

    @Autowired
    private BoardService BoardService;

    @Autowired
    private BoardRepository boardRepository;

    @GetMapping(value = "")
    public String getBoardList(Model model) {
        List<Board> boardList = boardRepository.findAll();
        model.addAttribute("boardList", boardList);
        return "main";
    }

    @GetMapping("/list")
    public String list(Model model, @RequestParam(value="page", defaultValue="0") int page) {
        Page<Board> paging = this.BoardService.getList(page);
        model.addAttribute("paging", paging);
        return "main";
    }

    @GetMapping("/")
    public String list() {
        return "main";
    }

    @GetMapping("/post")
    public String post() {
        return "Content";
    }

    @PostMapping("/post")
    public String write(BoardDto boardDto) {
        BoardService.savePost(boardDto);
        return "redirect:/";
    }




    @GetMapping("/Content") //localhost:8090/board/write
    public String boardTxtForm(){

        return "Content";
    }

    @PostMapping("/Content")
    public String boardWritePro(Board board){

        BoardService.Content(board);

        return "";
    }
}
