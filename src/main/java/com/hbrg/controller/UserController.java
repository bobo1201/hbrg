package com.hbrg.controller;

import com.hbrg.dto.Hbrg_UserFormDto;
import com.hbrg.entity.Hbrg_User;
import com.hbrg.service.Hbrg_UserService;
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

    private final Hbrg_UserService hbrg_userService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/new")
    public String newUserForm(Model model) {
        model.addAttribute("hbrg_userFormDto", new Hbrg_UserFormDto());
        return "users/userForm";
    }

//    @PostMapping(value="/new")
//    public String newHbrg_User(Hbrg_UserFormDto hbrg_userFormDto){
//
//        Hbrg_User hbrg_user = Hbrg_User.createHbrg_User(hbrg_userFormDto, passwordEncoder);
//        hbrg_userService.saveHbrg_User(hbrg_user);
//
//        return "redirect:/";
//    }

    @PostMapping(value="/new")
    public String newUser(@Valid Hbrg_UserFormDto hbrg_userFormDto, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "users/userForm";
        }

        try{
            Hbrg_User hbrg_user = Hbrg_User.createHbrg_User(hbrg_userFormDto, passwordEncoder);
            hbrg_userService.saveHbrg_User(hbrg_user);
        }catch(IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "users/userForm";
        }

        return "redirect:/";
    }

    @GetMapping(value="/login")
    public  String loginHbrg_User() {
        return "/users/userLoginForm";
    }

    @GetMapping(value="/login/error")
    public String loginError(Model model){
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해 주세요.");
        return "/users/userLoginForm";
    }
}
