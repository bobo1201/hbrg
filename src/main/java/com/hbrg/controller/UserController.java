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

@RequestMapping("/users")
@Controller
@RequiredArgsConstructor
public class UserController {

    private final Hbrg_UserService hbrg_userService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/users/new")
    public String newUserForm(Model model) {
        Hbrg_UserFormDto hbrg_userFormDto = new Hbrg_UserFormDto();
        model.addAttribute("hbrg_userFormDto", hbrg_userFormDto);
        return "user/userForm";
    }

    @PostMapping(value="/new")
    public String newUser(@Valid Hbrg_UserFormDto hbrg_userFormDto, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "user/userForm";
        }

        try{
            Hbrg_User hbrg_user = Hbrg_User.createHbrg_User(hbrg_userFormDto, passwordEncoder);
        }catch(IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "user/userForm";
        }

        return "redirect:/";
    }

}
