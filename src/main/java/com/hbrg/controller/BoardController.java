package com.hbrg.controller;

import com.hbrg.dto.BoardFormDto;
import com.hbrg.entity.Board;
import com.hbrg.repository.BoardRepository;
import com.hbrg.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/list")
@RequiredArgsConstructor
public class BoardController {

    private final BoardRepository boardRepository;

    private final BoardService boardService;

    @GetMapping(value = "/")
    public String getBoardList(Model model) {
        List<Board> boardList = boardRepository.findAll();
        model.addAttribute("boardList", boardList);
        return "main";
    }

//    @GetMapping(value = "/ex01")
//    public String getBoardList1(Model model){
//        List<Board> boardList = boardRepository.findAll();
//        model.addAttribute("boardList", boardList);
//        return "sub01Form";
//    }

    @GetMapping(value = "/ex01")
    public String boardForm(Model model){
        model.addAttribute("BoardFormDto", new BoardFormDto());
        return "sub01Form";
    }


    @PostMapping(value = "/ex01")
    public String boardForm(@Valid BoardFormDto boardFormDto, BindingResult bindingResult, Model model,
                            @RequestParam("itemImgFile") List<MultipartFile> fileList){

        if(bindingResult.hasErrors()){
            return "redirect:/list/ex01";
        }

        if(fileList.get(0).isEmpty() && boardFormDto.getBoardId() == null){
            model.addAttribute("errorMessage", "첫번째 이미지는 필수 입력 값입니다.");
            return "redirect:/list/ex01";
        }

        try {
            boardService.saveBoard(boardFormDto, fileList);
        } catch (Exception e){
            model.addAttribute("errorMessage", "등록 중 에러가 발생했습니다.");
            return "redirect:/list/ex01";
        }

        // userRepository를 만들고 아래와 같이 구현
        //HUser hUser = userRepo.findById(boardFormDto.getId())
        // Board.setId(id);
//        Board board = Board.createBoard(boardFormDto);
//        boardService.saveBoard(board);
        return "redirect:/list/";
    }

    @GetMapping(value = "/ex01/{boardId}")
    public String boardDtl(@PathVariable("boardId") Long boradId, Model model){

        try{
            BoardFormDto boardFormDto = boardService.getBoardDtl(boradId);
            model.addAttribute("BoardFormDto", boardFormDto);
        } catch (EntityNotFoundException e){
            model.addAttribute("errorMessage", "존재하지 않습니다.");
            model.addAttribute("BoardFormDto", new BoardFormDto());
            return "redirect:/list/";
        }
        return "redirect:/list/";
    }

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


    @GetMapping(value = "/ex02/{boardId}")
    public String boardView(Model model, @PathVariable("boardId") Long boardId){
        model.addAttribute("board", boardService.boardview(boardId));
        return "sub02Form";
    }

//    @GetMapping(value = "/delete")
//    public String boardDelete(Integer boardId){
//        boardService.boardDelete(boardId);
//        return "redirect:/list/";
//    }

}
