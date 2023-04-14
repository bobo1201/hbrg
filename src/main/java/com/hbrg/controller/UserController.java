package com.hbrg.controller;

import com.hbrg.dto.UserFormDto;
import com.hbrg.entity.HUser;
import com.hbrg.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.validation.Valid;

@RequestMapping("/user")
@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/new")
    public String userForm(Model model) {
        model.addAttribute("userFormDto", new UserFormDto());
        return "users/userForm";
    }

//    @PostMapping(value="/new")
//    public String newUser(UserFormDto userFormDto){
//
//        UserFormDto userFormDto = UserFormDto.createHUser(userFormDto, passwordEncoder);
//        userService.saveHUser(user);
//
//        return "redirect:/";
//    }

    @PostMapping(value="/new")
    public String newUser(@Valid UserFormDto userFormDto, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "users/userForm";
        }

        try{
            HUser hUser = HUser.createHUser(userFormDto, passwordEncoder);
            userService.saveHUser(hUser);
        }catch(IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
        }
        //회원가입 성공시 로그인 페이지로 이동
        return "redirect:/user/login";
    }

    @GetMapping(value="/login")
    public  String loginHUser() {
        return "/users/userLoginForm";
    }

    @GetMapping(value="/login/error")
    public String loginError(Model model){
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해 주세요.");
        return "/users/userLoginForm";
    }
}
