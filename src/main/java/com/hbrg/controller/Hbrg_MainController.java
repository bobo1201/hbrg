package com.hbrg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Hbrg_MainController {

    @GetMapping(value="/")
    public String main() {
        return "main";
    }
}
