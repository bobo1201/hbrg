package com.hbrg.service;

import com.hbrg.dto.BoardFormDto;
import com.hbrg.dto.HFileDto;
import com.hbrg.entity.Board;
import com.hbrg.entity.Hfile;
import com.hbrg.repository.BoardRepository;
import com.hbrg.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
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
            Hfile file = new Hfile();
            file.setBoard(board);
            if (i == 0)
                file.setRepImgYn("Y");
            else
                file.setRepImgYn("N");
            fileAddService.saveFile(file, fileList.get(i));
        }
        return board.getBoardId();
    }

    // 파일 수정을 위한 파일 읽기
    @Transactional(readOnly = true)
    public BoardFormDto getBoardDtl(Long boardId){

        // 이미지 파일 저장
        Board board = boardRepository.findByBoardId(boardId);
        List<Hfile> fileList = board.getFiles();
        List<HFileDto> fileDtoList = new ArrayList<>();

        for (Hfile file : fileList){
            HFileDto fileDto = HFileDto.of(file);
            fileDtoList.add(fileDto);

            System.out.println(fileDto + " 1");
        }

        // boardform으로 데이터 전달 및 fileDto 저장
        BoardFormDto boardFormDto = BoardFormDto.of(board);
        boardFormDto.setFileDtoList(fileDtoList);
        return boardFormDto;
    }

    // 상품 수정을 위한 메소드 생성
    public Long updateBoard(BoardFormDto boardFormDto,
                            List<MultipartFile> fileList) throws Exception{

        // 상품 수정
        Board board = boardRepository.findByBoardId(boardFormDto.getBoardId());
        board.updateBoard(boardFormDto);

        // 이미지 파일 삭제
        List<Hfile> files = board.getFiles();
        if (!CollectionUtils.isEmpty(files)) {
            fileRepository.deleteAll(files);
        }

        // 게시판에서 삭제
        board.removeHFiles();

        // 이미지 파일 다시 저장
        for (int i=0; i<fileList.size(); i++){
            Hfile file = new Hfile();
            file.setBoard(board);
            if (i == 0)
                file.setRepImgYn("Y");
            else
                file.setRepImgYn("N");
            fileAddService.saveFile(file, fileList.get(i));
        }
        return board.getBoardId();
    }


    // 페이징 처리를 위한 코드 구현 23/04/17 16:22 아래 문구 추가
    public Page<Board> boardList(Pageable pageable){
        //기존 List<Board>값으로 넘어가지만 페이징 설정을 해주면 Page<Board>로 넘어감
        return boardRepository.findAll(pageable);
    }

    public Board boardview(Long boardId){
        return boardRepository.getOne(boardId);
    }


    // 글삭제(23/04/18 16:58)
    public void boardDelete(Long boardId){
        Board board = boardRepository.findByBoardId(boardId);
        List<Hfile> files = board.getFiles();
        if (!CollectionUtils.isEmpty(files)) {
            fileRepository.deleteAll(files);
        }

        board.removeHFiles();
        boardRepository.delete(board);
    }


    // 조회수 증가를 위한 쿼리문 사용
    @Transactional
    public int updateView(Long boardId){
        return boardRepository.updateView(boardId);
    }

}
