package com.hbrg.controller;

import com.hbrg.dto.Hbrg_UserFormDto;
import com.hbrg.service.Hbrg_UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/users")
@Controller
@RequiredArgsConstructor
public class UserController {

    private final Hbrg_UserService hbrg_userService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping(value="/new")
    public String userForm(Model model){
        model.addAttribute("hbrg_userFormDto", new Hbrg_UserFormDto());
        return "user/userForm";
    }

}
