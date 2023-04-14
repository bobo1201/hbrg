package com.hbrg.service;

import com.hbrg.dto.BoardDto;
import com.hbrg.dto.BoardFormDto;
import com.hbrg.entity.Board;
import com.hbrg.entity.HFile;
import com.hbrg.repository.BoardRepository;
import com.hbrg.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final FileAddService fileAddService;
    private final FileRepository fileRepository;


    public Long saveBoard(BoardFormDto boardFormDto, List<MultipartFile> fileList) throws Exception{

        // 게시물 등록
        Board board = boardFormDto.createBoard();
        boardRepository.save(board);

        // 이미지 등록
        for (int i=0; i<fileList.size(); i++){
            HFile hFile = new HFile();
            hFile.setBoard(board);
            if (i == 0)
                hFile.setRepImgYn("Y");
            else
                hFile.setRepImgYn("N");
            fileAddService.saveFile(hFile, fileList.get(i));
        }
        return board.getBoardId();
    }

    @Transactional(readOnly = true)
    public BoardFormDto getBoardDtl(Long boardId){
//        List<HFile> fileList =  fileRepository.findByBoardIdOrderByFileIdAsc(boardId);
//        List<FileDto> fileDtoList = new ArrayList<>();
//        for (HFile hFile : fileList){
//            FileDto fileDto = FileDto.of(hFile);
//            fileDtoList.add(fileDto);
//        }

        Board board = boardRepository.findByBoardId(boardId);
        BoardFormDto boardFormDto = BoardFormDto.of(board);
//        boardFormDto.setFileDtoList(fileDtoList);
        return boardFormDto;
    }


    @Transactional
    public String savePost(BoardDto boardDto) {
        return boardRepository.save(boardDto.toEntity()).getId();
    }

    public void boardDelete(Long boardId){
        boardRepository.deleteByBoardId(boardId);
    }

    public void Content(Board board){

        boardRepository.save(board);
    }

}
