package com.hbrg.service;

import com.hbrg.dto.BoardFormDto;
import com.hbrg.entity.Board;
import com.hbrg.entity.HFile;
import com.hbrg.repository.BoardRepository;
import com.hbrg.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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
//        List<FileDto> fileDtoList = new ArrayList<>();0
//        for (HFile hFile : fileList){
//            FileDto fileDto = FileDto.of(hFile);
//            fileDtoList.add(fileDto);
//        }

        Board board = boardRepository.findByBoardId(boardId);
        BoardFormDto boardFormDto = BoardFormDto.of(board);
//        boardFormDto.setFileDtoList(fileDtoList);
        return boardFormDto;
    }

    public Long updateBoard(BoardFormDto boardFormDto,
                            List<MultipartFile> fileList) throws Exception{

        Board board = boardFormDto.createBoard();
        boardRepository.save(board);

//        // 이미지 등록
//        for (int i=0; i<fileList.size(); i++){
//            HFile hFile = new HFile();
//            hFile.setBoard(board);
//            if (i == 0)
//                hFile.setRepImgYn("Y");
//            else
//                hFile.setRepImgYn("N");
//            fileAddService.saveFile(hFile, fileList.get(i));
//        }


//        // 상품 수정
//        board = boardRepository.findByBoardId(boardFormDto.getBoardId());
//        board.updateBoard(boardFormDto);

//        // 이미지 등록
//        List<Long> fileIds = boardFormDto.getFileIds();
//
//        for(int i=0; i<fileList.size(); i++){
//            fileAddService.updateFile(fileIds.get(i), fileList.get(i));
//        }

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

    public void boardDelete(Long boardId){
        boardRepository.deleteByBoardId(boardId);
    }

//    public Long saveItem(Hbrg_BoardDto hbrg_boardDto) throws Exception {
//        //상품 등록
//        Hbrg_Board hbrg_board = Hbrg_BoardDto.createItem();
//        itemRepository.save(item);
//    }


    public void Content(Board board){
        boardRepository.save(board);
    }

    // 조회수 증가를 위한 쿼리문 사용
    @Transactional
    public int updateView(Long boardId){
        return boardRepository.updateView(boardId);
    }

}
