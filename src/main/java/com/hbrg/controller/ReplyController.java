//package com.hbrg.controller;
//
//import com.hbrg.dto.UserFormDto;
//import com.hbrg.entity.Huser;
//import com.hbrg.service.BoardService;
//import com.hbrg.service.UserService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import javax.validation.Valid;
//import java.util.List;
//
//@RequestMapping("/reply")
//@Controller
//@RequiredArgsConstructor
//public class ReplyController {
//
//    private final BoardService boardService;
//    private BoardService boardService1;
//
//    private  UserService userService;
//
//    //  글 상세보기
//    @GetMapping("/read/{boardId}")
//    public String read(@PathVariable Long boardId,  Model model) {
//        BoardResposeDto boardResposeDto = boardService.findByBoardId(boardId);
//        List<ReplyResponseDto> replies = boardResposeDto.getReply();
//
////      댓글 관련 코드
//        if(replies != null && !replies.isEmpty()){
//            model.addAttribute("replies", replies);
//        }
//
//        /* 사용자 관련 */
////        if (user != null) {
////            model.addAttribute("user", user.getNickname());
////
////            /*게시글 작성자 본인인지 확인*/
////            if (dto.getUserId().equals(user.getId())) {
////                model.addAttribute("writer", true);
////            }
////        }
//
//        model.addAttribute("posts", boardResposeDto);
//        return "reply";
//    }
//
////    @PostMapping(value="/new")
////    public String newUser(UserFormDto userFormDto){
////
////        UserFormDto userFormDto = UserFormDto.createHUser(userFormDto, passwordEncoder);
////        userService.saveHUser(user);
////
////        return "redirect:/";
////    }
//
//    private final PasswordEncoder passwordEncoder;
//
//    @PostMapping(value="/new")
//    public String newUser(@Valid UserFormDto userFormDto, BindingResult bindingResult, Model model){
//        if(bindingResult.hasErrors()){
//            return "users/userForm";
//        }
//
//        try{
//            Huser user = Huser.createHUser(userFormDto, passwordEncoder);
//            userService.saveHUser(user);
//        }catch(IllegalStateException e){
//            model.addAttribute("errorMessage", e.getMessage());
//        }
//        //회원가입 성공시 로그인 페이지로 이동
//        return "redirect:/user/login";
//    }
//
//    @GetMapping(value="/login")
//    public  String loginHUser() {
//        return "/users/userLoginForm";
//    }
//
//    @GetMapping(value="/login/error")
//    public String loginError(Model model){
//        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해 주세요.");
//        return "/users/userLoginForm";
//    }
//}
