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

//    @GetMapping(value = "/")
//    public String getBoardList(Model model) {
//         List<Board> boardList = boardRepository.findAll();
//         model.addAttribute("boardList", boardList);
//
//         BoardFormDto boardFormDto = new BoardFormDto();
//         Board board = new Board();
//         assertEquals(board.getBoardId(), boardFormDto.getBoardId());
//
//        return "main";
//    }

    @GetMapping(value = "/ex01")
    public String boardForm(Model model){
        model.addAttribute("BoardFormDto", new BoardFormDto());
        System.out.println("실행2");
        return "content";
    }


    @PostMapping(value = "/ex01")
    public String boardForm(@Valid BoardFormDto boardFormDto, BindingResult bindingResult, Model model,
                            @RequestParam("itemImgFile") List<MultipartFile> fileList){

        if(bindingResult.hasErrors()){
            System.out.println("에러1");
            return "redirect:/hbrg/ex01";
        }

//        if(fileList.get(0).isEmpty() && boardFormDto.getBoardId() == null){
//            model.addAttribute("errorMessage", "첫번째 이미지는 필수 입력 값입니다.");
//            return "redirect:/hbrg/ex01";
//        }

        try {
            boardService.saveBoard(boardFormDto, fileList);
            System.out.println("실행");

        } catch (Exception e){
            System.out.println("에러2");
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

    // 상품 수정 페이지로 값 전달
    @GetMapping(value = "/ex01/{boardId}")
    public String boardDtl(@PathVariable("boardId") Long boardId, Model model){

        try{
            BoardFormDto boardFormDto = boardService.getBoardDtl(boardId);
            model.addAttribute("BoardFormDto", boardFormDto);
        } catch (EntityNotFoundException e){
            model.addAttribute("errorMessage", "존재하지 않습니다.");
            model.addAttribute("BoardFormDto", new BoardFormDto());
            return "redirect:/hbrg/ex01/{boardId}";
        }
        return "content";
    }


    // 상품 수정 페이지에서 db로 값 전달
    @PostMapping(value = "/ex01/{boardId}")
    public String boardUpdate(@Valid BoardFormDto boardFormDto,BindingResult bindingResult, Model model,
                              @RequestParam("itemImgFile") List<MultipartFile> fileList){

        if(bindingResult.hasErrors()){
            System.out.println("에러1");
            return "redirect:/hbrg/ex01/{boardId}";
        }

//        if(fileList.get(0).isEmpty() && boardFormDto.getBoardId() == null){
//            model.addAttribute("errorMessage", "첫번째 이미지는 필수 입력 값입니다.");
//            return "content";
//        }

        try {
            boardService.updateBoard(boardFormDto, fileList);
            System.out.println("실행");
        } catch (Exception e){
            System.out.println("에러2");
            e.printStackTrace();

            model.addAttribute("errorMessage", "상품 수정 중 에러가 발생했습니다.");
            return "redirect:/hbrg/ex01/{boardId}";
        }

        return "redirect:/";
    }


    // 상세페이지 표시
    @GetMapping(value = "/ex02/{boardId}")
    public String boardView(Model model, @PathVariable("boardId") Long boardId){
        System.out.println("에러 " + boardId);
        BoardFormDto boardFormDto = boardService.getBoardDtl(boardId); // 추가
        model.addAttribute("BoardFormDto", boardFormDto);

        // 조회수 코드 추가
        boardService.updateView(boardId); //조회수~~
        return "contentview";
    }


    // 글삭제(23/04/18 16:58)
    @GetMapping(value = "/ex02/delete/{boardId}")
    public String boardDelete(@PathVariable("boardId") Long boardId){
        boardService.boardDelete(boardId);
        return "redirect:/";
    }

}
