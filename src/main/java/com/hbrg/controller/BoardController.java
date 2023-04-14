package com.hbrg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/hbrg")
public class BoardController {


    @GetMapping(value = "/board")
    public String Board() {
        return "Content";
    }
}
