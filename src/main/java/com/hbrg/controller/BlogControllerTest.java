package com.hbrg.controller;

import com.hbrg.dto.BoardFormDto;
import com.hbrg.entity.Board;
import com.hbrg.repository.BoardRepository;
import com.hbrg.service.BoardService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
//@RequestMapping("/list")
@RequiredArgsConstructor
public class BoardController {

    private final BoardRepository boardRepository;

    private final BoardService boardService;

//    @GetMapping(value = "")
//    public String getBoardList(Model model) {
//        List<Board> boardList = boardRepository.findAll();
//        model.addAttribute("boardList", boardList);
//        return "redirect:/";
//    }

//    @GetMapping(value = "/ex01")
//    public String getBoardList1(Model model){
//        List<Board> boardList = boardRepository.findAll();
//        model.addAttribute("boardList", boardList);
//        return "sub01";
//    }

//    @GetMapping(value = "/ex01")
//    public String boardForm(Model model){
//        model.addAttribute("BoardFormDto", new BoardFormDto());
//        return "sub01Form";
//    }


//    @PostMapping(value = "/ex01")
//    public String boardForm(BoardFormDto boardFormDto){
//        System.out.println(boardFormDto.getId());
//        System.out.println(boardFormDto.getTxt());
//        System.out.println(boardFormDto.getBoardId());
//        System.out.println(boardFormDto.getTitle());
//        System.out.println(boardFormDto.getCDate());
//        System.out.println(boardFormDto.getUDate());
//        System.out.println(boardFormDto.getBLike());
//
//        return "출력완료";
//    }

    @GetMapping("/http/get")
    public String getTest(){
        return "get 요청";
    }

    @PostMapping("/http/post")
    public String postTest(){
        return "post 요청";
    }

    @PutMapping("/http/put")
    public String putTest(){
        return "post 요청";
    }

    @DeleteMapping("/http/delete")
    public String deleteTest(){
        return "delete 요청";
    }


}
