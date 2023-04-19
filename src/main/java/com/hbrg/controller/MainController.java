package com.hbrg.controller;

import com.hbrg.entity.Board;
import com.hbrg.repository.BoardRepository;
import com.hbrg.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;


@Controller
@RequiredArgsConstructor
public class MainController {

    private final BoardRepository boardRepository;

    private final BoardService boardService;

    //    @GetMapping(value = "/")
//    public String main(Model model,
//                       @PageableDefault(page=0, size=10, sort="boardId", direction=Sort.Direction.DESC) Pageable pageable,
//                       @RequestParam(value = "searchKeyword", required = false) String searchKeyword,
//                       @RequestParam(value = "searchType", required = false) String searchType) {
//
//        // 페이지블럭 처리
//        Page<Board> list;
//        if (searchKeyword != null && searchType != null) {
//            switch(searchType) {
//                case "title":
//                    list = boardRepository.findByTitleContaining(searchKeyword, pageable);
//                    break;
//                case "content":
//                    list = boardRepository.findByContentContaining(searchKeyword, pageable);
//                    break;
//                case "vc":
//                    list = boardRepository.findByVcContaining(searchKeyword, pageable);
//                    break;
//                default:
//                    list = boardService.boardList(pageable);
//                    break;
//            }
//        } else {
//            list = boardService.boardList(pageable);
//        }
//
//        int nowPage = list.getPageable().getPageNumber() + 1;
//        int startPage = Math.max(nowPage - 2, 1);
//        int endPage = Math.min(nowPage+2, list.getTotalPages());
//        if(nowPage == 1){
//            endPage = Math.min(nowPage + 4, list.getTotalPages());
//        } else if(nowPage == 2){
//            endPage = Math.min(nowPage + 3, list.getTotalPages());
//        }
//
//        String queryString = "";
//        if (searchKeyword != null && searchType != null) {
//            queryString += "searchKeyword=" + searchKeyword + "&searchType=" + searchType + "&";
//        }
//
//        model.addAttribute("boards", list);
//        model.addAttribute("nowPage", nowPage);
//        model.addAttribute("startPage", startPage);
//        model.addAttribute("endPage", endPage);
//        model.addAttribute("searchKeyword", searchKeyword);
//        model.addAttribute("searchType", searchType);
//        model.addAttribute("queryString", queryString);
//
//        return "main";
//    }
    @GetMapping(value = "/")
    public String main(Model model,
                       @PageableDefault(page=0, size=10, sort="boardId", direction=Sort.Direction.DESC) Pageable pageable,
                       @RequestParam(value = "searchKeyword", required = false) String searchKeyword,
                       @RequestParam(value = "searchType", required = false) String searchType,
                       @RequestParam(value = "title", required = false) String title,
                       @RequestParam(value = "boardId", required = false) Long boardId,
                       @RequestParam(value = "vc", required = false) String vc,
                       @RequestParam(value = "regTime", required = false) LocalDateTime regTime) {

        // 페이지블럭 처리
        Page<Board> list;

        if (searchKeyword != null && searchType != null) {
            switch(searchType) {
                case "title":
                    Sort titleSort = Sort.by("title").descending();
                    Pageable titlePageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), titleSort);
                    list = boardRepository.findByTitleContaining(searchKeyword, titlePageable);
                    break;
                case "content":
                    list = boardRepository.findByContentContaining(searchKeyword, pageable);
                    break;
                case "vc":
                    Sort vcSort = Sort.by("vc").descending();
                    Pageable vcPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), vcSort);
                    list = boardRepository.findByVcContaining(searchKeyword, vcPageable);
                    break;
                default:
                    list = boardService.boardList(pageable);
                    break;
            }
        } else if (title != null) {
            Sort titleSort = Sort.by("title").descending();
            Pageable titlePageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), titleSort);
            list = boardRepository.findByTitleContaining(title, titlePageable);
        } else if (boardId != null) {
            Sort boardIdSort = Sort.by("boardId").descending();
            Pageable boardIdPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), boardIdSort);
            list = boardRepository.findByBoardId(boardId, boardIdPageable);
        } else if (vc != null) {
            Sort vcSort = Sort.by("vc").descending();
            Pageable vcPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), vcSort);
            list = boardRepository.findByVcContaining(vc, vcPageable);
        } else if (regTime != null) {
            LocalDateTime parsedRegTime = regTime;
            Sort regTimeSort = Sort.by("regTime").descending();
            Pageable regTimePageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), regTimeSort);
            list = boardRepository.findByRegTime(parsedRegTime, regTimePageable);
        } else {
            list = boardService.boardList(pageable);
        }

        int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 2, 1);
        int endPage = Math.min(nowPage+2, list.getTotalPages());
        if(nowPage == 1){
            endPage = Math.min(nowPage + 4, list.getTotalPages());
        } else if(nowPage == 2){
            endPage = Math.min(nowPage + 3, list.getTotalPages());
        }

        String queryString = "";
        if (searchKeyword != null && searchType != null) {
            queryString += "searchKeyword=" + searchKeyword + "&searchType=" + searchType + "&";
        }

        model.addAttribute("boards", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("searchKeyword", searchKeyword);
        model.addAttribute("searchType", searchType);
        model.addAttribute("queryString", queryString);

        return "main";
    }

}
