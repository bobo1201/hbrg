package com.hbrg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/Hbrg")
public class BoardController {

    @GetMapping(value = "/Board")
    public String Board() {
        return "Board/Txt";
    }
}
