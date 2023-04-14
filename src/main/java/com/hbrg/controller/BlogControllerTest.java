package com.hbrg.controller;

import com.hbrg.repository.BoardRepository;
import com.hbrg.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class BlogControllerTest {

    @GetMapping("/test/get")
    public String hello(){
        return "<h1>Hello Word</h1>";
    }

    @GetMapping("/http/get")
    public String getTest(){
        return "get 요청";
    }

    @PostMapping("/http/post")
    public String postTest(){
        return "post 요청";
    }

    @PutMapping("/http/put")
    public String putTest(){
        return "post 요청";
    }

    @DeleteMapping("/http/delete")
    public String deleteTest(){
        return "delete 요청";
    }


}
