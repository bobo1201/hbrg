package com.hbrg.controller;

import com.hbrg.dto.BoardSearchDto;
import com.hbrg.entity.Board;
import com.hbrg.repository.BoardRepository;
import com.hbrg.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;


@Controller
@RequiredArgsConstructor
public class MainController {

    private final BoardRepository boardRepository;

    private final BoardService boardService;

    @GetMapping(value = {"/", "/{page}"})
    public String BoardManage(BoardSearchDto boardSearchDto, @PathVariable("page") Optional<Integer> page, Model model) {
        // 클릭한 항목에 따라 sort 속성을 설정합니다.
        if(boardSearchDto.getSort() == null) {
            boardSearchDto.setSort("boardId"); // 기본값으로 번호를 선택합니다.
        }
        String sortField = "boardId";
        Sort.Direction direction = Sort.Direction.DESC;
        if (boardSearchDto.getSort() != null) {
            switch (boardSearchDto.getSort()) {
                case "title":
                    sortField = "title";
                    break;
                case "vc":
                    sortField = "vc";
                    break;
//                case "bLike":
//                    sortField = "bLike";
//                    break;
                case "createdBy":
                    sortField = "createdBy";
                    break;
                default:
                    sortField = "boardId";
                    break;
            }
            direction = boardSearchDto.getDirection() == Sort.Direction.ASC ? Sort.Direction.ASC : Sort.Direction.DESC;
        }
        Pageable pageable = PageRequest.of(page.orElse(0), 5, direction, sortField);

        Page<Board> boards = boardService.getAdminItemPage(boardSearchDto, pageable);

        model.addAttribute("boards", boards);
        model.addAttribute("boardSearchDto", boardSearchDto);
        model.addAttribute("maxPage", 5);
        return "main";
    }
}
