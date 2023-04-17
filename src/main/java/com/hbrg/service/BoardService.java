package com.hbrg.service;

import com.hbrg.dto.BoardFormDto;
import com.hbrg.entity.Board;
import com.hbrg.entity.HFile;
import com.hbrg.repository.BoardRepository;
import com.hbrg.repository.FileRepository;
import lombok.RequiredArgsConstructor;
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



    /* Paging */
//    public Page<Board> getList(int page) {
//        Pageable pageable = PageRequest.of(page, 10);
//        return this.boardRepository.findAll(pageable);
//    }

   /* public void create(String title, String content) {
        Board hb = new Board();
        hb.setTitle(title);
        hb.setContent(content);
        this.boardRepository.save(hb);
    }*/


//    @Transactional
//    public String savePost(BoardDto boardDto) {
//        return boardRepository.save(boardDto.toEntity()).getId();
//    }


    public void Content(Board board){
        boardRepository.save(board);
    }

}
