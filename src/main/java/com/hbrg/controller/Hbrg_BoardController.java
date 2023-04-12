package com.hbrg.controller;

import com.hbrg.dto.Hbrg_BoardDto;
import com.hbrg.entity.Hbrg_Board;
import com.hbrg.repository.Hbrg_BoardRepository;
import com.hbrg.service.Hbrg_BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.constraints.Negative;
import java.util.List;

@Controller
@RequestMapping(value="/Hbrg")
public class Hbrg_BoardController {

    public Hbrg_BoardController(Hbrg_BoardService hbrg_boardService) {
        this.Hbrg_BoardService = hbrg_boardService;
    }

    @Autowired
    private Hbrg_BoardService Hbrg_BoardService;

    @Autowired
    private Hbrg_BoardRepository hbrg_boardRepository;

    @GetMapping(value = "")
    public String getBoardList(Model model) {
        List<Hbrg_Board> hbrg_boardList = hbrg_boardRepository.findAll();
        model.addAttribute("hbrg_boardList", hbrg_boardList);
        return "main";
    }

    @GetMapping("/list")
    public String list(Model model, @RequestParam(value="page", defaultValue="0") int page) {
        Page<Hbrg_Board> paging = this.Hbrg_BoardService.getList(page);
        model.addAttribute("paging", paging);
        return "main";
    }

    @GetMapping("/")
    public String list() {
        return "main";
    }

    @GetMapping("/post")
    public String post() {
        return "Board/Txt";
    }

    @PostMapping("/post")
    public String write(Hbrg_BoardDto hbrg_boardDto) {
        Hbrg_BoardService.savePost(hbrg_boardDto);
        return "redirect:/";
    }




    @GetMapping("/board/Txt") //localhost:8090/board/write
    public String Hbrg_boardTxtForm(){

        return "Board/Txt";
    }

    @PostMapping("/board/Txt")
    public String boardWritePro(Hbrg_Board hbrg_board){

        Hbrg_BoardService.Hbrg_Txt(hbrg_board);

        return "";
    }
}
