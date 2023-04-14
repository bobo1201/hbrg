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

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value="/hbrg")
@RequiredArgsConstructor
public class BoardController {

    private final BoardRepository boardRepository;

    private final BoardService boardService;

//    @GetMapping(value = "/board")
//    public String Board() {
//        return "Content";
//    }


//    @GetMapping(value = "/board")
//    public String getBoardList(Model model) {
//        List<Board> boardList = boardRepository.findAll();
//        model.addAttribute("boardList", boardList);
//        return "main";
//    }


    @GetMapping(value = "/ex01")
    public String boardForm(Model model){
        model.addAttribute("BoardFormDto", new BoardFormDto());
        return "content";
    }


    @PostMapping(value = "/ex01")
    public String boardForm(@Valid BoardFormDto boardFormDto, BindingResult bindingResult, Model model,
                            @RequestParam("itemImgFile") List<MultipartFile> fileList){

        if(bindingResult.hasErrors()){
            return "redirect:/hbrg/ex01";
        }

        if(fileList.get(0).isEmpty() && boardFormDto.getBoardId() == null){
            model.addAttribute("errorMessage", "첫번째 이미지는 필수 입력 값입니다.");
            return "redirect:/hbrg/ex01";
        }

        try {
            boardService.saveBoard(boardFormDto, fileList);
        } catch (Exception e){
            model.addAttribute("errorMessage", "등록 중 에러가 발생했습니다.");
            return "redirect:/hbrg/ex01";
        }

        // userRepository를 만들고 아래와 같이 구현
        //HUser hUser = userRepo.findById(boardFormDto.getId())
        // Board.setId(id);
//        Board board = Board.createBoard(boardFormDto);
//        boardService.saveBoard(board);
        return "redirect:/";
    }

    @GetMapping(value = "/ex01/{boardId}")
    public String boardDtl(@PathVariable("boardId") Long boradId, Model model){

        try{
            BoardFormDto boardFormDto = boardService.getBoardDtl(boradId);
            model.addAttribute("BoardFormDto", boardFormDto);
        } catch (EntityNotFoundException e){
            model.addAttribute("errorMessage", "존재하지 않습니다.");
            model.addAttribute("BoardFormDto", new BoardFormDto());
            return "content";
        }
        return "content";
    }


    @GetMapping(value = "/ex02/{boardId}")
    public String boardView(Model model, @PathVariable("boardId") Long boardId){
        BoardFormDto boardFormDto = boardService.getBoardDtl(boardId); // 추가
        model.addAttribute("boardDto", boardFormDto);
        return "contentView";
    }


}
