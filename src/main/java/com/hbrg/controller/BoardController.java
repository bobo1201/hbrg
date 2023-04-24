package com.hbrg.controller;

import com.hbrg.dto.BoardFormDto;
import com.hbrg.dto.ReReplyFormDto;
import com.hbrg.dto.ReplyFormDto;
import com.hbrg.entity.*;
import com.hbrg.repository.BoardRepository;
import com.hbrg.repository.ReReplyRepository;
import com.hbrg.repository.ReplyRepository;
import com.hbrg.service.BoardService;
import com.hbrg.service.ReReplyService;
import com.hbrg.service.ReplyService;
import com.hbrg.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value="/hbrg")
@RequiredArgsConstructor
public class BoardController {


    private final BoardRepository boardRepository;

    private final BoardService boardService;

    private final UserService userService;

    private final ReplyService replyService;

    private final ReReplyService reReplyService;

    private final ReplyRepository replyRepository;

    private final ReReplyRepository reReplyRepository;


    // 글쓰기 페이지 실행
    @GetMapping(value = "/ex01")
    public String boardForm(Model model){
        model.addAttribute("BoardFormDto", new BoardFormDto());
        System.out.println("실행2");
        return "content";
    }


    // 글쓰기 페이지에서 Controller로 값 전달
    @PostMapping(value = "/ex01")
    public String boardForm(@Valid BoardFormDto boardFormDto, BindingResult bindingResult, Model model,
                            @RequestParam("itemImgFile") List<MultipartFile> fileList, String hashtags){

        if(bindingResult.hasErrors()){
            System.out.println("에러1");
            return "redirect:/hbrg/ex01";
        }


        try {
            boardService.saveBoard(boardFormDto, fileList);
//            String[] words = hashtags.split("#");
//            List<String> hashtagList = new ArrayList<>();
//            for(String word : words){
//                String trimmed= word.trim();
//                if(!trimmed.isEmpty()){
//                    hashtagList.add(trimmed);
//                }
//            }
//
//            for(String tagList : hashtagList){
//                Tag tag = new Tag();
//
//            }
            System.out.println("실행");

        } catch (Exception e){
            System.out.println("에러2");
            model.addAttribute("errorMessage", "등록 중 에러가 발생했습니다.");
            return "redirect:/hbrg/ex01";
        }

        // userRepository를 만들고 아래와 같이 구현
        //HUser hUser = userRepo.findById(boardFormDto.getId())

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


    // 상품 수정 페이지에서 dto로 값 전달
    @PostMapping(value = "/ex01/{boardId}")
    public String boardUpdate(@Valid BoardFormDto boardFormDto,BindingResult bindingResult, Model model,
                              @RequestParam("itemImgFile") List<MultipartFile> fileList){

        if(bindingResult.hasErrors()){
            System.out.println("에러1");
            return "redirect:/hbrg/ex01/{boardId}";
        }

        try {
            boardService.updateBoard(boardFormDto, fileList);
            System.out.println("실행");
        } catch (Exception e){
            System.out.println("에러2");
            e.printStackTrace();
            model.addAttribute("errorMessage", "수정 중 에러가 발생했습니다.");
            return "redirect:/hbrg/ex01/{boardId}";
        }

        return "redirect:/";
    }


    // 상세페이지 표시 + 댓글
    @GetMapping(value = "/ex02/{boardId}")
    public String boardView(Model model, @PathVariable("boardId") Long boardId){

        BoardFormDto boardFormDto = boardService.getBoardDtl(boardId); // 추가
        Board board = boardFormDto.createBoard();

        List<Reply> replies = replyService.replyList(board);
        ReplyFormDto replyFormDto = new ReplyFormDto();
        replyFormDto.setBoard(board);

        for (Reply reply : replies) {

            List<ReReply> reReplies = reReplyService.reReplyList(reply);
            String attributeName = "reReplies_" + reply.getReId(); // 댓글의 ID와 같은 고유한 이름 생성
            System.out.println("attributeName=" + attributeName);
            model.addAttribute(attributeName, reReplies);
        }

        ReReplyFormDto reReplyFormDto = new ReReplyFormDto();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String author = authentication.getName();
        Huser user = userService.findUser(author);

        board =  boardService.findBoard(boardId);

        Optional<Likes> like = boardService.findLike(board, user);

        int heart = 0;
        if(like.isEmpty()) {
            heart = 0;
        }else {
            heart = 1;
        }

        // 조회수 코드 추가
        boardService.updateView(boardId); //조회수~~
        model.addAttribute("user",user);
        model.addAttribute("heart", heart);
        model.addAttribute("BoardFormDto", boardFormDto);
        model.addAttribute("replyFormDto",replyFormDto);
        model.addAttribute("replies", replies);
        model.addAttribute("reReplyFormDto", reReplyFormDto);


        return "contentview";
    }


    // 상세페이지에서 댓글 값 db로 전달
    @PostMapping(value = "/ex02/{boardId}")
    public String replyForm(ReplyFormDto replyFormDto, Model model,@PathVariable("boardId") Long boardId){
        Reply reply = Reply.createReply(replyFormDto);
        replyService.addReply(reply);

        return "redirect:/hbrg/ex02/"+ boardId;
    }


    // 대댓글 값 db로 전달
    @PostMapping("/{reId}/reReply")
    public String reReply(@PathVariable Long reId, @RequestParam(name="boardId") Long boardId,
                          ReReplyFormDto reReplyFormDto){

        ReReply reReply = ReReply.createReReply(reReplyFormDto);
        System.out.println("테스트:" + reReplyFormDto.getReId());
        Reply reply = replyRepository.findById(reId).orElseThrow(EntityNotFoundException::new);;
        reReply.setReply(reply);
        reReplyService.addReReply(reReply);

        return "redirect:/hbrg/ex02/" + boardId;
    }

    // like 값 연동
    @PostMapping(value = "/like/ex02/{boardId}")
    public @ResponseBody int likeBoard(@PathVariable Long boardId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String author = authentication.getName();

        Huser user = userService.findUser(author);

        Board board = boardService.findBoard(boardId);
        System.out.println(boardId);

        // 저장 true, 삭제 false
        int result = boardService.saveLike(board, user);

        return result;
    }


    // 글삭제(23/04/18 16:58)
    @GetMapping(value = "/ex02/delete/{boardId}")
    public String boardDelete(@PathVariable("boardId") Long boardId){
        boardService.boardDelete(boardId);
        return "redirect:/";
    }


    // 댓글 삭제
    @PostMapping("/{reId}/delete")
    public String replyDelete(@PathVariable Long reId, @RequestParam(name="boardId") Long boardId){

        replyService.deleteReply(reId);
        return "redirect:/hbrg/ex02/" + boardId;
    }

    // 대댓글 삭제
    @PostMapping("/reply/{reReId}/delete")
    public String reReplyDelete(@PathVariable Long reReId, @RequestParam(name="boardId") Long boardId){

        reReplyService.deleteReReply(reReId);
        return "redirect:/hbrg/ex02/" + boardId;
    }
}
